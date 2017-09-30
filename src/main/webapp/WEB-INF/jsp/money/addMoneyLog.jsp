<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="/WEB-INF/jsp/common/_header.jsp"/>
<body>
<!-- 导航栏 -->
<jsp:include page="/WEB-INF/jsp/common/_navbar.jsp"/>
<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
            <div class="form-group">
                <label for="money">价格</label>
                <input type="number" class="form-control" id="_money" name="_money" placeholder="价格" required>
            </div>
            <div class="form-group">
                <label for="useFor">用途</label>
                <input type="text" class="form-control" id="_useFor" name="_useFor" placeholder="用途" required>
            </div>
            <%--<div class="form-group">
                <label>用途</label>
                <select class="form-control" name="use">
                    <option value="1">买菜</option>
                    <option value="0">其他</option>
                </select>
            </div>--%>

            <button type="button" id="_submit" class="btn btn-primary center-block" data-toggle="modal" data-target="#submitModal">提交</button>
    </div>


    <!-- 确认页面Modal -->
    <div class="modal fade" id="submitModal" tabindex="-1" role="dialog" aria-labelledby="submitModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">确认页面</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="money">价格</label>
                        <input type="number" class="form-control" id="money" name="money" placeholder="价格" required disabled>
                    </div>
                    <div class="form-group">
                        <label for="useFor">用途</label>
                        <input type="text" class="form-control" id="useFor" name="useFor" placeholder="用途" required disabled>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="submit" class="btn btn-primary">保存</button>
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




</div> <!-- /container -->

<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>
<script>
    $(function() {
        $("#_submit").click(function(){
            var money = $("#_money").val();
            var useFor = $("#_useFor").val();
            if ((money == null || money == '') && (useFor == null || useFor == '')) {
                $('#dataModalContent').html("<strong style='color: red'>请填写完整信息！</strong>");
                $('#dataModal').modal('show');
                $('#submitModal').modal('hide');
                return false;
            }
            $("#money").val(money);
            $("#useFor").val(useFor);
        });

        $("#submit").click(function(){
            var m = $("#money").val();
            var u = $("#useFor").val();
            var data = {
                money:m,
                useFor:u
            };
            $.ajax({
                type : 'POST',
                url : '${basePath}/money/addMoneyLog.html',
                dataType : 'json',
                data : data,
                success : function(data) {
                    if (data.status == 200) {
                        $("#dataModalContent").html("<strong style='color: green'>"+data.msg+"</strong>");
                        $('#submitModal').modal('hide');
                    } else {
                        $("#dataModalContent").html("<strong style='color: red'>"+data.msg+"</strong><br/>" +data.data);
                    }
                    $('#dataModal').modal('show');
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
