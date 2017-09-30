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


        <button type="button" id="calculate" class="btn btn-info">计算</button>

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
                    <td>${moneyLog.money}</td>
                    <td>${moneyLog.usefor}</td>
                    <td><fmt:formatDate value="${moneyLog.createTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="result">

        </div>

    <!-- 消息提示框 -->
    <div class="modal fade" id="dataModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="dataModalModalLabel">信息</h4>
                </div>
                <div class="modal-body" id="dataModalContent">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>
</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>
<script>
    $(function () {
        $("#calculate").click(function () {
            $.ajax({
                type : 'POST',
                url : '${basePath}/money/calculate.html',
                dataType : 'json',
                success : function(data) {
                    $("#result").html("");
                    var html = "";
                    html += "<p>总计："+data.totle+"</p>";
                    html += "<p>胡洋："+data.hy+"</p>";
                    html += "<p>何晓波："+data.hxb+"</p>";
                    html += "<p>李丹全："+data.ldq+"</p>";
                    html += "<p>平均："+data.avg+"</p>";
                    $("#result").html(html);
                },
                error : function(data) {
                    if(data!=null) {
                        $("#dataModalContent").html("<strong style='color: red'>"+data.msg+"</strong>" +data.data);
                        $('#dataModal').modal('show');
                    } else {"服务器错误！"
                        $("#dataModalContent").html("<strong style='color: red'>服务器错误！</strong>");
                        $('#dataModal').modal('show');
                    }
                },
            });
        });



    });

</script>

</html>
