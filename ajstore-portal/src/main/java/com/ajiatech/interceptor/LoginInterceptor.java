package com.ajiatech.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ajiatech.common.utils.CookieUtils;
import com.ajiatech.common.utils.GlobalConst;
import com.ajiatech.common.utils.HttpClientUtils;
import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.pojo.AjiaUserResult;

public class LoginInterceptor implements HandlerInterceptor{

	//在执行/cart /order这前执行preHandle
	/**
	 * 返回false 不再继续执行/cart
	 * 返回ture 继续执行/cart
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//得cookie
		String ticket=CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		String url="http://sso.ajstore.com:81/user/toLogin.html";
		if (ticket==null || "null".equals(ticket)||"".equals(ticket)||" ".equals(ticket)){
			
			//没得到 cookie,重定向登录界面
			response.sendRedirect(url);
			return false;//不再继续执行
		}else{			
			//有cookie，用httpClient访问sso,得到用户信息
			List<String> cookieList=new ArrayList<>();
			cookieList.add(GlobalConst.COOKIE_NAME+"="+ticket);
			String checkLoginUrl="http://sso.ajstore.com:81/user/checkLoginForIntercetpor.html";
			String json=HttpClientUtils.doGet(checkLoginUrl, cookieList);
			if (json==null ||"null".equals(json)||"".equals(json)||" ".equals(json))
			{
				//没得到 用户信息,重定向登录界面
				response.sendRedirect(url);
				return false;//不再继续执行
			}else{				
				//得到用户信息，把用户信息放到request,继续执行/cart
				AjiaUserResult ajiaUserResult=JsonUtils.jsonToPojo(json, AjiaUserResult.class);
				request.setAttribute("ajiaUserResult", ajiaUserResult);
				return true;//继续执行
			}
		}
		//return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
