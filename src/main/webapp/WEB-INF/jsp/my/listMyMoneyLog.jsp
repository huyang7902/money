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
    <h3 class="text-center">个人账单</h3>
    <form class="form-inline" action="${basePath}/my/listMyMoneyLog.html">
        <%--<div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" placeholder="姓名">
        </div>--%>
        &nbsp;
        &nbsp;
        <div class="form-group">
            <select class="form-control" name="year" id="year">
                <c:forEach items="${yearList}" var="year1">
                    <option value="${year1}" ${year == year1 ? 'selected' : '' }>${year1}</option>
                </c:forEach>
            </select>
            <label >年</label>
        </div>
        &nbsp;
        &nbsp;
        <div class="form-group">
            <select class="form-control" name="month" id="month">
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
        <h3>总和：${myMoneyLogTotle}元</h3>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>姓名</th>
                <th>金额</th>
                <th>用途</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="myMoneyLog" items="${list}">
                <tr>
                    <th scope="row">${myMoneyLog.userName}</th>
                    <td>${myMoneyLog.money}元</td>
                    <td>${myMoneyLog.usefor}</td>
                    <td><fmt:formatDate value="${myMoneyLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 周${myMoneyLog.weeks} </td>
                    <td>
                        <a href="javascript:void(0);" onclick="goEditMyMoneyLog(${myMoneyLog.uid},'${myMoneyLog.userName}',${myMoneyLog.id},'${myMoneyLog.usefor}',${myMoneyLog.money})">编辑</a>
                        <a href="javascript:void(0);" onclick="goDeleteMoneyLog(${myMoneyLog.id},'${myMoneyLog.userName}','${myMoneyLog.usefor}',${myMoneyLog.money})">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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

    <!-- 编辑对话框-->
    <div class="modal fade" id="editMyModal" tabindex="-1" role="dialog" aria-labelledby="editMyModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">编辑页面</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="form-control" id="id" name="id">
                    <input type="hidden" class="form-control" id="uid" name="uid">
                    <div class="form-group">
                        <label for="userName">姓名:</label>
                        <span id="userName"></span>
                    </div>
                    <div class="form-group">
                        <label for="usefor">用途</label>
                        <input type="text" class="form-control" id="usefor" name="usefor" placeholder="用途" required>
                    </div>
                    <div class="form-group">
                        <label for="money">金额</label>
                        <input type="text" class="form-control" id="money" name="money" placeholder="金额" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="submit" class="btn btn-primary" data-loading-text="Loading..." autocomplete="off" onclick="editMyMoneyLog();">保存</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 删除对话框-->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="mydeleteModalLabel">删除确认页面</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="form-control" id="deleteid" name="id">
                    <div class="form-group">
                        <label for="deleteuserName">姓名:</label>
                        <span id="deleteuserName"></span>
                    </div>
                    <div class="form-group">
                        <label for="deleteusefor">用途</label>
                        <input type="text" class="form-control" id="deleteusefor" name="usefor" placeholder="用途" required>
                    </div>
                    <div class="form-group">
                        <label for="deletemoney">金额</label>
                        <input type="text" class="form-control" id="deletemoney" name="money" placeholder="金额" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="deletesubmit" class="btn btn-primary" data-loading-text="Loading..." autocomplete="off">确认</button>
                </div>
            </div>
        </div>
    </div>

</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>

<script>
    $(function () {
        $("#listMyMoneyLog").addClass("active");
        $(".my").addClass("active");
    });

    /*删除对话框*/
    function goDeleteMoneyLog(id,name,usefor,money) {
        $("#deleteid").val(id);
        $("#deleteuserName").text(name);
        $("#deleteusefor").val(usefor);
        $("#deletemoney").val(money);
        $("#deletesubmit").on("click",function(){
            deleteMyMoneyLog(id);
        });

        $("#deleteModal").modal("show");
    }

    /*删除*/
    function deleteMyMoneyLog (id) {
        $.ajax({
            type : 'POST',
            url : '${basePath}/my/deleteMyMoneyLog.html',
            dataType : 'json',
            data:{
                id:id
            },
            success : function(data) {
                $("#deleteModal").modal("hide");
                if (data.status == 200) {
                    $("#dataModalContent").html("<strong style='color: green'>" + data.msg + "</strong>");

                    $('#dataModal').modal('show');
                    $('#dataModal').on('hidden.bs.modal', function (e) {
                        location.href = "${basePath}/my/listMyMoneyLog.html?year="+$('#year').val() +"&month="+$('#month').val();
                    });
                }
                if (data.status == 400) {
                    //$("#result").html("");
                    //$("tbody").html("");
                    $("#dataModalContent").html("<strong style='color: red'>" + data.msg + "</strong>");
                    $('#dataModal').modal('show');
                }

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
    }

    /*编辑对话框*/
    function goEditMyMoneyLog (uid,name,id,usefor,money) {
        $("#uid").val(uid);
        $("#id").val(id);
        $("#userName").text(name);
        $("#usefor").val(usefor);
        $("#money").val(money);
        $("#editMyModal").modal("show");
    }

    /*编辑*/
    function editMyMoneyLog () {
        $.ajax({
            type : 'POST',
            url : '${basePath}/my/editMyMoneyLog.html',
            dataType : 'json',
            data:{
                uid: $("#uid").val(),
                id: $("#id").val(),
                userName : $("#userName").text(),
                usefor : $("#usefor").val(),
                money:$("#money").val()
            },
            success : function(data) {
                $("#editMyModal").modal("hide");
                if (data.status == 200) {
                    $("#editMyModal").modal("hide");
                    $("#dataModalContent").html("<strong style='color: green'>" + data.msg + "</strong>");
                    $('#dataModal').modal('show');
                    $('#dataModal').on('hidden.bs.modal', function (e) {
                        location.href = "${basePath}/my/listMyMoneyLog.html?year="+$('#year').val() +"&month="+$('#month').val();
                    });
                }
                if (data.status == 400) {
                    //$("#result").html("");
                    //$("tbody").html("");
                    $("#dataModalContent").html("<strong style='color: red'>" + data.msg + "</strong>");
                    $('#dataModal').modal('show');
                }
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
    }
</script>

</html>
