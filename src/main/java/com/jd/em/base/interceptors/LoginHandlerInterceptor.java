package com.jd.em.base.interceptors;

import com.jd.em.base.utils.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Set;


/**
 * @author KING
 *
 * <p>Description: 拦截器</p>
 *
 * 2019年1月21日
 *
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else{
			HttpSession session = request.getSession();
			if(null!=session.getAttribute(Const.SESSION_USER)){
				if("/index".equals(path)){
					return true;
				}
				if ("/".equals(path)) {
					response.sendRedirect(request.getContextPath() + "/index");
					return true;
				}
				Set<String> hasMenus = (Set<String>) session.getAttribute(Const.SESSION_MENULIST);
				if(hasMenus!=null&&hasMenus.size()>0){
					for (Iterator<String> iterator = hasMenus.iterator(); iterator.hasNext();) {
						String string = (String) iterator.next();
						if(path.split(".do")[0].equals(string)){
							return true;
						}
					}
				}
				response.sendRedirect(request.getContextPath() + "/index");
				return false;//没有权限，应该跳转至相应页面。
			}else{
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;
			}
		}
	}
}
