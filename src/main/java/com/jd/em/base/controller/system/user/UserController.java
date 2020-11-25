package com.jd.em.base.controller.system.user;


import com.alibaba.fastjson.JSONObject;
import com.jd.em.base.aop.JdController;
import com.jd.em.base.config.BaseController;
import com.jd.em.base.config.ConfigBeanValue;
import com.jd.em.base.domain.Page;
import com.jd.em.base.domain.PageData;
import com.jd.em.base.domain.User;
import com.jd.em.base.service.system.user.RoleService;
import com.jd.em.base.service.system.user.UserService;
import com.jd.em.base.utils.HttpStatusCodes;
import com.jd.em.base.utils.JacksonMapper;
import com.jd.em.base.utils.JsonResult;
import com.jd.em.base.utils.MD5;
import com.jd.em.base.utils.sms.demo.SmsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author KING
 *
 * <p>Description: 后台管理账户</p>
 *
 * 2019年1月29日
 *
 */
@Controller
@RequestMapping("/system_user")
public class UserController extends BaseController {
	
	private static final JacksonMapper MAPPER = new JacksonMapper("yyyy-MM-dd HH:mm:ss");
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Autowired
	private ConfigBeanValue configBeanValue;
	/**
	 * 用户列表
	 * @param model
	 * @return
	 */
	@JdController(description="用户管理页面")
	@RequestMapping("/user_manage")
	public String userList(Model model){
		model.addAttribute("roleList", roleService.findAllRoles());

		//短信测试
		JSONObject res = SmsSend.sendMsg("测试","15653253155",configBeanValue);
		System.out.println(res);

		return "system/user/user_list";
	}
	
	@JdController(description="用户列表")
	@GetMapping("/listUser")
	@ResponseBody
	public String listUser(HttpServletRequest request, Page page){
		PageData pageData = this.getPageData();
		page.setPd(pageData);
		return MAPPER.toDataTablesPageJson(userService.listPageUser(page), page.getTotalResult(), request.getParameter("sEcho"));
	}
	
	/**
	 * 添加用户
	 * @param
	 * @return
	 */
	@JdController(description="保存用户")
	@PostMapping("/saveUser")
	@ResponseBody
	public String saveUser(){
		PageData pageData = this.getPageData();
		pageData.put("created_user", this.getCurrentUserId());
		return MAPPER.toJson(userService.saveUser(pageData));
	}
	
	/**
	 * ID查询用户
	 */
	@JdController(description="查看用户详情")
	@GetMapping("/findUserById")
	@ResponseBody
	public String findUserById(String id) {
		return MAPPER.toJson(userService.findById(id));
	}
	
	/**
	 * 修改信息
	 */
	@JdController(description="修改用户信息")
	@PostMapping("/updateUser")
	@ResponseBody
	public String updateUser() {
		PageData pageData = this.getPageData();
		return MAPPER.toJson(userService.updateUser(pageData));
	}
	
	@JdController(description="删除用户")
	@DeleteMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(String id) {
		return MAPPER.toJson(userService.deleteUser(id, this.getCurrentUserId()));
	}
	
	
	@JdController(description="重置密码")
	@RequestMapping(value="updatePassword", method= RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public String updatePassword() {
		PageData pageData = this.getPageData();
		return MAPPER.toJson(this.userService.updatePassword(pageData));
	}
	
	@JdController(description="修改密码页面")
	@RequestMapping("/exclude/password_edit")
	public String password_edit() {
		return "system/user/user_password_edit";
	}
	
	@JdController(description="修改密码")
	@RequestMapping(value="/exclude/resetPassword", method= RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public String resetPassword() {
		PageData pageData = this.getPageData();
		pageData.put("id", this.getCurrentUserId());
		//查询原始密码是否正确
		User user = this.getCurrentUser();
		if(!StringUtils.isEmpty(pageData.getString("password")) && !StringUtils.isEmpty(pageData.getString("oldPassword"))){
			pageData.put("oldPassword", MD5.md5(pageData.getString("oldPassword")));
			if (StringUtils.equals(user.getPassword(), pageData.getString("oldPassword"))) {
				return MAPPER.toJson(this.userService.updatePassword(pageData));
			} else {
				return MAPPER.toJson(new JsonResult(HttpStatusCodes.SERVERERROR, "原始密码错误"));
			}
		} else {
			return MAPPER.toJson(new JsonResult(HttpStatusCodes.SERVERERROR, "缺少参数"));
		}
	}
}
