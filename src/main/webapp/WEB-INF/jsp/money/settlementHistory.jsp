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

    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>总计</th>
                <th>平均</th>
                <th>详细</th>
                <th>时间</th>
                <th>结算人</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="settlementHistory" items="${settlementHistoryList}">
                <tr>
                    <th>${settlementHistory.totleMoney}元</th>
                    <td>${settlementHistory.avgMoney}元</td>
                    <td>${settlementHistory.detail}</td>
                    <td><fmt:formatDate value="${settlementHistory.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${settlementHistory.createUser}</td>
                    <td><a href="${basePath}/money/settlementHistoryInfo.html?settlementId=${settlementHistory.id}" >详情</a></td>

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
        $("#settlementHistory").addClass("active");
    });
</script>

</html>
