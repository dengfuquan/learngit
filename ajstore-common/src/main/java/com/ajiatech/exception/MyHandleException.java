package com.ajiatech.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
/**
 * 全局异常处理
 * @author java
 *
 */
public class MyHandleException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException
	(HttpServletRequest request,
			HttpServletResponse response, 
			Object handler,
			Exception ex) {
		//把错误信息显示在网页上
		
		//判断异常是不是我们定义的
		String errorMsg="";
		if (ex instanceof AjstoreException)
		{
			errorMsg=ex.getMessage();
		}else {
			errorMsg="出错了";
		}
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("errorMsg", errorMsg);
		modelAndView.setViewName("exception");
		//把错误的详细信息保存到mysql
		
		StringWriter stringWriter=new StringWriter();
		PrintWriter printWriter=new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		String detailErrormsg=stringWriter.toString();
		System.out.println("----------程序出错了---");
		System.out.println(detailErrormsg);
		
		
		
		
		return modelAndView;
	}

}
