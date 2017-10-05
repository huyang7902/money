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
                <th>姓名</th>
                <th>金额</th>
                <th>用途</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="moneyLog" items="${moneyLogList}">
                <tr>
                    <th scope="row">${moneyLog.userName}</th>
                    <td>${moneyLog.money}元</td>
                    <td>${moneyLog.usefor}</td>
                    <td><fmt:formatDate value="${moneyLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 周${moneyLog.weeks} </td>
                    <td>
                        <a href="javascript:void(0);" onclick="goEditMoneyLog(${moneyLog.uid},'${moneyLog.userName}',${moneyLog.id},'${moneyLog.usefor}',${moneyLog.money})">编辑</a>
                        <a href="javascript:void(0);" onclick="goDeleteMoneyLog(${moneyLog.id},'${moneyLog.userName}','${moneyLog.usefor}',${moneyLog.money})">删除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <button type="button" id="calculate" class="btn btn-info">计算</button>
    <button type="button" class="btn btn-danger" onclick="goBalance();">结算</button>
    <br>
    <br>
    <br>
        <div id="result"></div>

    <!-- 编辑对话框-->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
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
                    <button type="button" id="submit" class="btn btn-primary" data-loading-text="Loading..." autocomplete="off" onclick="editMoneyLog();">保存</button>
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

    <!-- 确认结算对话框-->
    <div class="modal fade" id="balanceModal" tabindex="-1" role="dialog" aria-labelledby="balanceModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="mybalanceModalLabel">结算确认页面</h4>
                </div>
                <div class="modal-body">
                    确认结算？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="balance" class="btn btn-primary" data-loading-text="Loading..." autocomplete="off">确认</button>
                </div>
            </div>
        </div>
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
</div>

<!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>
<script>
    $(function () {
        $("#ListUnBalanceMoney").addClass("active");
    });
    $(function () {
        /*计算*/
        $("#calculate").click(function () {
            $.ajax({
                type : 'POST',
                url : '${basePath}/money/calculate.html',
                dataType : 'json',
                success : function(data) {
                    $("#result").html("");
                    var html = "";
                    html += "<p>总计："+data.totle+"元</p>";
                    html += "<p>平均："+data.avg+"元</p>";
                    for (var i=0;i<data.moneyLogList.length;i++){
                        html += "<p>" + data.moneyLogList[i].userName+"："+data.moneyLogList[i].totleMoney+"元&nbsp;&nbsp;&nbsp;"+data.moneyLogList[i].money +"元</p>";
                    }
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



        /*结算*/
        $("#balance").click(function () {
            $("#balanceModal").modal("hide");
            if ($("#result").html() == "") {
                $("#dataModalContent").html("<strong style='color: red'>请先计算！</strong>");
                $('#dataModal').modal('show');
                return ;
            }
            $.ajax({
                type : 'POST',
                url : '${basePath}/money/balance.html',
                dataType : 'json',
                success : function(data) {
                    if (data.status == 200) {
                        //$("#result").html("");
                        //$("tbody").html("");
                        $("#dataModalContent").html("<strong style='color: green'>" + data.msg + "</strong>");
                        $('#dataModal').modal('show');
                        $('#dataModal').on('hidden.bs.modal', function (e) {
                            location.href = "${basePath}/money/ListUnBalanceMoney.html";
                        });
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
        });
    });

    /*结算确认对话框*/
    function goBalance() {
        $("#balanceModal").modal("show");
    }

    /*删除对话框*/
    function goDeleteMoneyLog(id,name,usefor,money) {
        $("#deleteid").val(id);
        $("#deleteuserName").text(name);
        $("#deleteusefor").val(usefor);
        $("#deletemoney").val(money);
        $("#deletesubmit").on("click",function(){
            deleteModelMoneyLog(id);
        });

        $("#deleteModal").modal("show");
    }

    /*删除*/
    function deleteModelMoneyLog (id) {
        $.ajax({
            type : 'POST',
            url : '${basePath}/money/deleteMoneyLog.html',
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
                        location.href = "${basePath}/money/ListUnBalanceMoney.html";
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
    function goEditMoneyLog (uid,name,id,usefor,money) {
        $("#uid").val(uid);
        $("#id").val(id);
        $("#userName").text(name);
        $("#usefor").val(usefor);
        $("#money").val(money);
        $("#editModal").modal("show");
    }

    /*编辑*/
    function editMoneyLog () {
        $.ajax({
            type : 'POST',
            url : '${basePath}/money/editMoneyLog.html',
            dataType : 'json',
            data:{
                uid: $("#uid").val(),
                id: $("#id").val(),
                userName : $("#userName").text(),
                usefor : $("#usefor").val(),
                money:$("#money").val()
            },
            success : function(data) {
                $("#editModal").modal("hide");
                if (data.status == 200) {
                    $("#dataModalContent").html("<strong style='color: green'>" + data.msg + "</strong>");
                    $('#dataModal').modal('show');
                    $('#dataModal').on('hidden.bs.modal', function (e) {
                        location.href = "${basePath}/money/ListUnBalanceMoney.html";
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
