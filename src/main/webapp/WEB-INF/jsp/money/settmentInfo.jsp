<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="/WEB-INF/jsp/common/_header.jsp"/>
<style>
    .jumbotron p {
        margin-bottom: 15px;
        font-size: 15px;
        font-weight: 200;
    }
</style>
<body>
<!-- 导航栏 -->
<jsp:include page="/WEB-INF/jsp/common/_navbar.jsp"/>
<div class="container" >
    <div class="jumbotron" style="padding-top: 5px;padding-bottom: 5px;">
        <div class="container" >
            <p>结算时间：<fmt:formatDate value="${settlementHistory.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
            <p>总计：${settlementHistory.totleMoney}元</p>
            <p>平均：${settlementHistory.avgMoney}元</p>
            <c:forTokens items="${settlementHistory.detail}" var="detail" delims="；">
            <p>${detail}</p>
            </c:forTokens>
        </div>
    </div>
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
            <c:forEach var="moneyLog" items="${moneyLogList}">
                <tr>
                    <th scope="row">${moneyLog.userName}</th>
                    <td>${moneyLog.money}元</td>
                    <td>${moneyLog.usefor}</td>
                    <td><fmt:formatDate value="${moneyLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 周${moneyLog.weeks} </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>
<script>
    $(function () {
        $("#settlementHistory").addClass("active");
    });

</script>

</html>
