package com.ajstore;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ajiatech.pojo.AjiaUser;
import com.ajiatech.pojo.AjiaUserExample;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedis {
	@Test
	public void testCluster()
	{
		//创建集合用来存放服务器信息
		Set<HostAndPort> set=new HashSet<>();
		
		//往集合中添加服务器
		set.add(new HostAndPort("127.0.0.1", 2002));
		set.add(new HostAndPort("127.0.0.1", 2001));
		set.add(new HostAndPort("127.0.0.1", 2003));
		set.add(new HostAndPort("127.0.0.1", 2004));
		set.add(new HostAndPort("127.0.0.1", 2005));
		set.add(new HostAndPort("127.0.0.1", 2006));
		
		//把集合变成集群
		JedisCluster jedisCluster=
				new JedisCluster(set);
		//写数据
		jedisCluster.set("4", "user4");
		jedisCluster.set("5", "user5");
		//读数据
		String user4=jedisCluster.get("4");
		String user5=jedisCluster.get("5");
		System.out.println(user4+","+user5);
		
	}

	@Test
	public void testRedisMysql() {

		try {

			// 测试写数据
			long startTime = 0, endTime = 0;
			startTime = System.currentTimeMillis();
			JedisPoolConfig config = new JedisPoolConfig();
			JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("zhangjj");
			for (int i = 0; i < 100; i++) {
				jedis.set("user4789" + i, "" + i);
			}
			endTime = System.currentTimeMillis();
			long redisWriteTime = endTime - startTime;

			startTime = System.currentTimeMillis();
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(inputStream);

			SqlSession sqlSession = factory.openSession();
			for (int i = 0; i < 100; i++) {
				AjiaUser ajiaUser = new AjiaUser();
				ajiaUser.setUsername("user4679" + i);
				ajiaUser.setPassword("");
				sqlSession.insert("com.ajiatech.mapper.AjiaUserMapper.insert", ajiaUser);
				sqlSession.commit();
			}
			endTime = System.currentTimeMillis();
			long mysqlWriteTime = endTime - startTime;
			System.out.println(mysqlWriteTime / redisWriteTime);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void read() {
		try {
			// 测试读数据
			long startTime = 0, endTime = 0;
			startTime = System.currentTimeMillis();
			JedisPoolConfig config = new JedisPoolConfig();
			JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("zhangjj");
			for (int i = 0; i < 100; i++) {
				jedis.get("user4788" + i);
			}
			endTime = System.currentTimeMillis();
			long redisWriteTime = endTime - startTime;

			startTime = System.currentTimeMillis();
			InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(inputStream);

			SqlSession sqlSession = factory.openSession();
			for (int i = 0; i < 100; i++) {

				AjiaUser ajiaUser = sqlSession.selectOne("com.ajiatech.mapper.AjiaUserMapper.selectByUsername",
						"user4677" + i);
			}
			endTime = System.currentTimeMillis();
			long mysqlWriteTime = endTime - startTime;
			System.out.println(mysqlWriteTime / redisWriteTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAlone() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
			Jedis jedis = pool.getResource();
			jedis.auth("zhangjj");
			// string
			jedis.set("1709_1001", "aaaa");
			String username = jedis.get("1709_1001");
			System.out.println(username);

			// list
			jedis.lpush("1709_orderList", "001", "002");
			List items = jedis.lrange("1709_orderList", 0, -1);
			System.out.println(items.toString());

			// hash
			HashMap hashMap = new HashMap<>();
			hashMap.put("usename", "aaa");
			hashMap.put("password", "111");
			jedis.hmset("1709_hashMap", hashMap);
			HashMap hashMap2 = (HashMap) jedis.hgetAll("1709_hashMap");
			System.out.println(hashMap2);

			// set
			jedis.sadd("1709_set", "1001", "1002");
			Set set = jedis.smembers("1709_set");
			System.out.println(set);

			// zset
			Map zMap = new HashMap();
			zMap.put("张三", 70D);
			zMap.put("李四", 90D);
			zMap.put("马云", 1D);
			jedis.zadd("student", zMap);
			Set zSet = jedis.zrange("student", 0, -1);
			System.out.println(zSet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
