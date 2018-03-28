package com.ajiatech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	//通过字符串重定向
	@RequestMapping("/test1")
	public String test1()
	{
		return "redirect:http://www.baidu.com";
	}
	//通过modelAndView实现重定向到外部网站
	//modelAndView model放数据，view放网页
	@RequestMapping("/test2")
	public ModelAndView test2()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("redirect:http://www.sohu.com");
		return modelAndView;
	}
	//通过modelAndView实现重定向到内部网页
	@RequestMapping("/test3")
	public ModelAndView test3()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("redirect:/index.jsp");
		return modelAndView;
	}
	//web-inf中的网页不能重定向
	@RequestMapping("/test4")
	public ModelAndView test4()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("redirect:/WEB-INF/jsp/login.html");
		return modelAndView;
	}
	//1:用字符串转发
	@RequestMapping("/f1")
	public String f1() {
		return "/WEB-INF/jsp/login.html";
	}
	//2:转发用request带数据
	@RequestMapping("/f2")
	public String f2(HttpServletRequest request)
	{
		request.setAttribute("data", "1709");
		return "/index.jsp";//还要在index.jsp中取数据
	}
	//3:用modelAndview转发，用setViewName设置网页
	@RequestMapping("/f3")
	public ModelAndView f3() {
		ModelAndView modelAndView=new ModelAndView();
		//设置数据
		modelAndView.addObject("data", "111");
		//设置要显示的网页
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	}
	//4:用modelAndview转发，用构造方法设置网页
	@RequestMapping("/f4")
	public ModelAndView f4() {
		//设置要显示的网页
		ModelAndView modelAndView=new ModelAndView("/index.jsp");
		//设置数据
		modelAndView.addObject("data", "222");	
		
		return modelAndView;
	}
	
	//5:用model设置数据
	@RequestMapping("/f5")
	public String f5(Model model)
	{
		model.addAttribute("username", "a");
		model.addAttribute("data", "5555");
		return "/index.jsp";
	}
	
	//6:总结用modelAndview转发，用setViewName设置网页
	@RequestMapping("/f6")
	public ModelAndView f6()
	{
		
		ModelAndView modelAndView=new ModelAndView();
		//modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
		modelAndView.setViewName("login");

		return modelAndView;
	}

}
