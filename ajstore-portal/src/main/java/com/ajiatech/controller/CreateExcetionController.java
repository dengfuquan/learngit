package com.ajiatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajiatech.exception.UserIdIsNullException;
import com.ajiatech.pojo.AjiaShipping;

@Controller
public class CreateExcetionController {

	@RequestMapping("/e1")
	public String exception1() {

		// 转发到\web-inf\jsp\ addressAdmin .jsp
		return "addressAdmin";

	}

	@RequestMapping("/e2")
	public String exception2() throws UserIdIsNullException {

		AjiaShipping ajiaShipping = new AjiaShipping();
		ajiaShipping.setUserId(null);

		// 转发到\web-inf\jsp\ addressAdmin .jsp
		return "addressAdmin";

	}
	
	@RequestMapping("/e3")
	public String exception3() throws Exception {

		AjiaShipping ajiaShipping = null;
		ajiaShipping.toString();

		// 转发到\web-inf\jsp\ addressAdmin .jsp
		return "addressAdmin";

	}

}
