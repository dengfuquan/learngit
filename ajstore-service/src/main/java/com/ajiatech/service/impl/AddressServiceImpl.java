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

	//�Զ�ע��
	@Autowired
	AjiaShippingMapper mapper;
	@Override
	public int insert(AjiaShipping ajiaShipping) throws Exception {
		// �������ݿ�

		// ��ȡ������Ϣ
//		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//		// �õ�sqlsessionFactory
//		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//		SqlSessionFactory factory = builder.build(inputStream);
//		// �õ�sqlsession
//		SqlSession sqlSession = factory.openSession();
//		// �õ���̬����������
//		AjiaShippingMapper mapper = sqlSession.getMapper(AjiaShippingMapper.class);
		int row = mapper.insert(ajiaShipping);
		// �ύ
//		sqlSession.commit();
//		// �ر�
//		sqlSession.close();
		return 0;
	}

	@Override
	public List<AjiaShipping> findByUserId(long userId) throws Exception {
//		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession = factory.openSession();

		AjiaShippingExample example = new AjiaShippingExample();
		// ���ò�ѯ����userid
		Criteria criteria = example.or();
		criteria.andUserIdEqualTo(userId);

		// ���ò�ѯ����status=1
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
		//1�����û������е�ַisdefault���ó�false
		AjiaShippingExample example=new AjiaShippingExample();
		Criteria criteria=example.or();
		criteria.andUserIdEqualTo(userId);
		
		AjiaShipping ajiaShipping=new AjiaShipping();
		ajiaShipping.setIsDefault(false);
		
		int row = mapper.updateByExampleSelective(ajiaShipping, example);
		//2,��addId�����ַ��isDefault���ó�true
		criteria.andAddIdEqualTo(addId);
		ajiaShipping.setIsDefault(true);
		row=row+mapper.updateByExampleSelective(ajiaShipping, example);
		
//		sqlSession.commit();
//		sqlSession.close();
		return row;
	}

}
