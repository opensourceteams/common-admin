<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="static/project/favicon.ico">

    <title>登录</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/static/modules/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="static/modules/bootstrap/bootstrap-4.0.0/examples/signin.css" rel="stylesheet">
</head>

<body class="text-center">

<form class="form-signin" action="doLogin" method="post">

    <#--<img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
    <h1 class="h3 mb-3 font-weight-normal">登录</h1>

    <div class="form-group <#if message?? && code == "1" > has-warning </#if> ">
        <input type="text" name="loginId" class="form-control form-control-warning" placeholder="用户名" required autofocus>
    </div>

    <div class="form-group <#if message?? && code == "2" > has-warning </#if> ">
        <input type="text" name="password" class="form-control form-control-warning" placeholder="密码" required>
    </div>

    <div class="form-group <#if message??> has-warning </#if> ">
        <div class="form-control-feedback">${message!''}</div>
    </div>


    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> 记住密码
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>



</body>
</html>
