
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/static/project/favicon.ico">

    <title>台管理平台</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/static/modules/bootstrap/bootstrap-4.0.0/css/bootstrap.min.css" >

    <!-- Custom styles for this template -->
    <link href="/static/modules/bootstrap/bootstrap-4.0.0/examples/starter-template.css" rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">后台管理平台</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">业务一 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">业务二</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">业务三</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown02"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">财务</a>
                <div class="dropdown-menu" aria-labelledby="dropdown02">
                    <a class="dropdown-item class_menu_url" href="#"  data_url="/finance/check/index">考勤</a>
                    <a class="dropdown-item class_menu_url" href="#" data_url="/common/admin/system_manager/user/index" >工资</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">系统设置</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">

                    <a class="dropdown-item class_menu_url" href="#"  data_url="/common/admin/system_manager/organization/listView" >机构管理</a>
                    <a class="dropdown-item class_menu_url" href="#" data_url="/common/admin/system_manager/user/listView" >用户管理</a>
                    <a class="dropdown-item class_menu_url" href="#" data_url="/common/admin/system_manager/menu/listView">菜单管理</a>
                    <a class="dropdown-item class_menu_url" href="#" data_url="/common/admin/system_manager/role/listView">角色管理</a>
                    <a class="dropdown-item class_menu_url" href="#" data_url="/common/admin/system_manager/permission/listView">权限管理</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <span class="btn-outline-success my-2 my-sm-0" style="padding-left: 10px">${username}</span>
        <a href="/logout" class="btn-outline-success my-2 my-sm-0" style="padding-left: 10px">退出</a>



    </div>
</nav>
<nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="#">主页&nbsp;&nbsp;></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">系统设置&nbsp;&nbsp;></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">菜单管理</a>
        </li>
    </ul>
</nav>
​
<#--<main role="main" class="container frame_main_container">-->
<main class="container frame_main_container">
    <div class="starter-template">
        <h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
    </div>

</main><!-- /.container -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<#--<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
<script src="/static/modules/jquery/v3.2.1/jquery-3.2.1.min.js" ></script>
<script src="/static/modules/bootstrap/bootstrap-4.0.0/vendor/js/popper.min.js" ></script>
<script src="/static/modules/bootstrap/bootstrap-4.0.0/js/bootstrap.min.js" ></script>
<script src="/static/common/admin/main/main.js" ></script>
<script src="/static/common/core/common.core.string.js" ></script>
<script src="/static/common/core/common.core.ztree.js" ></script>


<script type="text/javascript">

    $(document).ready(function(){
         menu_binding_event();
    });



</script>
</body>
</html>
