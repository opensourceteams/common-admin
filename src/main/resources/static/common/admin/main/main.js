/**
 * 菜单绑定单击事件
 */
function menu_binding_event() {
    var menuArray =  $(".class_menu_url") ;
    for(var i=0;i<menuArray.length;i++){
        if(menuArray[i] && $(menuArray[i])){
            /**
             * 绑定具体事件
             */
            $(menuArray[i]).click(function(){
                ajax_request($(this));
            });
        }

    }
}

function ajax_request(v) {
    var data_url = v.attr("data_url") ;
    deal_menu_navigation(v);

    if(data_url == '' || data_url == undefined  || data_url == '#'){
        return ;
    }
    $(".frame_main_container").html("");
    $.ajax({
        type: "POST",
        url: data_url,
        data: {},
        success: function(html){
            $(".frame_main_container").html(html);
        }
    });
}
var systemMenuListSimple ;
function ajax_request_menu() {
    var data_url = '/common/admin/system_manager/menu/getListSimple' ;
    var data = {};
    $.post(data_url, data, function(response) {
        systemMenuListSimple = response.object;
        console.log('response:' +response) ;
        console.log('systemMenuListSimple:' +systemMenuListSimple) ;
    }, 'json');
    
}

function deal_menu_navigation(v) {
    /**
     * 处理菜单
     */
    var data_navigationId = v.attr("data_navigationId") ;
    var data_id = v.attr("data_id") ;

    $(".nav-item").removeClass("active");
    $("#id_li_" +data_navigationId).addClass("active");

    var currentMenu = getMenu(data_id);
    /**
     * 处理地址导航
     */
    $(".address-navbar").empty();
    $(".address-navbar").append('<li class="nav-item"> <a class="nav-link" href="#">首页&nbsp;&nbsp;></a> </li>' );
    if(data_id == data_navigationId){
        //一级菜单
        $(".address-navbar").append('<li class="nav-item"> <a class="nav-link" href="#">' + currentMenu + '&nbsp;&nbsp;</a> </li>' );
    }else{
        var currentMenuNavigation = getMenu(data_navigationId);
        $(".address-navbar").append('<li class="nav-item"> <a class="nav-link" href="#">' + currentMenuNavigation + '&nbsp;&nbsp;></a> </li>' );
        $(".address-navbar").append('<li class="nav-item"> <a class="nav-link" href="#">' + currentMenu + '&nbsp;&nbsp;</a> </li>' );
    }


}

function getMenu(id) {
    var result ;
    $.each(systemMenuListSimple, function(n, value) {
        if(value.id == id){
            result = value.name ;
        }
    });
   return result;
}