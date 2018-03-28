<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 如果直接输出jstl表达式，加入下面配置 -->
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>确认订单</title>
    <link href="../css/orderConfirm.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="Stylesheet"/>
    </head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>

<div id="navlist">
    <ul>
        <li class="navlist_blue_left"></li>
        <li class="navlist_blue">确认订单信息</li>
        <li class="navlist_blue_arro"><canvas id="canvas_blue" width="20" height="38"></canvas>
        </li>
        <li class="navlist_gray">支付订单<b></b></li>
        <li class="navlist_gray_arro"><canvas id="canvas_gray" width="20" height="38"></canvas>
        </li>
        <li class="navlist_gray">支付完成<b></b></li>
        <li class="navlist_gray_right"></li>
    </ul>
</div>
<!--订单确认-->
<div id="container" class="clear">
    <!--收货地址-->
    <div class="adress_choice">
        <p>收货人信息<span class="rt" id="choose">新增收货地址</span></p>
        <div id="addresId1" class="base_select">
            <i class="address_name">
              正在加载默认地址
            </i>
            <i class="user_address">
              
            </i>
            <i class="user_site rt">
                设为默认地址
            </i>
        </div>
        
        
        <a id="more" href="javascript:void(0);">
            更多地址 &gt;&gt;
        </a>
    </div>
    <!-- 商品信息-->
    <form id="itemform" name="" method="post" action="/order/saveOrder.html">
         <input type="hidden" id="addIdInput" name=addId value="">
        
        <div class="product_confirm">
            <p>确认商品信息</p>
            <ul class="item_title">
                <li class="p_info">商品信息</li>
                <li class="p_price">单价(元)</li>
                <li class="p_count">数量</li>
                <li class="p_tPrice">金额</li>
            </ul>
            <c:forEach items="${voList}" var="vo">
            <input type="hidden" name="itemId" value="${vo.ajiaItem.id}">
            <ul class="item_detail">
                <li class="p_info">
                    <b><img src="../images/orderConfirm/product_simg1.png"/></b>

                    <b class="product_name lf">
                    ${vo.ajiaItem.brand}&nbsp;&nbsp;${vo.ajiaItem.model}&nbsp;&nbsp;${vo.ajiaItem.title}
                    </b>
                    <br/>
                <c:forEach items="${vo.paramsList }" var="itemParam">
                <span class="product_color ">
                   ${itemParam.key }：${itemParam.values.get(0) }
                </span>
                </c:forEach>
                </li>
                <li class="p_price">
                    <i>阿甲专属价</i>
                    <br/>
                    <span class="pro_price">￥<span class="ppp_price">${vo.ajiaItem.price }</span></span>
                </li>
                <li class="p_count">X<span>${vo.ajiaCartItem.num }</span></li>
                <li class="p_tPrice"><span>￥${vo.ajiaCartItem.num*vo.ajiaItem.price }</span></li>
            </ul>
        </c:forEach>
        </div>
    </form>
    <!-- 结算条-->
    <div id="count_bar">
        <span class="go_cart"><a href="#" >&lt;&lt;返回购物车</a></span>
        <span class="count_bar_info">已选<b  id="count"> ${$totalNumber } </b>件商品&nbsp;&nbsp;合计(不含运费):<b class="zj"></b> <input type="hidden" name="Payment" value=""/>${totalPrice}元</span>
        <span class="go_pay">确认并付款</span>
    </div>
</div>
<!--标题栏-->
<div class="modal" style="display:none">
        <!--收货人信息填写栏-->     
        <div class="rs_content rs_content_1">
        	<p class="cha">×</p>
        	<form method="post" action="">
	            <!--收货人姓名-->
	            <div class="recipients">
	                <span class="red">*</span><span class="kuan">收货人：</span><input type="text" name="receiverName" id="receiverName"/>
	            </div>
	            <!--收货人所在城市等信息-->
	            <div data-toggle="distpicker" class="address_content">
					 <span class="red">*</span><span class="kuan">省&nbsp;&nbsp;份：</span><select data-province="---- 选择省 ----" id="receiverState"></select>
					  城市：<select data-city="---- 选择市 ----" id="receiverCity"></select>
					  区/县：<select data-district="---- 选择区 ----" id="receiverDistrict"></select>
				</div> 
	            <!--收货人详细地址-->
	            <div class="address_particular">
	                <span class="red">*</span><span class="kuan">详细地址：</span><textarea name="receiverAddress" id="receiverAddress" cols="60" rows="3" placeholder="建议您如实填写详细收货地址"></textarea>
	            </div>
	            <!--收货人地址-->
	            <div class="address_tel">
	                <span class="red">*</span><span class="kuan">手机号码：</span><input type="tel" id="receiverMobile" name="receiverMobile"/>固定电话：<input type="text" name="receiverPhone" id="receiverPhone"/>
	            </div>
	            <!--邮政编码-->
	            <div class="address_postcode">
	                <span class="red">&nbsp;</span class="kuan"><span>邮政编码：</span>&nbsp;<input type="text" name="receiverZip"/>
	            </div>
	            <!--地址名称-->
	            <div class="address_name">
	                <span class="red">&nbsp;</span class="kuan"><span>地址名称：</span>&nbsp;<input type="text" id="addressName" name="addressName"/>如：<span class="sp">家</span><span class="sp">公司</span><span class="sp">宿舍</span>
	            </div>
	            <!--保存收货人信息-->
	            <div class="save_recipient">
	                保存收货人信息
	            </div>
	
    		</form>
    		</div>
</div>
<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>
        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>
        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>
        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>
        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<div class="foot_bj">
    <div id="foot">
        <div class="lf">
            <p class="footer1"><img src="../images/footer/logo.png" alt="" class=" footLogo"/></p>
            <p class="footer2"><img src="../images/footer/footerFont.png"alt=""/></p>
            
        </div>
        <div class="foot_left lf" >
            <ul>
                <li><a href="#"><h3>买家帮助</h3></a></li>
                <li><a href="#">新手指南</a></li>
                <li><a href="#">服务保障</a></li>
                <li><a href="#">常见问题</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>商家帮助</h3></a></li>
                <li><a href="#">商家入驻</a></li>
                <li><a href="#">商家后台</a></li>
            </ul>
            <ul>
                <li><a href="#"><h3>关于我们</h3></a></li>
                <li><a href="#">关于阿甲</a></li>
                <li><a href="#">联系我们</a></li>
                <li>
                    <img src="../images/footer/wechat.png" alt=""/>
                    <img src="../images/footer/sinablog.png" alt=""/>
                </li>
            </ul>
        </div>
        <div class="service">
            <p>阿甲商城客户端</p>
            <img src="../images/footer/ios.png" class="lf">
            <img src="../images/footer/android.png" alt="" class="lf"/>
        </div>
        <div class="download">
            <img src="../images/footer/erweima.png">
        </div>
		<!-- 页面底部-备案号 #footer -->
            <div class="record">
                &copy;2017 阿甲集团有限公司 版权所有 京ICP证xxxxxxxxxxx
            </div>
    </div>
</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script>
    var html=0;
    var total=0;
    $(function(){
    	
    	//加载默认地址
    	$.ajax({
    		url:"/cart/getDefaultAddress.html",
    		type:"post",
    		dataType:"json",
    		success:function(response)
    		{
    			if (response.status==200)
    				{
    				var ajiaShipping=response.data;
    				//保存地址编号
    				var $addIdInput=$("#addIdInput");
    				$addIdInput.val(ajiaShipping.addId);
    				$(".address_name").html(ajiaShipping.receiverName+"&nbsp;&nbsp;"+ajiaShipping.receiverPhone);
    				$(".user_address").html(ajiaShipping.receiverAddress);

    				}else
    					{
        				$(".address_name").html("加载默认地址失败");

    					}
    		},
    		error:function(e)
    		{
    			console.log(e);
    		}
    	});
        $(".item_detail").each(function() {
            html+=1;
            var p=parseFloat($(this).children('.p_price').children('.pro_price').children('.ppp_price').html());
            console.log(p);
            var sl=parseFloat($(this).children('.p_count').children('span').html());
            var xj=parseFloat(p*sl).toFixed(2);
            console.log("xiaoji"+xj);
            $(this).children('.p_tPrice').children('span').html(xj);
            total+=xj-0;
        })
        console.log("zongji"+total);
        $("#count").html(html)-0;
        $('.zj').html(total.toFixed(2))-0;
        var input_zj=parseFloat($('.zj').html()).toFixed(2);
        $('#payment').val(input_zj);
    })
</script>
<script>
    $(".go_pay").click(function () {
        //location.href = "payment.html";
        //location.href="/order/saveOrder.html?addId=1&itemId=23,28";
        //用代码提交form
        $("#itemform").submit();
    })
</script>
<script>
    var canvas=document.getElementById("canvas_gray");
    var cxt=canvas.getContext("2d");
    var gray = cxt.createLinearGradient (10, 0, 10, 38);
    gray.addColorStop(0, '#f5f4f5');
    gray.addColorStop(1, '#e6e6e5');
    cxt.beginPath();
    cxt.fillStyle = gray;
    cxt.moveTo(20,19);
    cxt.lineTo(0,38);
    cxt.lineTo(0,0);
    cxt.fill();
    cxt.closePath();
</script>
<script>
    var canvas=document.getElementById("canvas_blue");
    var cxt=canvas.getContext("2d");
    var blue = cxt.createLinearGradient (10, 0, 10, 38);
    blue.addColorStop(0, '#27b0f6');
    blue.addColorStop(1, '#0aa1ed');
    cxt.beginPath();
    cxt.fillStyle = blue;
    cxt.moveTo(20,19);
    cxt.lineTo(0,38);
    cxt.lineTo(0,0);
    cxt.fill();
    cxt.closePath();
</script>
<script src="../js/distpicker.data.js"></script>
<script src="../js/distpicker.js"></script>
<script>
	$("#choose").click(function(){
		$(".modal").show();
	})
	$(".cha").click(function(){
		$(".modal").hide();
	})
	
	$("#more").click(function(){
		if($(this).hasClass("upup")){
			$("#addresId2").hide();
			$("#addresId3").hide();
			$("#more").html("更多地址>>");
			$(this).removeClass("upup");
		}else{
			$("#addresId2").show();
			$("#addresId3").show();
			$("#more").html("收起地址>>");
			$("#more").addClass("upup");
		}
	})
</script>
<script>
	$(document).on("mouseover",".base",function(){
		$(this).find("i:eq(2)").show();
	})
	$(document).on("mouseout",".base",function(){
		$(this).find("i:eq(2)").hide();
	})
	$(".user_site").click(function(){
		$(this).parent().attr("class","base_select");
		$(this).parent().siblings().attr("class","base");
		$(this).hide();
	})
</script>
</body>
</html>

