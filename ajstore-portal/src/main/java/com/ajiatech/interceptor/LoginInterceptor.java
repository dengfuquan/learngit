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

	//��ִ��/cart /order��ǰִ��preHandle
	/**
	 * ����false ���ټ���ִ��/cart
	 * ����ture ����ִ��/cart
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//��cookie
		String ticket=CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		String url="http://sso.ajstore.com:81/user/toLogin.html";
		if (ticket==null || "null".equals(ticket)||"".equals(ticket)||" ".equals(ticket)){
			
			//û�õ� cookie,�ض����¼����
			response.sendRedirect(url);
			return false;//���ټ���ִ��
		}else{			
			//��cookie����httpClient����sso,�õ��û���Ϣ
			List<String> cookieList=new ArrayList<>();
			cookieList.add(GlobalConst.COOKIE_NAME+"="+ticket);
			String checkLoginUrl="http://sso.ajstore.com:81/user/checkLoginForIntercetpor.html";
			String json=HttpClientUtils.doGet(checkLoginUrl, cookieList);
			if (json==null ||"null".equals(json)||"".equals(json)||" ".equals(json))
			{
				//û�õ� �û���Ϣ,�ض����¼����
				response.sendRedirect(url);
				return false;//���ټ���ִ��
			}else{				
				//�õ��û���Ϣ�����û���Ϣ�ŵ�request,����ִ��/cart
				AjiaUserResult ajiaUserResult=JsonUtils.jsonToPojo(json, AjiaUserResult.class);
				request.setAttribute("ajiaUserResult", ajiaUserResult);
				return true;//����ִ��
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
