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



