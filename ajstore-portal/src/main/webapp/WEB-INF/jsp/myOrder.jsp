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
<title>我的订单 - 阿甲学子商城</title>
<link href="../css/myOrder.css" rel="Stylesheet" />
<link href="../css/header.css" rel="Stylesheet" />
<link href="../css/footer.css" rel="Stylesheet" />
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>

	<!-- 我的订单导航栏-->
	<div id="nav_order">
		<ul>
			<li><a href="">首页<span>&gt;</span>订单管理
			</a></li>
		</ul>
	</div>
	<!--我的订单内容区域 #container-->
	<div id="container" class="clearfix">
		<!-- 左边栏-->
		<div id="leftsidebar_box" class="lf">
			<div class="line"></div>
			<dl class="my_order">
				<dt onClick="changeImage()">
					我的订单 <img src="../images/myOrder/myOrder2.png">
				</dt>
				<dd class="first_dd">
					<a href="/order/myOrder.html?status=all" ${status=="all"?"style='color:#0aa1ed'":"" }>全部订单</a>
				</dd>
				<dd>
					<a href="/order/myOrder.html?status=waitPay" ${status=="waitPay"?"style='color:#0aa1ed'":"" }> 待付款 <span>
							<!--待付款数量-->
					</span>
					</a>
				</dd>
				<dd>
					<a href="/order/myOrder.html?status=waitReceive" ${status=="waitReceive"?"style='color:#0aa1ed'":"" }> 待收货 <span>
							<!--待收货数量-->1
					</span>
					</a>
				</dd>
				<dd>
					<a href="/order/myOrder.html?status=waitAssess" ${status=="waitAssess"?"style='color:#0aa1ed'":"" }> 待评价<span>
							<!--待评价数量-->
					</span>
					</a>
				</dd>
				<dd>
					<a href="#">退货退款</a>
				</dd>
			</dl>
			<dl class="footMark">
				<dt onClick="changeImage()">
					我的优惠卷<img src="../images/myOrder/myOrder1.png">
				</dt>
			</dl>
			<dl class="address">
				<dt>
					收货地址<img src="../images/myOrder/myOrder1.png">
				</dt>
				<dd>
					<a href="addressAdmin.html">地址管理</a>
				</dd>
			</dl>
			<dl class="count_managment">
				<dt onClick="changeImage()">
					帐号管理<img src="../images/myOrder/myOrder1.png">
				</dt>
				<dd class="first_dd">
					<a href="personage.html">我的信息</a>
				</dd>
				<dd>
					<a href="personal_password.html">安全管理</a>
				</dd>
			</dl>
		</div>
		-->
		<!-- 右边栏-->
		<div class="rightsidebar_box rt">
			<!-- 商品信息标题-->
			<table id="order_list_title" cellpadding="0" cellspacing="0">
				<tr>
					<th width="345">商品</th>
					<th width="82">单价（元）</th>
					<th width="50">数量</th>
					<th width="82">售后</th>
					<th width="100">实付款（元）</th>
					<th width="90">交易状态</th>
					<th width="92">操作</th>
				</tr>
			</table>
			<!-- 订单列表项 -->
			<c:forEach items="${voList}" var="ajiaOrderVo">
				<div id="orderItem">
					<p class="orderItem_title">
						<span id="order_id"> &nbsp;&nbsp;订单编号: <a href="#">${ajiaOrderVo.ajiaOrder.orderId }</a>
						</span> &nbsp;&nbsp;成交时间：
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm"
							value="${ajiaOrderVo.ajiaOrder.createTime}" />
						&nbsp;&nbsp; <span> 
						<a href="#" class="servie"> 联系客服<img
								src="../images/myOrder/kefuf.gif" />
						</a>
						<c:if test="${ajiaOrderVo.ajiaOrder.status==1}">
						<a href="javascript:;" onClick="confirm('你真的要取消订单吗?')?cancelOrder('${ajiaOrderVo.ajiaOrder.orderId}'):''" class="servie"> 取消	</a>
						</c:if>
						<c:if test="${ajiaOrderVo.ajiaOrder.status==8}">
						<a href="javascript:;" onClick="confirm('你真的要删除订单吗')?deleteOrder('${ajiaOrderVo.ajiaOrder.orderId}'):''" class="servie"> 删除	</a>
						</c:if>
						</span>
					</p>
				</div>
				<c:forEach items="${ajiaOrderVo.ajiaOrderItemList}"
					var="ajiaOrderItem">
					<div id="orderItem_detail">
						<ul>
							<li class="product"><b><a href="#"><img width=50
										height=50 src="${ajiaOrderItem.picPath}" /></a></b> <b
								class="product_name lf"> <a href="">${ajiaOrderItem.title}</a>
									<br />
							</b> <c:forEach items="${ajiaOrderItem.paramList}" var="itemParam">
									<b class="product_color ">
										${itemParam.key}:${itemParam.values[0]} </b>
								</c:forEach></li>
							<li class="unit_price">专属价 <br /> ￥${ajiaOrderItem.price}
							</li>
							<li class="count">${ajiaOrderItem.num}件</li>
							<li class="sale_support">退款/退货 <br /> 我要维权
							</li>
							<li class=" payments_received">
								￥${ajiaOrderItem.num*ajiaOrderItem.price}</li>
							<li class="trading_status">
							<c:if
									test="${ajiaOrderVo.ajiaOrder.status==1}">
                      待付款
                          <br />
								</c:if> 
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==2}">
                      已付款
                          <br />
								</c:if>
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==3}">
                      待发货
                          <br />
								</c:if>
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==4}">
                     已发货
                          <br />
								</c:if>
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==5}">
                      待收货
                          <br />
								</c:if>
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==6}">
                      待评价
                          <br />
								</c:if>
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==7}">
                      交易成功
                          <br />
								</c:if>
									<c:if
									test="${ajiaOrderVo.ajiaOrder.status==8}">
                      交易关闭
                          <br />
								</c:if>
								
								<a href="/order/toOrderInfo.html?orderId=${ajiaOrderVo.ajiaOrder.orderId}">订单详情</a> <br /> <a href="#"
								class="view_logistics">查看物流</a></li>
							<li class="operate">
							<c:if test="${ajiaOrderVo.ajiaOrder.status==1}">
							<a href="/order/toPayment.html?orderId=${ajiaOrderVo.ajiaOrder.orderId}">支付款</a>
							</c:if>
							<a href="#">确认收货</a></li>
						</ul>
					</div>
				</c:forEach>
			</c:forEach>
			<!--分页器-->
			<div class="tcdPageCode"></div>

		</div>
	</div>

	<!--<iframe src="order_status.html" width="1000" height=500""></iframe>-->
	<!-- 品质保障，私人定制等-->
	<div id="foot_box">
		<div class="icon1 lf">
			<img src="../images/footer/icon1.png" alt="" />

			<h3>品质保障</h3>
		</div>
		<div class="icon2 lf">
			<img src="../images/footer/icon2.png" alt="" />

			<h3>私人定制</h3>
		</div>
		<div class="icon3 lf">
			<img src="../images/footer/icon3.png" alt="" />

			<h3>学员特供</h3>
		</div>
		<div class="icon4 lf">
			<img src="../images/footer/icon4.png" alt="" />

			<h3>专属特权</h3>
		</div>
	</div>
	<!-- 页面底部-->
	<div class="foot_bj">
		<div id="foot">
			<div class="lf">
				<p class="footer1">
					<img src="../images/footer/logo.png" alt="" class=" footLogo" />
				</p>
				<p class="footer2">
					<img src="../images/footer/footerFont.png" alt="" />
				</p>

			</div>
			<div class="foot_left lf">
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
					<li><a href="#">关于达内</a></li>
					<li><a href="#">联系我们</a></li>
					<li><img src="../images/footer/wechat.png" alt="" /> <img
						src="../images/footer/sinablog.png" alt="" /></li>
				</ul>
			</div>
			<div class="service">
				<p>达内商城客户端</p>
				<img src="../images/footer/ios.png" class="lf"> <img
					src="../images/footer/android.png" alt="" class="lf" />
			</div>
			<div class="download">
				<img src="../images/footer/erweima.png">
			</div>
			<!-- 页面底部-备案号 #footer -->
			<div class="record">&copy;2017 阿甲集团有限公司 版权所有 京ICP证xxxxxxxxxxx</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/index.js"></script>
<script src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/order.js"></script>
<script type="text/javascript">
//分页部分
//重新请求新页时，把订单状态带给服务器
var status="${status}"
if (!status){status="all"}
var $div=$(".tcdPageCode");
var pageCount="${pageCount}";
var currentPage="${currentPage}"
$div.createPage({
	pageCount:pageCount,
	current:currentPage,
	backFn:function(currentPage){
		
		window.location.href="/order/myOrder.html?status="+status+"&currentPage="+currentPage;
	}
	});
function cancelOrder(orderId)
{
	location.href="/order/cancelOrder.html?orderId="+orderId+"&status="+status+"&currentPage="+currentPage;
	}
function deleteOrder(orderId)
{
	location.href="/order/"+orderId+".html?status="+status;
	}
</script>





</html>