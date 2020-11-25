package com.jd.em.base.controller.system.login;

import com.jd.em.base.config.BaseController;
import com.jd.em.base.domain.PageData;
import com.jd.em.base.domain.User;
import com.jd.em.base.mapper.UserMapper;
import com.jd.em.base.service.system.user.RoleService;
import com.jd.em.base.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author KING
 *
 * <p>Description: 登录</p>
 *
 * 2019年1月29日
 *
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	private static  final JacksonMapper MAPPER = new JacksonMapper(("yyyy-MM-dd HH:mm:ss"));

	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleService roleService;

	@PostMapping("login")
	@ResponseBody
	public String login(){
		PageData pd = this.getPageData();
		if(!StringUtils.isEmpty(pd.getString("username"))&&!StringUtils.isEmpty(pd.getString("password"))){
			pd.put("password", MD5.md5(pd.getString("password")));
			User user = userMapper.login(pd);
			if(null!=user){
				Set<String> menus = this.roleService.findAllMenuUrlByByRole(user.getRoleId());
				user.setPermissions(menus);
				this.getSession().setAttribute(Const.SESSION_ROLE_RIGHTS, user.getRoleId());
				this.getSession().setAttribute(Const.SESSION_MENULIST, menus);
				this.getSession().setAttribute(Const.SESSION_USER, user);
				return MAPPER.toJson(new JsonResult(HttpStatusCodes.CREATED, ""));
			}else{
				return MAPPER.toJson(new JsonResult(HttpStatusCodes.SERVERERROR, "用户名或者密码错误"));
			}
		}else{
			return MAPPER.toJson(new JsonResult(HttpStatusCodes.SERVERERROR, "用户名或者密码错误"));
		}
	}
		
	
	@RequestMapping("tologin")
	public String toLogin(){
		return "system/login";
	}
	
	@RequestMapping("logout")
	public String logout(){
		this.getSession().setAttribute(Const.SESSION_USER, null);
		return "system/login";
	}
}
