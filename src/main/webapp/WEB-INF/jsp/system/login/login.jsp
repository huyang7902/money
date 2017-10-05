<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${resourcesPath}/images/favicon.ico" type="image/x-icon">

    <title>欢迎登录记账系统</title>

    <!-- Bootstrap core CSS -->
    <%--<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="${resourcesPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${resourcesPath}/css/signin.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form class="form-signin" action="${basePath}/login/doLogin.html" method="post">
        <h2 class="form-signin-heading">欢迎登录记账系统！</h2>
        <input type="text" id="id" name="userName" class="form-control" placeholder="用户名" value="${param.userName}" required>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
            <label class="text-center">
                <strong style="color: red">${responseResult.msg}</strong>
            </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>
</div> <!-- /container -->
</body>
</html>

