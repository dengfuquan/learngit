package com.ajiatech.pojo;

import java.util.*;

import com.ajiatech.pojo.itemParam.Params;

/**
 * 购物车中一件商品
 * @author java
 *
 */
public class AjiaCartItemVo {
	/**
	 * 主要有num
	 */
	AjiaCartItem ajiaCartItem;
	
	/**
	 * 主要有title,price
	 */
	AjiaItem ajiaItem;
	
	/**
	 * 商品参数主要有颜色，型号
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
