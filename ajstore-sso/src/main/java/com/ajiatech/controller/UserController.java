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
		// 取cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);

		if (ticket != null) {
			// 从session中移出Ajiauser
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
		// 得cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		if (ticket == null) {
			ajiaResult = new AjiaResult(500, "未登录", null);
		} else {
			// 再从session中得user
			//AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			//从redis中取用户信息
			Jedis jedis=jedisUtils.getJedis();
			String userInfo=jedis.hget(ticket, "userInfo");
			AjiaUser ajiaUser=JsonUtils.jsonToPojo(userInfo, AjiaUser.class);
			jedisUtils.returnJedis(jedis);
			if (ajiaUser == null) {
				ajiaResult = new AjiaResult(500, "未登录", null);

			} else {
				String username = ajiaUser.getUsername();
				ajiaResult = AjiaResult.ok(username);
			}
		}
		// 用out把数据返回给浏览器
		PrintWriter out = response.getWriter();
		String json = JsonUtils.objectToJson(ajiaResult);
		String data = jsonpCallback + "(" + json + ")";
		out.println(data);
		out.close();
		return;
	}

	/**
	 * 检查用户 是否登录
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
		// 得cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		System.out.println("sso ticket="+ticket);

		if (ticket == null) {
			ajiaResult = new AjiaResult(500, "未登录", null);
		} else {
			// 再从session中得user
			//AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			Jedis jedis=jedisUtils.getJedis();
			String userInfo=jedis.hget(ticket, "userInfo");
			AjiaUser ajiaUser=JsonUtils.jsonToPojo(userInfo, AjiaUser.class);
			jedisUtils.returnJedis(jedis);
			if (ajiaUser == null) {
				ajiaResult = new AjiaResult(500, "未登录", null);

			} else {
				//在sso项目中，不把密码传给portal
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
		// 告诉浏览器，我信任www.ajstore.com，把返回结果交给www.ajstore.com
		// response.setHeader("Access-Control-Allow-Origin",
		// "http://www.ajstore.com");
		// response.setHeader("Access-Control-Allow-Credentials", "true");
		AjiaResult ajiaResult = null;
		// 得cookie
		String ticket = CookieUtils.getCookieValue(request, GlobalConst.COOKIE_NAME);
		System.out.println("sso ticket="+ticket);

		if (ticket == null) {
			ajiaResult = new AjiaResult(500, "未登录", null);
		} else {
			// 再从session中得user
			//AjiaUser ajiaUser = (AjiaUser) session.getAttribute(ticket);
			Jedis jedis=jedisUtils.getJedis();
			String userInfo=jedis.hget(ticket, "userInfo");
			AjiaUser ajiaUser=JsonUtils.jsonToPojo(userInfo, AjiaUser.class);
			jedisUtils.returnJedis(jedis);
			if (ajiaUser == null) {
				ajiaResult = new AjiaResult(500, "未登录", null);

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
		// 判断有没有这个用户
		if (dbUser == null) {
			return new AjiaResult(500, "用户名不存在", null);
		} else if (dbUser != null && !dbUser.getPassword().equals(ajiaUser.getPassword())) {
			// 判断密码是否正确
			return new AjiaResult(500, "密码错误", null);

		}
		String randomId = UUID.randomUUID().toString();
		// 把用户信息保存到 session中
		//session.setAttribute(randomId, dbUser);
		
		//把用户信息保存到redis中
		//多台服务器都能访问redis数据库
		//redis服务器关机，重启还有
		//redis还有集群，故障转移
		Jedis jedis=jedisUtils.getJedis();
		//把对象转成字符串
		String userinfo=JsonUtils.objectToJson(dbUser);
		jedis.hset(randomId, "userInfo", userinfo);
		jedisUtils.returnJedis(jedis);
		
		// ticket用cookie实现
		CookieUtils.setCookie(request, response, GlobalConst.COOKIE_NAME, randomId);
		// 密码不能返回给浏览器
		// dbUser.setPassword("");
		// 用户的真实id不能返回给浏览器
		// dbUser.setId(Long.parseLong(randomId));
		return AjiaResult.ok(dbUser);

	}

	@RequestMapping("/register.html")
	public ModelAndView register(AjiaUser ajiaUser) throws Exception {
		// 对密码进行md5
		String password = ajiaUser.getPassword();
		// 对密码进行加密，可以用加密工具类
		password = password + "ajstore123456789";
		String md5 = MD5Encrypt.MD5Encode(password);
		ajiaUser.setPassword(md5);

		ModelAndView modelAndView = new ModelAndView();
		ajiaUser.setCreated(new Date());
		// 查询用户 名是否存在
		AjiaUser dbUser = userService.findByUsername(ajiaUser.getUsername());
		if (dbUser != null) {
			modelAndView.addObject("errorMsg", "用户名已存在");
			modelAndView.setViewName("register");
		} else {

			int row = userService.insert(ajiaUser);

			// 判断注册是否成功
			if (row < 1) {
				modelAndView.addObject("errorMsg", "注册失败");
				modelAndView.setViewName("register");
			} else {
				// 注册成功，推荐做法不要再登录，从那来，回那去
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
			return AjiaResult.ok("用户名已经存在");
		}
		// 最好用状态码 1:存在， 2:不存在
		return AjiaResult.ok("用户名不存在");
	}
}
