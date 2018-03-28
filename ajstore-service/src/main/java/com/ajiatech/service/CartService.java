package com.ajiatech.service;

import java.util.List;


import com.ajiatech.pojo.AjiaCartItem;
import com.ajiatech.pojo.AjiaCartItemVo;
import com.ajiatech.pojo.AjiaShipping;

public interface CartService {
	
	/**
	 * ���û���Ĭ�ϵ�ַ
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public AjiaShipping getDefaultAddress(long userId) throws Exception;
	/**
	 * ������Ʒid��ѯ������Ʒ
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public List<AjiaCartItemVo> selectByItemIds(List<Long> idList,long userId) throws Exception;

	/**
	 * ɾ�����ﳵ�ж����Ʒ
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByIds(List<Long> idList,long userId) throws Exception;

	/**
	 * ɾ�����ﳵ�е�����Ʒ
	 * @param ajiaCartItem
	 * @return
	 * @throws Exception
	 */
	public boolean deleteById(AjiaCartItem ajiaCartItem) throws Exception;
	/**
	 * ���Ĺ��ﳵĳ����Ʒ������
	 * @param ajiaCartItem
	 * @return
	 * @throws Exception
	 */
	public boolean changeCartNum(AjiaCartItem ajiaCartItem) throws Exception;
	/**
	 * ��ѯĳ���û��Ĺ��ﳵ
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<AjiaCartItemVo> selectByUserId(long userId) throws Exception;
	/**
	 * ����Ʒ�ŵ����ﳵ��
	 * @param ajiaCartItem
	 * @return
	 * @throws Exception
	 */
	public boolean  insert(AjiaCartItem ajiaCartItem) throws Exception;

}
