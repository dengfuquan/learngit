package com.ajiatech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajiatech.mapper.AjiaUserMapper;
import com.ajiatech.pojo.AjiaUser;
import com.ajiatech.pojo.AjiaUserExample;
import com.ajiatech.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AjiaUserMapper ajiaUserMapper;

	@Override
	public AjiaUser findByUsername(String username) throws Exception {
		AjiaUserExample example = new AjiaUserExample();
		AjiaUserExample.Criteria criteria = example.or();
		criteria.andUsernameEqualTo(username);
		List<AjiaUser> list = ajiaUserMapper.selectByExample(example);
		if (list != null && list.size() >= 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insert(AjiaUser ajiaUser) throws Exception {
		
		return ajiaUserMapper.insert(ajiaUser);
	}

}
