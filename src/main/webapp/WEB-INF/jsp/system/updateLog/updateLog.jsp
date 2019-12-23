<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="/WEB-INF/jsp/common/_header.jsp"/>
<style>
    .date{

        margin-top: 10px;
        margin-bottom: 0px;
    }
    .updateLog{
        padding-left: 10px;
    }
    .detail{
        color: #555555;
        font-size:13px;
        padding-left: 20px;
    }
</style>
<body>
<!-- 导航栏 -->
<jsp:include page="/WEB-INF/jsp/common/_navbar.jsp"/>
<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h3 class="text-center" ><strong >更新日志</strong></h3>
        <h4 class="text-center">项目地址：https://github.com/huyang7902/money</h4>

        <p class="date">时间：2019-12-23</p>
        <div class="updateLog">
            更新功能如下：<br>
            一、切换为springboot<br>
        </div>

        <p class="date">时间：2017-10-5</p>
        <div class="updateLog">
            更新功能如下：<br>
            一、可以查看结算历史记录<br>
            二、优化没有未结算账单不能结算<br>
            三、把所有表格变为响应式，优化弹框显示<br>
        </div>
        <p class="date">时间：2017-10-4</p>
        <div class="updateLog">
            系统初次发布。前端框架：Bootstrap；后端框架：SSM<br>
            功能如下：<br>
            一、添加记账功能<br>
            二、未结算列表<br>
            <div class="detail">
                1、计算功能<br>
                2、结算功能<br>
            </div>
            三、历史账单记录功能(未结算)<br>
            <div class="detail">
            1、根据月份进行搜索<br>
            2、计算每个人的账单总计<br>
            </div>
            四、个人记账功能<br>
            <div class="detail">
            1、添加<br>
            2、查看历史记录<br>
            3、计算历史账单总计<br>
            </div>
            五、设置功能<br>
            <div class="detail">
            1、修改个人密码(md5加密)<br>
            </div>
            六、用户登录<br>
            七、用户登出<br>
        </div>


    </div>

</div> <!-- /container -->


<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
</body>
<script>
    $(function () {
        $("#updateLog").css("color","#fff")
    });
</script>
</html>
