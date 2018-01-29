
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
            removeTitle:"删除",
            renameTitle:"修改"
        },
        callback: {
            onRemove: zTreeOnRemove ,//删除事件
            beforeEditName: zTreeBeforeEditName
        }
    };


    <#-- 初使化事件-->
    $(document).ready(function(){

        $.ajax({
            url: "/common/admin/system_manager/user/jsonList",
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



        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0){

             if(treeNode.editNameFlag == true && $("#addBtn_"+treeNode.tId).length == 0){

                 if(treeNode && treeNode.icon) {
                     if (treeNode.icon.indexOf("13_") > 0) {
                         //个人客户
                         //修改
                         editForm(treeNode.id);
                     }else{
                         return;
                     }
                 }

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
            $("input:hidden[name='orgId']")[0].value = treeNode.id;
            $("input:hidden[name='id']")[0].value = '';
            $("input:hidden[name='name']")[0].value = '';
            $("input:hidden[name='loginId']")[0].value = '';
            $("#id_remark").text( '') ;
            $("#id_remark").val( '') ;
            $("#parentId option[value='1']").attr("selected", true) ;

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

        var basicFormData = $('.submit-form').serialize();
        $.post( "/common/admin/system_manager/user/editJSON", basicFormData, function( data ) {
            if(data && data.success){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if( $("input:hidden[name='id']")[0].value == ''){
                    //增加
                    zTree.addNodes(treeNodeGlobal, {id:data.object.id, pId:data.object.parentId, name:data.object.name,iconOpen:data.object.iconOpen,iconClose:data.object.iconClose,icon:data.object.icon});
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
     * 修改(节点)
     */
    function editForm(id) {
        $.post( "/common/admin/system_manager/user/editViewJSON", {id:id}, function( data ) {
            if(data && data.success){

                $("input:hidden[name='id']")[0].value = id;
                $("input[name='name']")[0].value = data.object.name;
                $("input[name='loginId']")[0].value = data.object.loginId;

                //$("#parentId option[value=" + data.object.parentId + "]").attr("selected", true) ;
                //$("#parentId option[value=2]").attr("selected", true) ;
                $(".selector_parentId").find("option[value='" + data.object.parentId + "']").attr("selected",true);
                $("#id_remark").text( data.object.remark) ;
                $('#exampleModal').modal('show');

            }

        }, "json" );
    }

    /**
     * 删除(节点)
     */
    function zTreeOnRemove(event,treeId, treeNode) {
        $.post( "/common/admin/system_manager/user/deleteJSON", {id:treeNode.id}, function( data ) {
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
                <h5 class="modal-title" id="exampleModalLabel">用户编辑</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post"  class="submit-form">
                    <input type="hidden" name="orgId" />
                    <input type="hidden" name="id" />
                    <div class="form-group">
                        <label for="loginId" class="col-form-label">登录名:</label>
                        <input type="text" class="form-control" id="loginId" name="loginId">
                    </div>
                    <div class="form-group">
                        <label for="loginPwd" class="col-form-label">登录密码:</label>
                        <input type="password" class="form-control" id="loginPwd" name="loginPwd">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">姓名:</label>
                        <input type="text" class="form-control" id="recipient-name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="exampleSelect2">上级</label>
                        <select  class="form-control selector_parentId" id="exampleSelect2" id="parentId"  name="parentId" >

                            <#if (superiors?? && superiors?size gt  0) >
                                <#list superiors as vo>
                                    <option value="${vo.id}">${vo.name}</option>
                                </#list>
                            </#if>

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
