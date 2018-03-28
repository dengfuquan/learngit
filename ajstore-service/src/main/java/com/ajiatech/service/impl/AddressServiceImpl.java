package com.ajiatech.service.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.ajiatech.mapper.AjiaShippingMapper;
import com.ajiatech.pojo.AjiaShipping;
import com.ajiatech.pojo.AjiaShippingExample;
import com.ajiatech.pojo.AjiaShippingExample.Criteria;
import com.ajiatech.service.AddressService;

//@Component
//@Controller
@Service
public class AddressServiceImpl implements AddressService {

	//自动注入
	@Autowired
	AjiaShippingMapper mapper;
	@Override
	public int insert(AjiaShipping ajiaShipping) throws Exception {
		// 操作数据库

		// 读取连接信息
//		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//		// 得到sqlsessionFactory
//		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//		SqlSessionFactory factory = builder.build(inputStream);
//		// 得到sqlsession
//		SqlSession sqlSession = factory.openSession();
//		// 得到动态代理对象添加
//		AjiaShippingMapper mapper = sqlSession.getMapper(AjiaShippingMapper.class);
		int row = mapper.insert(ajiaShipping);
		// 提交
//		sqlSession.commit();
//		// 关闭
//		sqlSession.close();
		return 0;
	}

	@Override
	public List<AjiaShipping> findByUserId(long userId) throws Exception {
//		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession = factory.openSession();

		AjiaShippingExample example = new AjiaShippingExample();
		// 设置查询条件userid
		Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);

		// 设置查询条件status=1
		criteria.andStatusEqualTo((byte) 1);
		List<AjiaShipping> list = mapper.selectByExample(example);
		return list;
	}

	@Override
	public int delete(long addId) throws Exception {
//		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession = factory.openSession();
//
//		AjiaShippingMapper mapper = sqlSession.getMapper(AjiaShippingMapper.class);
		int row = mapper.deleteByPrimaryKey(addId);
//		sqlSession.commit();
//		sqlSession.close();
		return row;
	}

	@Override
	public int setDefault(long userId, long addId) throws Exception {
		
//		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession = factory.openSession();
//
//		AjiaShippingMapper mapper = sqlSession.getMapper(AjiaShippingMapper.class);
		//1：把用户的所有地址isdefault设置成false
		AjiaShippingExample example=new AjiaShippingExample();
		Criteria criteria=example.or();
		criteria.andUserIdEqualTo(userId);
		
		AjiaShipping ajiaShipping=new AjiaShipping();
		ajiaShipping.setIsDefault(false);
		
		int row = mapper.updateByExampleSelective(ajiaShipping, example);
		//2,把addId这个地址的isDefault设置成true
		criteria.andAddIdEqualTo(addId);
		ajiaShipping.setIsDefault(true);
		row=row+mapper.updateByExampleSelective(ajiaShipping, example);
		
//		sqlSession.commit();
//		sqlSession.close();
		return row;
	}

}
