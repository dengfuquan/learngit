package com.ajiatech.service;

import com.ajiatech.pojo.AjiaItem;
import com.ajiatech.pojo.AjiaItemDesc;
import com.ajiatech.pojo.AjiaItemParam;
import com.ajiatech.pojo.AjiaItemParamItem;

public interface ItemService {
	/**
	 * 根据参数查询对应的商品
	 * @param itemParamsId
	 * @param color
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long selectItemByparams(long itemParamsId,String color,String model) throws Exception;
/**
 * 根据商品参数id查询商品所在的类型的参数数据
 * @param itemParamId
 * @return
 * @throws Exception
 */
	public AjiaItemParam selectParamById(long itemParamId) throws Exception;
	
	/**
	 * 根据itemid查找商品的参数信息(图片)
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public AjiaItemParamItem selectByItemId(long itemId) throws Exception;
	
	/**
	 * 查询商品信息有title,price,model
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public AjiaItem selectItemById(long itemId) throws Exception;
	
	/**
	 * 查询商品描述图片
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public AjiaItemDesc selectDescById(long itemId) throws Exception;
	
	
	
	
	
	
}
