package com.ajiatech.exception;

/**
 * 自定义异常
 * @author java
 *
 */
public class UserIdIsNullException extends AjstoreException{
@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return "用户编号不存在";
}
}
