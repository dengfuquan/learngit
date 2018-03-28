package com.ajiatech.service;

import java.util.List;

import com.ajiatech.pojo.AjiaShipping;

public interface AddressService {
	public int setDefault(long userId,long addId) throws Exception;
	public List<AjiaShipping> findByUserId(long userId) throws Exception;
	public int insert(AjiaShipping ajiaShipping) throws Exception;
	public int delete(long addId) throws Exception;

}
