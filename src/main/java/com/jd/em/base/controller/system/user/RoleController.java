package com.jd.em.base.controller.system.user;

import com.jd.em.base.aop.JdController;
import com.jd.em.base.config.BaseController;
import com.jd.em.base.domain.PageData;
import com.jd.em.base.service.system.user.RoleService;
import com.jd.em.base.utils.HttpStatusCodes;
import com.jd.em.base.utils.JacksonMapper;
import com.jd.em.base.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author KING
 *
 * <p>Description: 角色</p>
 *
 * 2019年1月29日
 *
 */
@Controller
@RequestMapping("system_role")
public class RoleController extends BaseController {

	private static final JacksonMapper MAPPER = new JacksonMapper("yyyy-MM-dd HH:mm:ss");
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/role_manage")
	public String goAdd(Model model){
		model.addAttribute("roleList", this.roleService.findAllRoles());
		return "system/role/role_manage";
	}
	
	@GetMapping(value="findAllMenuForZtree")
	@ResponseBody
	public String findAllMenuForZtree(){
		return MAPPER.toJson(roleService.findAllMenuForZtree());
	}
	
	@JdController(description="执行了新增角色操作")
	@PostMapping("saveRole")
	@ResponseBody
	public String saveRole(){
		PageData pd = this.getPageData();
		pd.put("roleid", this.get32UUID());
		pd.put("created_time", new Date());
		pd.put("created_user", this.getCurrentUserId());
		return MAPPER.toJson(roleService.saveRole(pd));
	}
	
	@JdController(description="执行了更新角色操作")
	@PostMapping("/updateRole")
	@ResponseBody
	public String updateRole(){
		PageData pd = this.getPageData();
		pd.put("update_time", new Date());
		pd.put("update_user", this.getCurrentUserId());
		return MAPPER.toJson(roleService.updateRole(pd));
	}
	
	@JdController(description="查看角色列表")
	@RequestMapping("role_list")
	public String userList(){
		return "admin/role/role_list";
	}

	@GetMapping("/findRoleById")
	@ResponseBody
	public String findRoleById(String id){
		return MAPPER.toJson(roleService.findRoleById(id));
	}
	
	@RequestMapping("/findAllRole")
	@ResponseBody
	public String findAllRole() {
		return MAPPER.toJson(new JsonResult(HttpStatusCodes.OK, "查询成功", this.roleService.findAllRoles()));
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	@JdController(description="删除角色")
	public String delete(String id) {
		return MAPPER.toJson(this.roleService.deleteRole(id));
	}
}
