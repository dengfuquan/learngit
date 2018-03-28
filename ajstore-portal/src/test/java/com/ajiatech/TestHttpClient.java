package com.ajiatech;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.ajiatech.common.utils.HttpClientUtils;

public class TestHttpClient {
	// 访问百度
	@Test
	public void test1() {
		String html = HttpClientUtils.doGet("https://www.baidu.com");
		System.out.println(html);
	}

	// 生成网页,网页静态化
	@Test
	public void test2() {
		try {
			String[] itemIds = { "10000028", "10000032", "10000031" };

			for (int i = 0; i < itemIds.length; i++) {
				String url = "http://www.ajstore.com/toItemInfo/" + itemIds[i] + ".html";
				String html = HttpClientUtils.doGet(url);
				String path = "D:/1709tomcat/apache-tomcat-7.0.82-windows-x64/apache-tomcat-7.0.82/webapps/ajstore-portal/staticHtml";
				path = path + "/" + itemIds[i] + ".html";

				FileOutputStream fileOutputStream = new FileOutputStream(path);
				DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

				dataOutputStream.writeUTF(html);
				fileOutputStream.close();
				dataOutputStream.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 登录
	@Test
	public void test3()
	{
		String url="http://sso.ajstore.com:81/user/checkLogin.html";
		String json=HttpClientUtils.doGet(url);
		System.out.println(json);
	}
}
