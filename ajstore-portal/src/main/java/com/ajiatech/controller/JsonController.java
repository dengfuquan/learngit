package com.ajiatech.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajiatech.pojo.AjiaOrder;
import com.ajiatech.pojo.AjiaOrderItem;
import com.ajiatech.pojo.AjiaUser;

@Controller
public class JsonController {
	@RequestMapping("/getJson4")
	@ResponseBody
	public ArrayList<AjiaOrder> getJson4() {
		ArrayList<AjiaOrder> list = new ArrayList<>();
		AjiaOrder ajiaOrder1 = new AjiaOrder();
		ajiaOrder1.setOrderId("001");
		AjiaOrder ajiaOrder2 = new AjiaOrder();
		ajiaOrder2.setOrderId("002");

		list.add(ajiaOrder1);
		list.add(ajiaOrder2);
		return list;

	}

	// 属性是对象
	@RequestMapping("/getJson5")
	@ResponseBody
	public AjiaOrder getJson5() {
		AjiaOrder ajiaOrder1 = new AjiaOrder();
		ajiaOrder1.setOrderId("001");

		AjiaUser ajiaUser = new AjiaUser();
		ajiaUser.setUsername("张久军");
		ajiaOrder1.setAjiaUser(ajiaUser);

		return ajiaOrder1;

	}
	
	// 属性是list
		@RequestMapping("/getJson6")
		@ResponseBody
		public AjiaOrder getJson6() {
			AjiaOrder ajiaOrder1 = new AjiaOrder();
			ajiaOrder1.setOrderId("001");

			AjiaUser ajiaUser = new AjiaUser();
			ajiaUser.setUsername("张久军");
			ajiaOrder1.setAjiaUser(ajiaUser);
			
			
			AjiaOrderItem ajiaOrderItem1=new AjiaOrderItem();
			ajiaOrderItem1.setTitle("华为mate10");
			
			AjiaOrderItem ajiaOrderItem2=new AjiaOrderItem();
			ajiaOrderItem2.setTitle("小米");
			
			ArrayList<AjiaOrderItem> list=new ArrayList<>();
			list.add(ajiaOrderItem1);
			list.add(ajiaOrderItem2);
			ajiaOrder1.setAjiaOrderItemList(list);

			return ajiaOrder1;

		}

}
