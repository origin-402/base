package com.jd.em.base.service.system.user;

import com.jd.em.base.domain.PageData;
import com.jd.em.base.mapper.RoleMapper;
import com.jd.em.base.mapper.UserMapper;
import com.jd.em.base.utils.HttpStatusCodes;
import com.jd.em.base.utils.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


/**
 * @author KING
 *
 * <p>Description: roleservice</p>
 *
 * 2017年5月2日
 *
 */
@Service("roleService")
public class RoleService {

	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserMapper userMapper;

	/**
	 * 权限树
	 * @return
	 */
	public JsonResult findAllMenuForZtree(){

		return new JsonResult(HttpStatusCodes.OK, "", roleMapper.findAllMenuForZtree());
	}

	/**
	 * 保存角色
	 * @param pd
	 * @return
	 */
	@Transactional
	public JsonResult saveRole(PageData pd) {
		List<PageData> roleList = this.roleMapper.findRoleByName(null, pd.getString("name"));
		if (null != roleList && roleList.size() > 0) {
			return new JsonResult(HttpStatusCodes.SERVERERROR, "角色名称已经存在，操作失败");
		}
		try {
			roleMapper.saveRole(pd);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(HttpStatusCodes.SERVERERROR, "保存异常");
		}
		String menuL = pd.getString("menus");
		if(!StringUtils.isEmpty(menuL)){
			String[] menus = menuL.split(",");
			for (String string : menus) {
				pd.put("menu_id", string);
				try {
					roleMapper.saveRoleAndMenu(pd);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("保存角色对应权限异常");
				}
			}
		}
		return new JsonResult(HttpStatusCodes.CREATED, "操作成功");
	}

	/**
	 * 修改角色
	 * @param pd
	 * @return
	 */
	@Transactional
	public JsonResult updateRole(PageData pd) {
		List<PageData> roleList = this.roleMapper.findRoleByName(pd.getString("id"), pd.getString("name"));
		if (null != roleList && roleList.size() > 0) {
			return new JsonResult(HttpStatusCodes.SERVERERROR, "角色名称已经存在，修改失败");
		}
		try {
			this.roleMapper.updateRole(pd);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(HttpStatusCodes.SERVERERROR, "修改角色异常");
		}
		String menuL = pd.getString("menus");
		this.roleMapper.deletePermissionsByRoleId(pd.getString("id"));
		if(!StringUtils.isEmpty(menuL)){
			String[] menus = menuL.split(",");
			for (String string : menus) {
				pd.put("menu_id", string);
				pd.put("roleid", pd.getString("id"));
				try {
					roleMapper.saveRoleAndMenu(pd);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("保存角色对应权限异常");
				}
			}
		}
		return new JsonResult(HttpStatusCodes.CREATED, "修改成功");
	}

	/**
	 * 根据角色id查询相应权限列表
	 * @param role
	 * @return
	 */
	public Set<String> findAllMenuUrlByByRole(String role) {
		return roleMapper.findAllMenuUrlByByRole(role);
	}

	/**
	 * 根据角色id查询相应权限列表
	 * @param id
	 * @return
	 */
	public List<String> findAllMenuByRoleId(String id) {
		return roleMapper.findAllMenuByRoleId(id);
	}

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<PageData> findAllRoles() {
		return this.roleMapper.findAllRoles();
	}

	@Transactional
	public JsonResult deleteRole(String id) {
		List<PageData> userList = this.userMapper.findUserByRoleId(id);
		if (null != userList && userList.size() > 0) {
			return new JsonResult(HttpStatusCodes.SERVERERROR, "角色下面有用户，无法删除");
		}
		this.roleMapper.deletePermissionsByRoleId(id);
		this.roleMapper.deleteRoleById(id);
		return new JsonResult(HttpStatusCodes.NO_CONTENT, "删除成功");
	}

	public JsonResult findRoleById(String id) {
		PageData pageData = roleMapper.findRoleById(id);
		if (null == pageData) {
			return new JsonResult(HttpStatusCodes.SERVERERROR, "角色查询异常");
		}
		List<String> permissions = this.findAllMenuByRoleId(id);
		pageData.put("permissions", permissions.toArray());
		return new JsonResult(HttpStatusCodes.OK, "查询成功", pageData);
	}
}
