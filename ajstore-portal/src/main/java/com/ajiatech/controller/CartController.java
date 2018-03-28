package com.ajiatech.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jdt.internal.compiler.ast.NumberLiteral;
import org.eclipse.jdt.internal.compiler.flow.LoopingFlowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ajiatech.pojo.AjiaCartItem;
import com.ajiatech.pojo.AjiaCartItemVo;
import com.ajiatech.pojo.AjiaResult;
import com.ajiatech.pojo.AjiaShipping;
import com.ajiatech.pojo.AjiaUserResult;
import com.ajiatech.service.CartService;
import com.ajiatech.service.OrderService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	//�������ɾ��
//	@Autowired
	//OrderService OrderService;

	@RequestMapping("/cartToOrder.html")
	public ModelAndView cartToOrder(String itemIds) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if (itemIds != null) {
			String[] array = itemIds.split(",");
			ArrayList<Long> idList = new ArrayList<>();
			for (String id : array) {
				idList.add(Long.parseLong(id));
			}
			List<AjiaCartItemVo> voList = cartService.selectByItemIds(idList, 14L);
			modelAndView.addObject("voList", voList);

			// ���ܼۿ�����jsp,controller,service�м���
			// �����service����
			double totalPrice = 0;
			double totalNumber = 0;
			for (AjiaCartItemVo vo : voList) {
				totalNumber = totalNumber + vo.getAjiaCartItem().getNum();
				totalPrice = totalPrice + (vo.getAjiaCartItem().getNum() * vo.getAjiaItem().getPrice());
			}
			modelAndView.addObject("totalNumber", totalNumber);
			modelAndView.addObject("totalPrice", totalPrice);
			//���Զ��������������Ƿ񱣴�

			//OrderService.saveOrder(14L, 1L, idList);

		}
		modelAndView.setViewName("orderConfirm");
		return modelAndView;
	}

	@RequestMapping("/getDefaultAddress.html")
	@ResponseBody
	public AjiaResult getDefaultAddress() throws Exception {
		AjiaShipping ajiaShipping = cartService.getDefaultAddress(14L);
		if (ajiaShipping != null) {
			// status=200,msg=ok,data=ajiaShipping
			return AjiaResult.ok(ajiaShipping);
		}
		return new AjiaResult(500, "ʧ��", null);

	}

	@RequestMapping("/insert.html")
	@ResponseBody
	public AjiaResult insert(long itemId, int num) throws Exception {
		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setItemId(itemId);
		ajiaCartItem.setNum(num);
		ajiaCartItem.setUserId(14L);
		ajiaCartItem.setCreated(new Date());
		ajiaCartItem.setUpdated(new Date());
		ajiaCartItem.setStatus(1);
		boolean isSuccess = cartService.insert(ajiaCartItem);
		// ����
		// List<AjiaCartItemVo> voList=cartService.selectByUserId(14L);
		if (isSuccess) {
			return AjiaResult.ok();
		}
		return new AjiaResult(500, "ʧ��", null);
	}

	@RequestMapping("/toCart.html")
	public ModelAndView toCart(HttpServletRequest request) throws Exception {
		
		AjiaUserResult ajiaUserResult=(AjiaUserResult) request.getAttribute("ajiaUserResult");
		long userId=ajiaUserResult.getData().getId();
		List<AjiaCartItemVo> voList = cartService.selectByUserId(userId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("voList", voList);
		// web-inf/jsp/cart.jsp
		modelAndView.setViewName("cart");
		return modelAndView;

	}

	// ?ids=28,31,32
	@RequestMapping("/deleteByIds")
	public ModelAndView deleteByids(String ids) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		// �и��ַ���
		if (ids != null) {
			String[] array = ids.split(",");
			ArrayList<Long> idList = new ArrayList<>();
			for (String id : array) {
				idList.add(Long.parseLong(id));
			}
			boolean isSuccess = cartService.deleteByIds(idList, 14L);
			if (isSuccess) {
				modelAndView.setViewName("redirect:/cart/toCart.html");
			} else {
				modelAndView.addObject("errorInfo", "ʧ��");
				List<AjiaCartItemVo> voList = cartService.selectByUserId(14L);
				modelAndView.addObject("voList", voList);
				modelAndView.setViewName("cart");
			}
		}
		// �������е����ݷŵ�list
		return modelAndView;
	}

	@RequestMapping("/deleteById.html")
	public ModelAndView deleteById(long itemId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setItemId(itemId);
		ajiaCartItem.setUserId(14L);
		ajiaCartItem.setStatus(1);
		boolean isSuccess = cartService.deleteById(ajiaCartItem);

		if (isSuccess) {
			// ֪ͨ������ض���ȡ��������
			modelAndView.setViewName("redirect:/cart/toCart.html");
		} else {
			modelAndView.addObject("errorInfo", "ʧ��");
			// ת����web-inf/jsp/cart.jsp
			List<AjiaCartItemVo> voList = cartService.selectByUserId(14L);
			modelAndView.addObject("voList", voList);

			modelAndView.setViewName("cart");

		}

		return modelAndView;

	}

	// ?itemId=1&num=6
	@RequestMapping("/changeCartNum.html")
	@ResponseBody
	public AjiaResult changeCartNum(long itemId, int num) throws Exception {
		AjiaCartItem ajiaCartItem = new AjiaCartItem();
		ajiaCartItem.setUserId(14L);
		ajiaCartItem.setItemId(itemId);
		ajiaCartItem.setNum(num);
		boolean isSuccess = cartService.changeCartNum(ajiaCartItem);
		if (isSuccess) {
			return AjiaResult.ok();
		}
		return new AjiaResult(500, "ʧ��", null);
	}

}
