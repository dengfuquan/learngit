package com.ajiatech.pojo;

public class AjiaUserResult {
//	{
//		status:200
//		msg:"ok"
//		data:{
//		username:"123452"
//		userId:"14:
//		}
	private String status;
	private String msg;
	private AjiaUser data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AjiaUser getData() {
		return data;
	}
	public void setData(AjiaUser data) {
		this.data = data;
	}
	

}
