
<link rel="stylesheet" href="/static/modules/ztree/v3.5.32/css/metroStyle/metroStyle.css" type="text/css">

<script type="text/javascript" src="/static/modules/ztree/v3.5.32/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/static/modules/ztree/v3.5.32/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/static/modules/ztree/v3.5.32/js/jquery.ztree.exedit.min.js"></script>


<SCRIPT type="text/javascript">
    <#-- 定义树 -->
    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: true,
            showRemoveBtn:false,
            showRenameBtn:false,
            removeTitle:"删除",
            renameTitle:"修改"
        },
        callback: {
            //onRemove: zTreeOnRemove ,//删除事件
            beforeEditName: zTreeBeforeEditName
        },
        simpleData:{
            enable:true,
            idKey:"id",
            pIdKey:"pId",
            rootPid:0
        }
    };

    var setting_base = {
        view: {
            //addHoverDom: addHoverDom,
            //removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: true,
            showRemoveBtn:false,
            showRenameBtn:false,
            removeTitle:"删除",
            renameTitle:"修改"
        },
        callback: {
            //onRemove: zTreeOnRemove ,//删除事件
            //beforeEditName: zTreeBeforeEditName
        },
        simpleData:{
            enable:false,
            idKey:"sId",
            pIdKey:"pId",
            rootPid:0
        }
    };


    <#-- 初使化事件-->
    $(document).ready(function(){

        $.ajax({
            url: "/common/admin/system_manager/role/jsonList",
            data:{},
            dataType:"json",
            success: function(result){
                if(result.success){
                    $.fn.zTree.init($("#treeDemo"), setting, result.object);
                }

            }
        });

        //绑定提交事件 button-form-submit
        $(".button-form-submit").bind("click", function(){
            submitForm();
        });
    });

    var treeNodeGlobal ; //选中的节点
    function addHoverDom(treeId, treeNode) {

        treeNodeGlobal = treeNode;
        var sObj = $("#" + treeNode.tId + "_span");//当前的节点

        if (treeNode.editNameFlag || $("#editBtn_"+ treeNode.tId).length>0) return;
        if (treeNode.editNameFlag || $("#removeBtn_"+ treeNode.tId).length>0) return;

        if(treeNode && treeNode.icon) {
            if (treeNode.icon.indexOf("role_") > 0) {
                //
                var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.tId
                        + "' title='删除' onfocus='this.blur();'   ></span>";
                sObj.after(removeStr);
                var removeBtn = $("#removeBtn_"+treeNode.tId);
                if (removeBtn) removeBtn.bind("click", function(){

                    deleteForm(treeNode.sId);
                });

                var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
                        + "' title='修改' onfocus='this.blur();'   ></span>";
                sObj.after(editStr);
                var editBtn = $("#editBtn_"+treeNode.tId);
                if (editBtn) editBtn.bind("click", function(){
                    editForm(treeNode.sId);
                });
            }else{
                //机构，部门，组
                if (treeNode.editNameFlag || $("#addBtn_"+ treeNode.tId).length>0) return;
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                        + "' title='新增' onfocus='this.blur();'   ></span>";
                sObj.after(addStr);

                var btn = $("#addBtn_"+treeNode.tId);

                if (btn) btn.bind("click", function(){

                    $("input:hidden[name='orgId']")[0].value = treeNode.id;
                    $("input:hidden[name='id']")[0].value = '';
                    $("input[name='roleName']")[0].value = '';
                    $("input[name='roleCode']")[0].value = '';
                    $("#id_remark").text( '') ;
                    $("#id_remark").val( '') ;


                    $.ajax({
                        url: "/common/admin/system_manager/permission/jsonList",
                        data:{},
                        dataType:"json",
                        success: function(result){
                            if(result.success){
                                $.fn.zTree.init($("#treePermission"), setting_base, result.object);
                                $('#exampleModal').modal('show');
                            }

                        }
                    });

                    return false;
                });
            }
        }


    };
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
        $("#removeBtn_"+treeNode.tId).unbind().remove();
    };


    /**
     * 提交表单事件
     */
    function submitForm() {

        var treePermission = getTreeSelectedIds('treePermission');
        $("input:hidden[name='permissionList']")[0].value = treePermission;
        console.log('treePermission:' + treePermission);

        var basicFormData = $('.submit-form').serialize();


        $.post( "/common/admin/system_manager/role/editJSON", basicFormData, function( data ) {
            if(data && data.success){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if( $("input:hidden[name='id']")[0].value == ''){
                    //增加
                    zTree.addNodes(treeNodeGlobal, {id:data.object.id,sId: data.object.sId, pId:data.object.sPid, name:data.object.roleName,iconOpen:data.object.iconOpen,iconClose:data.object.iconClose,icon:data.object.icon});
                }else{
                    //修改
                    treeNodeGlobal.name = data.object.roleName ;
                    zTree.updateNode(treeNodeGlobal) ;
                }

                $('#exampleModal').modal('hide');
            }

        }, "json" );
    }


    /**
     * 修改(节点)
     */
    function editForm(id) {
        id = id.split('_')[1];
        $.post( "/common/admin/system_manager/role/editViewJSON", {id:id }, function( data ) {
            if(data && data.success){

                $("input:hidden[name='id']")[0].value = id;
                $("input[name='roleName']")[0].value = data.object.roleName;
                $("input[name='roleCode']")[0].value = data.object.roleCode;
                $("#id_remark").text( data.object.remark) ;
                $('#exampleModal').modal('show');

            }

        }, "json" );
    }

    /**
     * 删除(节点)
     */
    function deleteForm(id) {
        id = id.split('_')[1];
        $.post( "/common/admin/system_manager/role/deleteJSON", {id:id}, function( data ) {
            if(data && data.success){

                if(data && data.success){

                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.removeNode(treeNodeGlobal);
                }

            }

        }, "json" );
    }

    <#-- 打印输出对象-->
    function outputObj(obj) {
        var description = "";
        for (var i in obj) {
            description += i + " = " + obj[i] + "\n";
        }

    }

    function zTreeBeforeEditName(treeId, treeNode) {

        if(treeNode && treeNode.icon) {
            if (treeNode.icon.indexOf("role_") > 0) {
                //人员
                return true;
            }
        }
        return false;
    }
</SCRIPT>

<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>

</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">角色编辑</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  class="submit-form">
                    <input type="hidden" name="orgId" />
                    <input type="hidden" name="id" />
                    <input type="hidden" name="permissionList" />
                    <div class="form-group">
                        <label for="roleName" class="col-form-label">角色名称:</label>
                        <input type="text" class="form-control" id="roleName" name="roleName">
                    </div>
                    <div class="form-group">
                        <label for="roleCode" class="col-form-label">角色代码:</label>
                        <input type="text" class="form-control" id="roleCode" name="roleCode">
                    </div>
                    <div class="form-group">
                        <label for="roleCode" class="col-form-label">角色代码:</label>
                        <input type="text" class="form-control" id="roleCode" name="roleCode">
                    </div>
                    <div class="form-group">
                        权限
                        <ul id="treePermission" class="ztree"></ul>
                    </div>

                    <div class="form-group">
                        <label for="message-text" class="col-form-label">备注:</label>
                        <textarea class="form-control" id="id_remark" name="remark"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary button-form-submit">提交</button>
            </div>
        </div>
    </div>
</div>
