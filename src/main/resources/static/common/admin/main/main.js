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