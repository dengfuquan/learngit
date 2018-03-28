package com.ajiatech.service;

import java.util.List;


import com.ajiatech.pojo.AjiaCartItem;
import com.ajiatech.pojo.AjiaCartItemVo;
import com.ajiatech.pojo.AjiaShipping;

public interface CartService {
	
	/**
	 * 得用户的默认地址
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public AjiaShipping getDefaultAddress(long userId) throws Exception;
	/**
	 * 根据商品id查询几个商品
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public List<AjiaCartItemVo> selectByItemIds(List<Long> idList,long userId) throws Exception;

	/**
	 * 删除购物车中多个商品
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByIds(List<Long> idList,long userId) throws Exception;

	/**
	 * 删除购物车中单个商品
	 * @param ajiaCartItem
	 * @return
	 * @throws Exception
	 */
	public boolean deleteById(AjiaCartItem ajiaCartItem) throws Exception;
	/**
	 * 更改购物车某个商品的数量
	 * @param ajiaCartItem
	 * @return
	 * @throws Exception
	 */
	public boolean changeCartNum(AjiaCartItem ajiaCartItem) throws Exception;
	/**
	 * 查询某个用户的购物车
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<AjiaCartItemVo> selectByUserId(long userId) throws Exception;
	/**
	 * 把商品放到购物车中
	 * @param ajiaCartItem
	 * @return
	 * @throws Exception
	 */
	public boolean  insert(AjiaCartItem ajiaCartItem) throws Exception;

}
