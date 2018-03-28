package com.ajiatech.service;

import com.ajiatech.pojo.AjiaItem;
import com.ajiatech.pojo.AjiaItemDesc;
import com.ajiatech.pojo.AjiaItemParam;
import com.ajiatech.pojo.AjiaItemParamItem;

public interface ItemService {
	/**
	 * ���ݲ�����ѯ��Ӧ����Ʒ
	 * @param itemParamsId
	 * @param color
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long selectItemByparams(long itemParamsId,String color,String model) throws Exception;
/**
 * ������Ʒ����id��ѯ��Ʒ���ڵ����͵Ĳ�������
 * @param itemParamId
 * @return
 * @throws Exception
 */
	public AjiaItemParam selectParamById(long itemParamId) throws Exception;
	
	/**
	 * ����itemid������Ʒ�Ĳ�����Ϣ(ͼƬ)
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public AjiaItemParamItem selectByItemId(long itemId) throws Exception;
	
	/**
	 * ��ѯ��Ʒ��Ϣ��title,price,model
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public AjiaItem selectItemById(long itemId) throws Exception;
	
	/**
	 * ��ѯ��Ʒ����ͼƬ
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public AjiaItemDesc selectDescById(long itemId) throws Exception;
	
	
	
	
	
	
}
