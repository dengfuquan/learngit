package com.ajiatech.service;

import com.ajiatech.pojo.AjiaUser;

public interface UserService {
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 * @throws Exception
	 */
public AjiaUser findByUsername(String username) throws Exception;

public int insert(AjiaUser ajiaUser) throws Exception;



}
