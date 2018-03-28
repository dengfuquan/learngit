package com.ajiatech.service;

import java.util.List;

import com.ajiatech.pojo.AjiaOrder;
import com.ajiatech.pojo.AjiaOrderVo;
import com.github.pagehelper.PageInfo;

/**
 * 订单相关
 * @author java
 *
 */
public interface OrderService {
	/**
	 * 查询订单信息
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public AjiaOrderVo selectVoByOrderId(String orderId) throws Exception;
	
	/**
	 * 删除订单
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByOrderId(String orderId) throws Exception;
	
	/**
	 * 根据订单编号更新订单状态
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public boolean updateStatusByOrderid(String orderId,int status) throws Exception;
	/**
	 * 根据订单编号查找订单
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public AjiaOrder selectByOrderId(String orderid) throws Exception;
	/**
	 * 查询用户的订单
	 * @param userId
	 * @param status
	 * @return  pageInfo包含总页数和当前页数据
	 * @throws Exception
	 */
	public PageInfo<AjiaOrderVo> selectByUserIdStatusForPage(Long userId,int status,int currentPage,int pageSize) throws Exception;
	
	/**
	 * 查询用户的订单
	 * @param userId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<AjiaOrderVo> selectByUserIdStatus(Long userId,int status) throws Exception;
	
	/**
	 * 保存订单
	 * @param userId
	 * @param addId
	 * @param itemIdList 订单中商品的id
	 * @return
	 * @throws Exception
	 */
public AjiaOrder saveOrder(Long userId,Long addId,List<Long> itemIdList) throws Exception;
}
