package com.ajiatech.service;

import com.ajiatech.pojo.AjiaUser;

public interface UserService {
	/**
	 * �����û��������û�
	 * @param username
	 * @return
	 * @throws Exception
	 */
public AjiaUser findByUsername(String username) throws Exception;

public int insert(AjiaUser ajiaUser) throws Exception;



}
