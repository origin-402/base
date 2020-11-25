package com.jd.em.base.utils;

import org.springframework.context.ApplicationContext;


/**
 * @author KING
 *
 * <p>Description: 配置</p>
 *
 * 2019年1月21日
 *
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_MENULIST = "menuList";			//当前登陆用户拥有菜单
	public static final String SESSION_ALLMENULIST = "allmenuList";		//全部菜单
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String SESSION_NAME = "NAME";			//用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String EMPTY = "empty";
	public static final String LOGIN = "/tologin";				//登录地址
	public static final String ERRORPATH = "/error";				//404地址
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(app)|(static)|(main)|(register)|(toRegister)|(tologin)|(assets)|(error)|(rest)).*";	//不对匹配该值的访问路径拦截（正则）


	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化





}
