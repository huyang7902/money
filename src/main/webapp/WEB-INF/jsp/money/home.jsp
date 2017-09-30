<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="/WEB-INF/jsp/common/_header.jsp"/>
<body>
<!-- 导航栏 -->
<jsp:include page="/WEB-INF/jsp/common/_navbar.jsp"/>
<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>您好：${loginUser.name}</h1>
        <p>您上次登录时间为：<fmt:formatDate value="${loginUser.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"/> </p>

    </div>

</div> <!-- /container -->


<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>
</html>
