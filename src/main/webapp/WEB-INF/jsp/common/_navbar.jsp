<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/12
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a id="updateLog" class="navbar-brand" href="${basePath}/system/updateLog.html">记账系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li id="index"><a href="${basePath}/money/index.html">主页</a></li>
                <li id="ListUnBalanceMoney"><a href="${basePath}/money/ListUnBalanceMoney.html">未结算</a></li>
                <li id="goAddMoneyLog"><a href="${basePath}/money/goAddMoneyLog.html">添加</a></li>
                <li id="listMoneyLog"><a href="${basePath}/money/listMoneyLog.html">历史账单</a></li>
                <li id="settlementHistory"><a href="${basePath}/money/settlementHistory.html">历史结算</a></li>
                <li id="dropdown" class="dropdown my">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li id="listMyMoneyLog"><a href="${basePath}/my/listMyMoneyLog.html">历史</a></li>
                        <li id="goAddMyMoneyLog"><a href="${basePath}/my/goAddMyMoneyLog.html">添加</a></li>
                    </ul>
                </li>
                <li id="dropdown" class="dropdown setting">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">设置 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li id="resetPassword"><a href="${basePath}/setting/resetPassword.html">修改密码</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%--<li><a href="../navbar/">Default</a></li>
                <li><a href="../navbar-static-top/">Static top</a></li>--%>
                <li class="active"><a href="${basePath}/login/logout.html">登出<span class="sr-only">(current)</span></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>

</nav>
