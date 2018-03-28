package com.ajiatech.controller;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajiatech.common.utils.CookieUtils;
import com.ajiatech.common.utils.GlobalConst;
import com.ajiatech.common.utils.HttpClientUtils;
import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.pojo.AjiaResult;

@Controller
@RequestMapping("/user")
public class UserController {
@RequestMapping("/httpClientCheckLogin.html")
	public void checkLogin(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	String json="";
	//�õ�cookie
	String cookie=CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
	if (cookie==null){
		AjiaResult ajiaResult=new AjiaResult(500, "��¼ʧ��", null);
		json=JsonUtils.objectToJson(ajiaResult);
	}else{
		//�� cookie�ŵ� httpClient��
		List<String> list=new ArrayList<>();
		//cookiename=cookieValue
		list.add(GlobalConst.COOKIE_NAME+"="+cookie);
		//��httpClient����sso.ajstore.com:81/user/checkLogin.html
		String url="http://sso.ajstore.com:81/user/checkLogin.html";
		json=HttpClientUtils.doGet(url, list);
		}
	//�ѽ�����ظ������
	PrintWriter out=response.getWriter();
	out.println(json);
	System.out.println("portal json="+json);
	out.close();
	}




}
