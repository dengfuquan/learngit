package com.ajiatech.controller;

import java.util.Arrays;
import java.util.List;
import org.eclipse.jdt.internal.compiler.ast.NumberLiteral;
import org.eclipse.jdt.internal.compiler.flow.LoopingFlowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ajiatech.pojo.AjiaOrder;
import com.ajiatech.pojo.AjiaOrderVo;
import com.ajiatech.service.OrderService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/order")
public class OrderController {
	//值来之src/main/resoruces/properties/config.properties
	@Value("${pageSize}")
	int pageSize;
	@Autowired
	OrderService orderService;
	//href=/order/detlete.html?orderid=
	//href=/order/delete/orderid.html  restful
	@RequestMapping("/{orderId}.html")
	public ModelAndView deleteOrder
	(@PathVariable(name="orderId")String orderId,@RequestParam(name="status",defaultValue="all")String status) throws Exception
		{
		ModelAndView modelAndView=new ModelAndView();
		AjiaOrder ajiaOrder=orderService.selectByOrderId(orderId);
		if (ajiaOrder!=null && ajiaOrder.getUserId()==14L)
		{
			orderService.deleteByOrderId(orderId);
		}
		modelAndView.setViewName("redirect:/order/myOrder.html?status="+status);
		return modelAndView;
	}

	@RequestMapping("/cancelOrder.html")
	public ModelAndView cancelOrder(@RequestParam(name = "orderId", required = true) String orderId,
			@RequestParam(name = "status", defaultValue = "all", required = false) String status,
			@RequestParam(name="currentPage",defaultValue="1",required=false)int currentPage
			) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		// 查找订单
		AjiaOrder ajiaOrder = orderService.selectByOrderId(orderId);
		if (ajiaOrder != null) {
			// 判断是不是当前用户的
			if (ajiaOrder.getUserId() == 14L) {
				// 更新状态
				orderService.updateStatusByOrderid(orderId, 8);
			}

		}
		modelAndView.setViewName("redirect:/order/myOrder.html?status=" + status+"&currentPage="+currentPage);

		return modelAndView;
	}
	@RequestMapping("/toOrderInfo.html")
	public ModelAndView toOrderInfo(String orderId) throws Exception
	{
		ModelAndView modelAndView=new ModelAndView();
		//调service得订单信息 
		AjiaOrderVo ajiaOrderVo=orderService.selectVoByOrderId(orderId);
		
		//判断订单是不是当前用户的
		if (ajiaOrderVo!=null && ajiaOrderVo.getAjiaOrder().getUserId()==14L)
		{
			modelAndView.addObject("ajiaOrderVo", ajiaOrderVo);
			modelAndView.setViewName("orderInfo");
		}else{
			modelAndView.setViewName("redirect:/order/myOrder.html");
		}
		return modelAndView;
	}

	@RequestMapping("/myOrder.html")
	public ModelAndView myOrder(@RequestParam(name = "status", required = false, defaultValue = "all") String strStatus,
			@RequestParam(defaultValue = "1", name = "currentPage", required = false) int currentPage)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		int status = 0;
		switch (strStatus) {
		case "waitPay":
			status = 1;
			break;
		case "waitReceive":
			status = 5;
			break;
		case "waitAssess":
			status = 6;
			break;
		case "all":
			status = 0;
			break;

		default:
			break;
		}
		// List<AjiaOrderVo> voList=orderService.selectByUserIdStatus(14L,
		// status);
		PageInfo<AjiaOrderVo> pageInfoList = orderService.selectByUserIdStatusForPage(14L, status, currentPage, pageSize);

		modelAndView.addObject("voList", pageInfoList.getList());
		modelAndView.addObject("status", strStatus);
		modelAndView.setViewName("myOrder");
		modelAndView.addObject("pageCount", pageInfoList.getPages());
		modelAndView.addObject("currentPage", pageInfoList.getPageNum());
		return modelAndView;
	}

	@RequestMapping("/saveOrder")
	public ModelAndView saveOrder(Long addId, Long[] itemId) throws Exception {
		// 把数组转成list
		List<Long> itemIdList = Arrays.asList(itemId);
		AjiaOrder ajiaOrder = orderService.saveOrder(14L, addId, itemIdList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ajiaOrder", ajiaOrder);
		modelAndView.setViewName("payment");
		return modelAndView;

	}
	@RequestMapping("/toPayment")
	public ModelAndView toPayment(String orderId) throws Exception {
		

		ModelAndView modelAndView = new ModelAndView();
		AjiaOrder ajiaOrder=orderService.selectByOrderId(orderId);
		modelAndView.addObject("ajiaOrder", ajiaOrder);
		modelAndView.setViewName("payment");
		return modelAndView;

	}
}
