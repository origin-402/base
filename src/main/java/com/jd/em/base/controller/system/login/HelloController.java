package com.jd.em.base.controller.system.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author KING
 *
 * <p>Description: 登录页首页</p>
 *
 * 2017年4月30日
 *
 */
@Controller
@RequestMapping("")
public class HelloController {

	@RequestMapping("index")
	public String index(Model model){
		return "system/index";
	}
	
	@RequestMapping("main")
	public String index(){
		return "system/main";
	}
	
	
}
