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
            <label >年</label>
            <select class="form-control" name="year">
                <option value="2017">2017</option>
                <option value="2018">2018</option>
                <option value="2019">2019</option>
            </select>
        </div>
        &nbsp;
        &nbsp;
        <div class="form-group">
            <label >月</label>
            <select class="form-control" name="month">
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
        </div>
        &nbsp;
        &nbsp;
        <button type="submit" class="btn btn-info">查询</button>
    </form>

        <c:forEach var="map" items="${map}" varStatus="status">
        <h4>本周</h4>
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
            <c:forEach var="moneyLog" items="${map.value}">
                <tr>
                    <th scope="row">${moneyLog.userName}</th>
                    <td>${moneyLog.money}</td>
                    <td>${moneyLog.usefor}</td>
                    <td><fmt:formatDate value="${moneyLog.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:forEach>



</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>

</html>
