package com.ajiatech.controller;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ajiatech.pojo.AjiaResult;
import com.ajiatech.pojo.AjiaShipping;
import com.ajiatech.service.AddressService;
import com.ajiatech.service.impl.AddressServiceImpl;

@Controller
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping("/setDefault")
	@ResponseBody
	public AjiaResult setDefault(long addId) throws Exception
	{
		AjiaResult ajiaResult=null;
		//AddressService addressService=new AddressServiceImpl();
		int row=addressService.setDefault(14L, addId);
		if (row>=2)
		{
			ajiaResult=AjiaResult.ok();
		}else{
			ajiaResult=new AjiaResult(500, "设置失败", null);
		}
		//return {status:200,msg:ok}
		return ajiaResult;
	}
	
	
//ajiaRest有status=200,msg=ok ajax收到的是{status:200,msg:ok}
	@RequestMapping("/delete")
	@ResponseBody
	public AjiaResult delete(long addId) throws Exception {
		//AddressService addressService = new AddressServiceImpl();
		int row = addressService.delete(addId);
		if (row >= 1) {
			AjiaResult ajiaResult = AjiaResult.ok();
			return ajiaResult;
		}
		AjiaResult ajiaResult = new AjiaResult(500, "删除失败", null);
		return ajiaResult;
	}

	@RequestMapping("/insert.html")
	public ModelAndView insert(AjiaShipping ajiaShipping) throws Exception {
		// 给非空列设置值
		ajiaShipping.setUserId(14L);
		ajiaShipping.setStatus((byte) 1);
		ajiaShipping.setIsDefault(true);

		// 调 去添加数据
		//AddressService addressService = new AddressServiceImpl();
		int row = addressService.insert(ajiaShipping);
		// 通过重定向返回的是网页
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/address/list");
		return modelAndView;
	}

	// 访问的url是/address/list
	//@RequestMapping("/list.html")
	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		// 调用service取数据
		//AddressService addressService = new AddressServiceImpl();
		List<AjiaShipping> list = addressService.findByUserId(14L);

		// 把数据保存到jsp中
		modelAndView.addObject("ajiaShippingList", list);
		// 转发到\web-inf\jsp\ addressAdmin .jsp
		modelAndView.setViewName("addressAdmin");
		return modelAndView;
	}
}
