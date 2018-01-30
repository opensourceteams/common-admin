
<link rel="stylesheet" href="static/modules/ztree/v3.5.32/css/metroStyle/metroStyle.css" type="text/css">

<script type="text/javascript" src="static/modules/ztree/v3.5.32/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="static/modules/ztree/v3.5.32/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="static/modules/ztree/v3.5.32/js/jquery.ztree.exedit.min.js"></script>


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
            enable: true
        },
        callback: {
            onRemove: zTreeOnRemove //删除事件
        }
    };



    <#-- 初使化事件-->
    $(document).ready(function(){

        $.ajax({
            url: "/common/admin/system_manager/menu/jsonList",
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
        <#-- 增加根节点 按钮 -->
        $(".btn-add-root-node").bind("click", function(){
            addRootNode();
        });
       <#-- 删除选中的节点 按钮 -->
        $(".btn-delete-selected-node").bind("click", function(){
            deleteTreeSelected("treeDemo");
        });

    });

    var treeNodeGlobal ; //选中的节点
    function addHoverDom(treeId, treeNode) {
        treeNodeGlobal = treeNode;
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0){

             if(treeNode.editNameFlag == true && $("#addBtn_"+treeNode.tId).length == 0){
                 //修改
                 editForm(treeNode.id);
                 return ;
             }
            return;

        }
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='新增' onfocus='this.blur();' data-toggle='modal'  ></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);

        if (btn) btn.bind("click", function(){
            $('#exampleModal').modal('show');
            $("input:hidden[name='parentId']")[0].value = treeNode.id;
            $("input:hidden[name='operationType']")[0].value = 'add-operation';
            $("input:hidden[name='id']")[0].value = '';
            $("input:hidden[name='name']")[0].value = '';
            $("#id_remark").text( '') ;
            $("#id_remark").val( '') ;
            $("#id_org_type option[value='1']").attr("selected", true) ;

            return false;
        });
    };

    <#-- 增加根节点 -->
    function addRootNode() {
        $('#exampleModal').modal('show');
        $("input:hidden[name='operationType']")[0].value = 'add-root-node-operation';
        $("input:hidden[name='parentId']")[0].value = '';
        $("input:hidden[name='id']")[0].value = '';
        $("input:hidden[name='name']")[0].value = '';
        $("#id_remark").text( '') ;
        $("#id_remark").val( '') ;
        $("#id_org_type option[value='1']").attr("selected", true) ;

    }
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };


    /**
     * 提交表单事件
     */
    function submitForm() {

        var basicFormData = $('.submit-form').serialize();
        $.post( "/common/admin/system_manager/menu/editJSON", basicFormData, function( data ) {
            if(data && data.success){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if( $("input:hidden[name='operationType']")[0].value == 'add-root-node-operation'){
                    //增加 root 节点
                    zTree.addNodes(null, {id:data.object.id, pId:data.object.parentId, name:data.object.menuName,iconOpen:data.object.iconOpen,iconClose:data.object.iconClose,icon:data.object.icon});
                }if( $("input:hidden[name='operationType']")[0].value == 'add-operation'){
                    //增加
                    zTree.addNodes(treeNodeGlobal, {id:data.object.id, pId:data.object.parentId, name:data.object.menuName,iconOpen:data.object.iconOpen,iconClose:data.object.iconClose,icon:data.object.icon});
                }if( $("input:hidden[name='operationType']")[0].value == 'edit-operation'){
                    //修改
                    treeNodeGlobal.name = data.object.menuName ;
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
        $.post( "/common/admin/system_manager/menu/editViewJSON", {id:id}, function( data ) {
            if(data && data.success){
                $("input:hidden[name='operationType']")[0].value = 'edit-operation';
                $("input:hidden[name='id']")[0].value = id;
                $("input:hidden[name='parentId']")[0].value = data.object.parentId;
                $("input[name='name']")[0].value = data.object.name;
                $("#id_org_type option[value=" + data.object.orgType + "]").attr("selected", true) ;
                $("#id_remark").text( data.object.remark) ;
                $('#exampleModal').modal('show');

            }

        }, "json" );
    }

    /**
     * 删除(节点)
     */
    function zTreeOnRemove(event,treeId, treeNode) {
        $.post( "/common/admin/system_manager/menu/deleteJSON", {id:treeNode.id}, function( data ) {
            if(data && data.success){

                $("input:hidden[name='id']")[0].value = id;
                $("input:hidden[name='parentId']")[0].value = data.object.parentId;
                $("input[name='name']")[0].value = data.object.name;
                $("#id_org_type option[value=" + data.object.orgType + "]").attr("selected", true) ;
                $("#id_remark").text( data.object.remark) ;
                $('#exampleModal').modal('show');

            }

        }, "json" );
    }

    <#-- 打印输出对象-->
    function outputObj(obj) {
        var description = "";
        for (var i in obj) {
            description += i + " = " + obj[i] + "\n";
        }
        console.log("["+ obj +"] " + description);
    }


    /**
     * 删除选中的所有树
     * @param treeId
     */
    function deleteTreeSelected(treeId) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        var nodes = zTree.getCheckedNodes();
        var selectedIds = '';
        for (var i=0, l=nodes.length; i<l; i++) {
            var checked = nodes[i].checked;
            if(checked){
                selectedIds += nodes[i].id +",";
            }

        }
        selectedIds = selectedIds.removeEndWith(",") ;
        if(selectedIds ==''){
           alert('请先选择需要删除的节点');
            return;
        }

        $.post( "/common/admin/system_manager/menu/deleteIdsJSON", {ids:selectedIds}, function( data ) {
            if(data && data.success){
                removeCheckedNodes(zTree,nodes);
            }

        }, "json" );

    }
</SCRIPT>
<p>

    <button type="button" class="btn btn-success btn-add-root-node">增加根节点</button>
    <button type="button" class="btn btn-success btn-delete-selected-node">批量删除</button>
</p>
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
                <h5 class="modal-title" id="exampleModalLabel">菜单编辑</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  class="submit-form">
                    <input type="hidden" name="id" />
                    <input type="hidden" name="parentId" />
                    <input type="hidden" name="operationType" />

                    <div class="form-group">
                        <label for="menuName" class="col-form-label">菜单名称:</label>
                        <input type="text" class="form-control" id="menuName" name="menuName">
                    </div>
                    <div class="form-group">
                        <label for="menuUrl" class="col-form-label">菜单URL:</label>
                        <input type="text" class="form-control" id="menuUrl" name="menuUrl">
                    </div>
                    <div class="form-group">
                        <label for="typeCode">菜单类型</label>
                        <select  class="form-control" id="typeCode" name="typeCode">
                            <option value="1" selected>菜单</option>
                            <option value="2">权限节点</option>
                        </select>
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
