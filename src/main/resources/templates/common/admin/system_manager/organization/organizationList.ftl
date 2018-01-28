
<link rel="stylesheet" href="static/modules/ztree/v3.5.32/css/metroStyle/metroStyle.css" type="text/css">
<#--<script type="text/javascript" src="static/modules/jquery/v3.2.1/jquery-3.2.1.min.js"></script>-->
<script type="text/javascript" src="static/modules/ztree/v3.5.32/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="static/modules/ztree/v3.5.32/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="static/modules/ztree/v3.5.32/js/jquery.ztree.exedit.min.js"></script>


<SCRIPT type="text/javascript">
    <!--
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
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"父节点1", open:true},
        { id:11, pId:1, name:"父节点11"},
        { id:111, pId:11, name:"叶子节点111"},
        { id:112, pId:11, name:"叶子节点112"},
        { id:113, pId:11, name:"叶子节点113"},
        { id:114, pId:11, name:"叶子节点114"},
        { id:12, pId:1, name:"父节点12"},
        { id:121, pId:12, name:"叶子节点121"},
        { id:122, pId:12, name:"叶子节点122"},
        { id:123, pId:12, name:"叶子节点123"},
        { id:124, pId:12, name:"叶子节点124"},
        { id:13, pId:1, name:"父节点13", isParent:true},
        { id:2, pId:0, name:"父节点2"},
        { id:21, pId:2, name:"父节点21", open:true},
        { id:211, pId:21, name:"叶子节点211"},
        { id:212, pId:21, name:"叶子节点212"},
        { id:213, pId:21, name:"叶子节点213"},
        { id:214, pId:21, name:"叶子节点214"},
        { id:22, pId:2, name:"父节点22"},
        { id:221, pId:22, name:"叶子节点221"},
        { id:222, pId:22, name:"叶子节点222"},
        { id:223, pId:22, name:"叶子节点223"},
        { id:224, pId:22, name:"叶子节点224"},
        { id:23, pId:2, name:"父节点23"},
        { id:231, pId:23, name:"叶子节点231"},
        { id:232, pId:23, name:"叶子节点232"},
        { id:233, pId:23, name:"叶子节点233"},
        { id:234, pId:23, name:"叶子节点234"},
        { id:3, pId:0, name:"父节点3", isParent:true}
    ];

    $(document).ready(function(){

        $.ajax({
            url: "/common/admin/system_manager/organization/jsonList",
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
       // $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });

    var newCount = 1;
    var treeNodeGlobal ;
    function addHoverDom(treeId, treeNode) {
        treeNodeGlobal = treeNode;
        console.log("[addHoverDom log]");
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0){
             console.log('[edit]' + treeNode.editNameFlag);
             console.log('[edit true]' + (treeNode.editNameFlag == true));
             console.log('[treeNode.length]' + ($("#addBtn_"+treeNode.tId).length));
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

            console.log("[addHoverDom click 增加] " + newCount);

            $('#exampleModal').modal('show');
            $("input:hidden[name='parentId']")[0].value = treeNode.id;
            $("input:hidden[name='id']")[0].value = '';


            return false;
        });
    };
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    };


    /**
     * 提交表单事件
     */
    function submitForm() {
        console.log('[submitForm]' + $("input:hidden[name='parentId']")[0].value );

        var basicFormData = $('.submit-form').serialize();
        $.post( "/common/admin/system_manager/organization/editJSONOrganization", basicFormData, function( data ) {
            console.log( data ); // John
            if(data && data.success){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if( $("input:hidden[name='id']")[0].value == ''){
                    //增加
                    zTree.addNodes(treeNodeGlobal, {id:data.object.id, pId:data.object.parentId, name:data.object.name});
                }else{
                    //修改
                    treeNodeGlobal.name = data.object.name ;
                    zTree.updateNode(treeNodeGlobal) ;
                }

                $('#exampleModal').modal('hide');
            }

        }, "json" );
    }

    /**
     * 修改
     */
    function editForm(id) {
        $.post( "/common/admin/system_manager/organization/editViewJSONOrganization", {id:id}, function( data ) {
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
                <h5 class="modal-title" id="exampleModalLabel">机构编辑</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  class="submit-form">
                    <input type="hidden" name="parentId" />
                    <input type="hidden" name="id" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">名称:</label>
                        <input type="text" class="form-control" id="recipient-name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="exampleSelect2">类型</label>
                        <select  class="form-control" id="exampleSelect2" name="orgType">
                            <option value="1">机构</option>
                            <option value="2">部门</option>
                            <option value="3">组</option>

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
