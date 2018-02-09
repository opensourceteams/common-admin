/**
 * 删除选中节点(根据树ID)
 */
function removeCheckedNodes(id_Tree_Div)
{
    var treeObj = $.fn.zTree.getZTreeObj(id_Tree_Div);
    //选中节点
    var nodes = treeObj.getCheckedNodes();
    for (var i=0, l=nodes.length; i < l; i++)
    {
        //删除选中的节点
        treeObj.removeNode(nodes[i]);
    }
}

/**
 * 删除选中节点(根据选中的节点)
 *  treeObj:树对象
 *  nodes:选中的节点对象
 */
function removeCheckedNodes(treeObj,nodes)
{
    for (var i=0, l=nodes.length; i < l; i++)
    {
        //删除选中的节点
        treeObj.removeNode(nodes[i]);
    }
}


/**
 * 得到选中的ids
 * @param treeId
 * @returns {string}
 */
function getTreeSelectedIds(treeId) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    var nodes = zTree.getCheckedNodes();
    var selectedIds = '';
    for (var i = 0, l = nodes.length; i < l; i++) {
        var checked = nodes[i].checked;
        if (checked) {
            selectedIds += nodes[i].id + ",";
        }

    }
    selectedIds = selectedIds.removeEndWith(",");
    return selectedIds;
}

/**
 * 得到选中的ids
 * @param treeId
 * @returns {string}
 */
function getTreeSelectedIdsRemovePrefix(treeId) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    var nodes = zTree.getCheckedNodes();
    var selectedIds = '';
    for (var i = 0, l = nodes.length; i < l; i++) {
        var checked = nodes[i].checked;
        if (checked) {
            if(nodes[i].id.toString().indexOf("_") == -1){
                //不带前缀的id
                selectedIds += nodes[i].id + ",";
            }else{
                //带前缀的id
                selectedIds += nodes[i].id.split('_')[1] + ",";
            }

        }

    }
    selectedIds = selectedIds.removeEndWith(",");
    return selectedIds;
}


