package com.ajiatech.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.deploy.LoginConfig;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ajiatech.common.utils.CookieUtils;
import com.ajiatech.common.utils.GlobalConst;
import com.ajiatech.common.utils.JsonUtils;
import com.ajiatech.common.utils.MD5Encrypt;
import com.ajiatech.pojo.AjiaResult;
import com.ajiatech.pojo.AjiaUser;
import com.ajiatech.service.UserService;
import com.ajiatech.utils.JedisUtils;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	JedisUtils jedisUtils;

	@RequestMapping("/logout.html")
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String jsonpCallback) throws IOException {
		// ȡcookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);

		if (ticket != null) {
			// ��session���Ƴ�Ajiauser
			session.removeAttribute(ticket);
		}

		AjiaResult ajiaResult = AjiaResult.ok();
		String json = JsonUtils.objectToJson(ajiaResult);

		String data = jsonpCallback + "(" + json + ")";
		PrintWriter out = response.getWriter();
		out.println(data);
		out.close();
	}

	@RequestMapping("/checkLoginJsonp.html")

	public void checkLoginJsonp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String jsonpCallback) throws IOException {

		AjiaResult ajiaResult = null;
		// ��cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		if (ticket == null) {
			ajiaResult = new AjiaResult(500, "δ��¼", null);
		} else {
			// �ٴ�session�е�user
			//AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			//��redis��ȡ�û���Ϣ
			Jedis jedis=jedisUtils.getJedis();
			String userInfo=jedis.hget(ticket, "userInfo");
			AjiaUser ajiaUser=JsonUtils.jsonToPojo(userInfo, AjiaUser.class);
			jedisUtils.returnJedis(jedis);
			if (ajiaUser == null) {
				ajiaResult = new AjiaResult(500, "δ��¼", null);

			} else {
				String username = ajiaUser.getUsername();
				ajiaResult = AjiaResult.ok(username);
			}
		}
		// ��out�����ݷ��ظ������
		PrintWriter out = response.getWriter();
		String json = JsonUtils.objectToJson(ajiaResult);
		String data = jsonpCallback + "(" + json + ")";
		out.println(data);
		out.close();
		return;
	}

	/**
	 * ����û� �Ƿ��¼
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkLoginForIntercetpor.html")
	@ResponseBody
	public AjiaResult checkLoginForIntercetpor(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		AjiaResult ajiaResult = null;
		// ��cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		System.out.println("sso ticket="+ticket);

		if (ticket == null) {
			ajiaResult = new AjiaResult(500, "δ��¼", null);
		} else {
			// �ٴ�session�е�user
			//AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			Jedis jedis=jedisUtils.getJedis();
			String userInfo=jedis.hget(ticket, "userInfo");
			AjiaUser ajiaUser=JsonUtils.jsonToPojo(userInfo, AjiaUser.class);
			jedisUtils.returnJedis(jedis);
			if (ajiaUser == null) {
				ajiaResult = new AjiaResult(500, "δ��¼", null);

			} else {
				//��sso��Ŀ�У��������봫��portal
				ajiaUser.setPassword("");
				ajiaResult = AjiaResult.ok(ajiaUser);
			}
		}
		System.out.println("sso ajiaResult="+ajiaResult.toString());

		return ajiaResult;
	}

	@RequestMapping("/checkLogin.html")
	@ResponseBody
	public AjiaResult checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// �����������������www.ajstore.com���ѷ��ؽ������www.ajstore.com
		// response.setHeader("Access-Control-Allow-Origin",
		// "http://www.ajstore.com");
		// response.setHeader("Access-Control-Allow-Credentials", "true");
		AjiaResult ajiaResult = null;
		// ��cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		System.out.println("sso ticket="+ticket);

		if (ticket == null) {
			ajiaResult = new AjiaResult(500, "δ��¼", null);
		} else {
			// �ٴ�session�е�user
			//AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			Jedis jedis=jedisUtils.getJedis();
			String userInfo=jedis.hget(ticket, "userInfo");
			AjiaUser ajiaUser=JsonUtils.jsonToPojo(userInfo, AjiaUser.class);
			jedisUtils.returnJedis(jedis);
			if (ajiaUser == null) {
				ajiaResult = new AjiaResult(500, "δ��¼", null);

			} else {
				String username = ajiaUser.getUsername();
				ajiaResult = AjiaResult.ok(username);
			}
		}
		System.out.println("sso ajiaResult="+ajiaResult.toString());

		return ajiaResult;
	}

	@RequestMapping("/login.html")
	@ResponseBody
	public AjiaResult Login(AjiaUser ajiaUser, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String password = ajiaUser.getPassword();
		password = password + "ajstore123456789";
		String md5 = MD5Encrypt.MD5Encode(password);
		ajiaUser.setPassword(md5);

		AjiaUser dbUser = userService.findByUsername(ajiaUser.getUsername());
		// �ж���û������û�
		if (dbUser == null) {
			return new AjiaResult(500, "�û���������", null);
		} else if (dbUser != null && !dbUser.getPassword().equals(ajiaUser.getPassword())) {
			// �ж������Ƿ���ȷ
			return new AjiaResult(500, "�������", null);

		}
		String randomId = UUID.randomUUID().toString();
		// ���û���Ϣ���浽 session��
		//session.setAttribute(randomId, dbUser);
		
		//���û���Ϣ���浽redis��
		//��̨���������ܷ���redis���ݿ�
		//redis�������ػ�����������
		//redis���м�Ⱥ������ת��
		Jedis jedis=jedisUtils.getJedis();
		//�Ѷ���ת���ַ���
		String userinfo=JsonUtils.objectToJson(dbUser);
		jedis.hset(randomId, "userInfo", userinfo);
		jedisUtils.returnJedis(jedis);
		
		// ticket��cookieʵ��
		CookieUtils.setCookie(request, response, GlobalConst.COOKIE_NAME, randomId);
		// ���벻�ܷ��ظ������
		// dbUser.setPassword("");
		// �û�����ʵid���ܷ��ظ������
		// dbUser.setId(Long.parseLong(randomId));
		return AjiaResult.ok(dbUser);

	}

	@RequestMapping("/register.html")
	public ModelAndView register(AjiaUser ajiaUser) throws Exception {
		// ���������md5
		String password = ajiaUser.getPassword();
		// ��������м��ܣ������ü��ܹ�����
		password = password + "ajstore123456789";
		String md5 = MD5Encrypt.MD5Encode(password);
		ajiaUser.setPassword(md5);

		ModelAndView modelAndView = new ModelAndView();
		ajiaUser.setCreated(new Date());
		// ��ѯ�û� ���Ƿ����
		AjiaUser dbUser = userService.findByUsername(ajiaUser.getUsername());
		if (dbUser != null) {
			modelAndView.addObject("errorMsg", "�û����Ѵ���");
			modelAndView.setViewName("register");
		} else {

			int row = userService.insert(ajiaUser);

			// �ж�ע���Ƿ�ɹ�
			if (row < 1) {
				modelAndView.addObject("errorMsg", "ע��ʧ��");
				modelAndView.setViewName("register");
			} else {
				// ע��ɹ����Ƽ�������Ҫ�ٵ�¼��������������ȥ
				modelAndView.setViewName("redirect:/user/toLogin.html");
			}
		}
		return modelAndView;
	}

	@RequestMapping("/toLogin.html")
	public ModelAndView toLogin() {
		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("from", from);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping("/toRegister.html")
	public ModelAndView toRegister() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping("/checkUser")
	@ResponseBody
	public AjiaResult checkUser(String username) throws Exception {

		AjiaUser ajiaUser = userService.findByUsername(username);
		if (ajiaUser != null) {
			return AjiaResult.ok("�û����Ѿ�����");
		}
		// �����״̬�� 1:���ڣ� 2:������
		return AjiaResult.ok("�û���������");
	}
}
