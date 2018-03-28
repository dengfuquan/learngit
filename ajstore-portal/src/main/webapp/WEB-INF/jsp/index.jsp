<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 如果直接输出jstl表达式，加入下面配置 -->
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>阿甲商城学子首页</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="Stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="Stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/slide.css" rel="Stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<jsp:include page="commons/header.jsp"></jsp:include>
<!-- banner部分-->
<div class="ck-slide">
    <ul class="ck-slide-wrapper">
        <li>
            <a href="/toItemInfo/10000028.html"><img src="/images/index/index_banner1.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="/toItemInfo/100000021.html"><img src="/images/index/index_banner2.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="/lookforward.html"><img src="/images/index/index_banner3.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="/toItemInfo/10000019.html"><img src="/images/index/index_banner4.png" alt=""></a>
        </li>
        <li style="display:none">
            <a href="/lookforward.html"><img src="/images/index/index_banner5.png" alt=""></a>
        </li>
    </ul>
    <a href="javascript:;" class="ctrl-slide ck-prev">上一张</a> <a href="javascript:;" class="ctrl-slide ck-next">下一张</a>
    <div class="ck-slidebox">
        <div class="slideWrap">
            <ul class="dot-wrap">
                <li class="current"><em>1</em></li>
                <li><em>2</em></li>
                <li><em>3</em></li>
                <li><em>4</em></li>
                <li><em>5</em></li>
            </ul>
        </div>
    </div>
</div>
<!-- 特推部分-->
<div id="product">
    <div class="product_1 lf">
        <div class="aside lf">
        	<a href="/toItemInfo/100000422.html">
            <img id="aside_image" src="/images/index/index_Sbanner_img1.png" alt=""/>
            </a>
        </div>
        <div class="text rt">
            <p class="ys1">ThinkPad<b class="ys2">s1</b></p>
            <p class="ys3">设计师独享高端配置</p>
        </div>
    </div>
    <div class="product_2 lf">
        <div class="aside lf">
        	<a href="/toItemInfo/10000043.html">
           		 <img id="aside_image2" src="/images/index/index_Sbanner_img2.png" alt=""/>
            </a>
        </div>
        <div class="text rt">
            <p class="ys1 ys4">乐尚书包<b class="ys2 ys4">Bag</b></p>
            <p class="ys3 ys4">给你满载而归的喜悦</p>
        </div>
    </div>
    <div class="product_3 lf">
        <div class="aside lf">
        	<a href="/lookforward.html">
	            <img id="aside_image3" src="/images/index/index_Sbanner_img3.png" alt=""/>
        	</a>
        </div>
        <div class="text rt">
            <p class="ys1">刀鱼焖排骨</p>
            <p class="ys3">吃饱了才有力气学习</p>
        </div>
    </div>
</div>
<!-- welcome部分-->
<div id="welcome">
    <a href="/lookforward.html">阅读更多>></a>
</div>
<!-- 学习用品-->
<div id="study">
    <p>学习用品<span>爱学习 享品质工作</span></p>
    <div class="study_left lf">
        <div id="product_parameters_main">
            <p>
            	<img src="/images/index/wenan.png" alt=""/>
           	</p>
            <h3 id="product_name_main">联想(Lenovo) 小新Air13 pro版</h3>
            <h3 id="product_preferential_main">限时特惠<b id="product_price_main">￥6688.00</b></h3>
            <p class="view_details"><a href="">查看详情</a></p>
        </div>
        <a href="/toItemInfo/10000037.html">
        <img id="study_product_main" src="/images/index/index_hot_study_img1.png" alt=""/>
        </a>
    </div>
    <div class="study_right rt">
        <div class="right_top">
            <div id="product_parameters_vice1">
                <h3 id="product_name_vice1">戴尔(Dell) 燃7000 不超15mm才敢叫轻薄</h3>
                <h3 id="product_preferential_vice1"><b id="product_price_vice1">￥6688.00</b></h3>
                <p class="view_details"><a href="/toItemInfo/10000007.html">查看详情</a></p>
            </div>
            <a href="/toItemInfo/10000007.html">
            <img id="study_product_vice1" src="/images/index/index_hot_study_img2.png" alt=""/>
             </a>
        </div>
        <div class="right_bottom">
            <div id="product_parameters_vice2">
                <h3 id="product_name_vice2">雅致布面年历本 张扬个性，点亮生活新时尚</h3>
                <h3 id="product_preferential_vice2"><b id="product_price_vice2">￥68.00</b></h3>
                <p class="view_details"><a href="/toItemInfo/10000002.html">查看详情</a></p>
                <img id="study_product_vice2" src="/images/index/index_hot_study_img3.png" alt=""/>
            </div>
        </div>
    </div>
</div>
<!-- 美食餐饮-->
<div id="gourmet">
    <p>美食餐饮<span>爱美食 享品质生活</span></p>
    <div class="bellows lf">
        <div class="bellows_item">
            <div class="bellows_header">
                <h3>红烧肉盖饭(吉野家)</h3>
            </div>
            <div class="">
                <div class="bellows_content">
                    <p>
                        红烧肉是热菜菜谱之一。以五花肉为制作主料，香甜松软。最好选用肥瘦相间的三层肉（五花肉）来做。红烧肉的烹饪技巧以砂锅为主，肥瘦相间，香甜松软，入口即化。红烧肉在我国各地流传甚广，是一道著名的大众菜肴,当然也是大家最喜欢的菜。
                    </p></div>
            </div>
        </div>
        <div class="bellows_item">
            <div class="bellows_header">
                <h3>春卷套餐(魏家凉皮)</h3>
            </div>
            <div class="bellows_content-wrapper" style="display: block">
                <div class="bellows_content">
                    <p>魏家凉皮怎么样?目前为止，魏家餐饮团队以“和谐、高效、奋进”为准则，坚持“全心全情、和谐共赢”的团队发展理念。带领魏家餐饮管理公司迈向新的高度。</p>
                </div>
            </div>
        </div>
        <div class="bellows_item">
            <div class="bellows_header active">
                <h3>营养海鲜汤(山西面食家)</h3>
            </div>
            <div class="bellows_content-wrapper">
                <div class="bellows_content">
                    <p>汤汁的香味在口里弥漫开来的新概念面条！粗粗的3mm面条中有凹凸，吃面条时也会跟着到嘴里来，使海鲜汤的味道和风味极大化。</p>
                </div>
            </div>
        </div>
    </div>
    <div class="food_detail rt">
        <div>
            <div class="food1 lf">
                <img src="/images/index/index_hot_food_img1.png" alt=""/>
                <h2>红烧肉盖饭套餐<span> (吉野家) </span></h2>
                <p>￥20.00</p>
                <a href="lookforward.html">立即订餐</a>
            </div>
            <div class="food1 lf">
                <img src="/images/index/index_hot_food_img2.png" alt="" id="food1"/>

                <h2>全素套餐<span> (魏家凉皮) </span></h2>

                <p>￥16.00</p>
                <a href="lookforward.html">立即订餐</a>
            </div>
            <div class="food1 lf">
                <img src="/images/index/index_hot_food_img3.png" alt=""/>

                <h2>营养海鲜汤<span> (山西面食家) </span></h2>

                <p>￥25.00</p>
                <a href="lookforward.html">立即订餐</a>
            </div>
        </div>
    </div>
</div>
<!-- 页面底部-->
<jsp:include page="commons/footer.jsp"></jsp:include>   
<script>
    //手风琴效果
    $(function () {
    	$('.bellows_item:nth-child(1)').addClass('bellows_open');
        $(".bellows_content-wrapper").hide();
        $(".bellows_header").click(function () {
            $(this).next().slideDown()
            $(this).parent(".bellows_item").addClass("bellows_open")
            $(".bellows_header").not(this).next().slideUp()
            $(".bellows_header").not(this).parent(".bellows_item").removeClass("bellows_open")
        });
        $('.bellows_header').on('click', 'h3', function (e) {
            $(e.target).removeClass('active').siblings('.active').addClass('active');
        });
    })
</script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>
<script>
    $('.ck-slide').ckSlide({
        autoPlay: true,//默认为不自动播放，需要时请以此设置
        dir: 'x',//默认效果淡隐淡出，x为水平移动，y 为垂直滚动
        interval:3000//默认间隔2000毫秒
    });
</script>
</body>
</html>










