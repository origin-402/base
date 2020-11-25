package com.jd.em.base.config;

import com.jd.em.base.domain.PageData;
import com.jd.em.base.domain.User;
import com.jd.em.base.utils.Const;
import com.jd.em.base.utils.UuidUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;


/**
 * @author KING
 *
 * <p>Description: controller_base</p>
 *
 * 2019年1月21日
 *
 */
public class BaseController {

	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}

	/**
	 * 得到session对象
	 */
	public HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

		return request.getSession();
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){

		return UuidUtil.get32UUID();
	}

	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}

	public String getCurrentUserId(){
		User user = (User)getRequest().getSession().getAttribute(Const.SESSION_USER);
		return user.getId();
	}

	public User getCurrentUser(){
		User user = (User)getRequest().getSession().getAttribute(Const.SESSION_USER);
		return user;
	}

	public User getSchoolId(){
		User user = (User)getRequest().getSession().getAttribute(Const.SESSION_USER);
		return user;
	}

	public String getCurrentName(){
		User user = (User)getRequest().getSession().getAttribute(Const.SESSION_USER);
		return user.getName();
	}
	public Set<String> getCurrentUserMenus(){
		Set<String> currentUserMenus = (Set<String>) getRequest().getSession().getAttribute(Const.SESSION_MENULIST);
		return currentUserMenus;
	}
}
