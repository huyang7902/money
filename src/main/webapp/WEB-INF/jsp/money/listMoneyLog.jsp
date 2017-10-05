<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="/WEB-INF/jsp/common/_header.jsp"/>
<body>
<!-- 导航栏 -->
<jsp:include page="/WEB-INF/jsp/common/_navbar.jsp"/>
<div class="container">

    <form class="form-inline" action="${basePath}/money/listMoneyLog.html">
        <%--<div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" placeholder="姓名">
        </div>--%>
        &nbsp;
        &nbsp;
        <div class="form-group">
            <select class="form-control" name="year">
                <c:forEach items="${yearList}" var="year1">
                    <option value="${year1}" ${year == year1 ? 'selected' : '' }>${year1}</option>
                </c:forEach>
            </select>
            <label >年</label>
        </div>
        &nbsp;
        &nbsp;
        <div class="form-group">
            <select class="form-control" name="month">
                <c:forEach items="${monthList}" var="month1">
                    <option value="${month1}" ${month == month1 ? 'selected' : '' }>${month1}</option>
                </c:forEach>
            </select>
            <label >月</label>
        </div>
        &nbsp;
        &nbsp;
        <button type="submit" class="btn btn-info">查询</button>
    </form>
    <h3>总和：${moneyLogTotle}元</h3>
    <c:forEach items="${sumMoneyLogList}" var="sumMoneyLog">
        <h4>${sumMoneyLog.userName}：${sumMoneyLog.totleMoney}元</h4>
    </c:forEach>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>姓名</th>
                <th>金额</th>
                <th>用途</th>
                <th>时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="moneyLog" items="${list}">
                <tr>
                    <th scope="row">${moneyLog.userName}</th>
                    <td>${moneyLog.money}</td>
                    <td>${moneyLog.usefor}</td>
                    <td><fmt:formatDate value="${moneyLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 周${moneyLog.weeks} </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>

<script>
    $(function () {
        $("#listMoneyLog").addClass("active");
    });
</script>

</html>
