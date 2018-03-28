package com.ajiatech.pojo;

import java.util.List;

import com.ajiatech.pojo.itemParam.Params;

public class AjiaOrderVo {
	AjiaOrder ajiaOrder;
	List<AjiaOrderItem> ajiaOrderItemList;
	
	public AjiaOrder getAjiaOrder() {
		return ajiaOrder;
	}
	public void setAjiaOrder(AjiaOrder ajiaOrder) {
		this.ajiaOrder = ajiaOrder;
	}
	public List<AjiaOrderItem> getAjiaOrderItemList() {
		return ajiaOrderItemList;
	}
	public void setAjiaOrderItemList(List<AjiaOrderItem> ajiaOrderItemList) {
		this.ajiaOrderItemList = ajiaOrderItemList;
	}
	

}
