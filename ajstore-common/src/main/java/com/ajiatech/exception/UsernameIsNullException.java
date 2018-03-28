package com.ajiatech.exception;

/**
 * 自定义异常
 * @author java
 *
 */
public class UsernameIsNullException extends AjstoreException{
@Override
public String getMessage() {
	// TODO Auto-generated method stub
	return "用户名不存在";
}
}
