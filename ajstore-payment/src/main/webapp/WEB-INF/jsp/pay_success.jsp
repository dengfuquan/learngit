<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>阿甲学子商城——支付页面</title>
    <link rel="stylesheet" href="/css/search.css"/>
    <link href="/css/payment.css" rel="Stylesheet"/>
    <link rel="stylesheet" href="/css/index.css"/>
    
</head>
<body>
<!-- 页面顶部-->
<jsp:include page="commons/header.jsp"></jsp:include>
<div id="navlist">
    <ul>
        <li class="navlist_gray_left"></li>
        <li class="navlist_gray">确认订单信息</li>
        <li class="navlist_gray_arrow"></li>
        <li class="navlist_gray">支付订单<b></b></li>
        <li class="navlist_gray_arrow"></li>
        <li class="navlist_blue">支付完成<b></b></li>
        <li class="navlist_blue_right"></li>
    </ul>
</div>
<div id="container">
    <div>
        <h1><i>支付结果</i><span class="rt">支付订单：${order.orderId} &nbsp;
            支付金额：<b>${order.payment}元</b></span></h1>
    </div>
    <div class="rightsidebar_box rt">
        <div class="pay_result">
            <img src="http://139.129.241.42/images/payment/pay/pay_succ.png" alt=""/>
            <p>支付成功</p>
            <span><a href="http://localhost:18888/order/toOrderInfo/${orderId}.html">查看订单状态？ </a><b><a href="http://localhost:18888/order/toMyOrder.html">查看订单&gt;&gt;</a></b></span>
            <br/>
            阿甲学子商城不会以系统异常、订单升级为由，要求你点击任何链接进行退款操作！
        </div>
    </div>
</div>
<!-- 页面底部-->
<jsp:include page="commons/footer.jsp"></jsp:include>

</body>
</html>