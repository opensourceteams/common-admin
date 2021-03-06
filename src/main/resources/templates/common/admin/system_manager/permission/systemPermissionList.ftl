
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
        }
    };


    <#-- 初使化事件-->
    $(document).ready(function(){

        $.ajax({
            url: "/common/admin/system_manager/permission/jsonList",
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
            if (treeNode.icon.indexOf("permission_") > 0) {
                //员工
                var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.tId
                        + "' title='删除' onfocus='this.blur();'   ></span>";
                sObj.after(removeStr);
                var removeBtn = $("#removeBtn_"+treeNode.tId);
                if (removeBtn) removeBtn.bind("click", function(){
                    deleteForm(treeNode.id);
                });

                var editStr = "<span class='button edit' id='editBtn_" + treeNode.tId
                        + "' title='修改' onfocus='this.blur();'   ></span>";
                sObj.after(editStr);
                var editBtn = $("#editBtn_"+treeNode.tId);
                if (editBtn) editBtn.bind("click", function(){
                    editForm(treeNode.id);
                });
            }else{
                //机构，部门，组
                if (treeNode.editNameFlag || $("#addBtn_"+ treeNode.tId).length>0) return;
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                        + "' title='新增' onfocus='this.blur();'   ></span>";
                sObj.after(addStr);

                var btn = $("#addBtn_"+treeNode.tId);

                if (btn) btn.bind("click", function(){
                    $('#exampleModal').modal('show');
                    $("input:hidden[name='menuId']")[0].value = treeNode.id;
                    $("input:hidden[name='id']")[0].value = '';
                    $("#id_remark").text( '') ;
                    $("#id_remark").val( '') ;
                    $("#parentId option[value='1']").attr("selected", true) ;

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

        var basicFormData = $('.submit-form').serialize();
        $.post( "/common/admin/system_manager/permission/editJSON", basicFormData, function( data ) {
            if(data && data.success){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if( $("input:hidden[name='id']")[0].value == ''){
                    //增加
                    zTree.addNodes(treeNodeGlobal, {id:data.object.sId, pId:data.object.menuId, name:data.object.permissionName,iconOpen:data.object.iconOpen,iconClose:data.object.iconClose,icon:data.object.icon});
                }else{
                    //修改
                    treeNodeGlobal.name = data.object.permissionName ;
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
        $.post( "/common/admin/system_manager/permission/editViewJSON", {id:id }, function( data ) {
            if(data && data.success){

                $("input:hidden[name='id']")[0].value = id;
                $("input:hidden[name='menuId']")[0].value = data.object.menuId;
                $("input[name='permissionName']")[0].value = data.object.permissionName;
                $("input[name='permissionCode']")[0].value = data.object.permissionCode;

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
        $.post( "/common/admin/system_manager/permission/deleteJSON", {id:id}, function( data ) {
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
            if (treeNode.icon.indexOf("13_") > 0) {
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
                <h5 class="modal-title" id="exampleModalLabel">权限编辑</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  class="submit-form">
                    <input type="hidden" name="menuId" />
                    <input type="hidden" name="id" />
                    <div class="form-group">
                        <label for="permissionName" class="col-form-label">权限名:</label>
                        <input type="text" class="form-control" id="permissionName" name="permissionName">
                    </div>
                    <div class="form-group">
                        <label for="permissionCode" class="col-form-label">权限代码:</label>
                        <input type="text" class="form-control" id="permissionCode" name="permissionCode">
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
