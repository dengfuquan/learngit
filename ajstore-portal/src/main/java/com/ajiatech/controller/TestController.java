package com.ajiatech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	//ͨ���ַ����ض���
	@RequestMapping("/test1")
	public String test1()
	{
		return "redirect:http://www.baidu.com";
	}
	//ͨ��modelAndViewʵ���ض����ⲿ��վ
	//modelAndView model�����ݣ�view����ҳ
	@RequestMapping("/test2")
	public ModelAndView test2()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("redirect:http://www.sohu.com");
		return modelAndView;
	}
	//ͨ��modelAndViewʵ���ض����ڲ���ҳ
	@RequestMapping("/test3")
	public ModelAndView test3()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("redirect:/index.jsp");
		return modelAndView;
	}
	//web-inf�е���ҳ�����ض���
	@RequestMapping("/test4")
	public ModelAndView test4()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("redirect:/WEB-INF/jsp/login.html");
		return modelAndView;
	}
	//1:���ַ���ת��
	@RequestMapping("/f1")
	public String f1() {
		return "/WEB-INF/jsp/login.html";
	}
	//2:ת����request������
	@RequestMapping("/f2")
	public String f2(HttpServletRequest request)
	{
		request.setAttribute("data", "1709");
		return "/index.jsp";//��Ҫ��index.jsp��ȡ����
	}
	//3:��modelAndviewת������setViewName������ҳ
	@RequestMapping("/f3")
	public ModelAndView f3() {
		ModelAndView modelAndView=new ModelAndView();
		//��������
		modelAndView.addObject("data", "111");
		//����Ҫ��ʾ����ҳ
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	}
	//4:��modelAndviewת�����ù��췽��������ҳ
	@RequestMapping("/f4")
	public ModelAndView f4() {
		//����Ҫ��ʾ����ҳ
		ModelAndView modelAndView=new ModelAndView("/index.jsp");
		//��������
		modelAndView.addObject("data", "222");	
		
		return modelAndView;
	}
	
	//5:��model��������
	@RequestMapping("/f5")
	public String f5(Model model)
	{
		model.addAttribute("username", "a");
		model.addAttribute("data", "5555");
		return "/index.jsp";
	}
	
	//6:�ܽ���modelAndviewת������setViewName������ҳ
	@RequestMapping("/f6")
	public ModelAndView f6()
	{
		
		ModelAndView modelAndView=new ModelAndView();
		//modelAndView.setViewName("/WEB-INF/jsp/login.jsp");
		modelAndView.setViewName("login");

		return modelAndView;
	}

}
