package com.jd.em.base.utils;

import java.io.Serializable;

/**
 * @author KING
 *
 * <p>Description: 接口返回实体</p>
 *
 * 2019年1月21日
 *
 */
public class JsonResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764583331390963977L;
	
	public JsonResult () {}
	
	public JsonResult (String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public JsonResult (String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	private String code;
	
	private String message = "";
	
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
