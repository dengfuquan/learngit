package com.ajiatech.pojo;

import java.util.*;

import com.ajiatech.pojo.itemParam.Params;

/**
 * ���ﳵ��һ����Ʒ
 * @author java
 *
 */
public class AjiaCartItemVo {
	/**
	 * ��Ҫ��num
	 */
	AjiaCartItem ajiaCartItem;
	
	/**
	 * ��Ҫ��title,price
	 */
	AjiaItem ajiaItem;
	
	/**
	 * ��Ʒ������Ҫ����ɫ���ͺ�
	 */
	List<Params> paramsList;

	public AjiaCartItem getAjiaCartItem() {
		return ajiaCartItem;
	}

	public void setAjiaCartItem(AjiaCartItem ajiaCartItem) {
		this.ajiaCartItem = ajiaCartItem;
	}

	public AjiaItem getAjiaItem() {
		return ajiaItem;
	}

	public void setAjiaItem(AjiaItem ajiaItem) {
		this.ajiaItem = ajiaItem;
	}

	public List<Params> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<Params> paramsList) {
		this.paramsList = paramsList;
	}

}
