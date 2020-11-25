package com.jd.em.base.utils;

/**
 * 
 * 
 * @author KING
 * 
 * <p>Description:相应状态码</p>
 * 
 *  @date 2019-01-21
 *
 */
public class HttpStatusCodes {
	public static final String OK = "200";       //get 成功后会获得
	public static final String CREATED = "201";  //[POST/PUT]：用户新建或修改数据成功。
	public static final String NO_CONTENT = "204";  //用户删除数据成功。以及查询不到数据时
	public static final String BAD_REQUEST = "400";       //参数错误
	public static final String UNAUTHORIZED = "401";   //用于提示用户名、密码错误
	public static final String PAYMENT_REQUIRED = "402";
	public static final String NOT_FOUND = "404";      //找不到对应资源
	public static final String UNPROCESSABLEENTITY = "422";  //字段缺失或者参数格式化有问题
	public static final String SERVERERROR = "500"; 
	
}
