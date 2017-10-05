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
                <label for="originPass">原始密码</label>
                <input type="number" class="form-control" id="originPass" name="originPass" placeholder="原始密码">
            </div>
            <div class="form-group">
                <label for="newPass">新密码</label>
                <input type="text" class="form-control" id="newPass" name="newPass" placeholder="新密码" required>
            </div>
            <div class="form-group">
                <label for="newPassConfirm">重复新密码</label>
                <input type="text" class="form-control" id="newPassConfirm" name="newPassConfirm" placeholder="重复新密码" required>
            </div>


            <button type="button" id="submit" class="btn btn-primary center-block">提交</button>
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
        $("#resetPassword").addClass("active");
        $(".setting").addClass("active");
    });

    $(function() {
        $("#submit").click(function(){
            var originPass = $("#originPass").val();
            var newPass = $("#newPass").val();
            var newPassConfirm = $("#newPassConfirm").val();
            var data = {
                originPass:originPass,
                newPass:newPass,
                newPassConfirm:newPassConfirm
            };
            $.ajax({
                type : 'POST',
                url : '${basePath}/setting/doResetPassword.html',
                dataType : 'json',
                data : data,
                success : function(data) {
                    if (data.status == 200) {
                        $("#dataModalContent").html("<strong style='color: green'>"+data.msg+"</strong>");
                    } else {
                        $("#dataModalContent").html("<strong style='color: red'>"+data.msg+"</strong><br/>");
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
