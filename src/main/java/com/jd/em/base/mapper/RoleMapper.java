package com.jd.em.base.mapper;

import com.jd.em.base.domain.PageData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;


/**
 * @author KING
 *
 * <p>Description: roleMapper</p>
 *
 * 2019年1月29日
 *
 */
@Mapper
public interface RoleMapper {

	/**
	 * 权限树
	 * @return
	 */
	List<PageData> findAllMenuForZtree();

	/**
	 * 保存角色
	 * @param pageData
	 */
	void saveRole(PageData pageData);

	/**
	 * 保存角色权限
	 * @param pageData
	 */
	void saveRoleAndMenu(PageData pageData);

	/**
	 * 修改角色
	 * @param pageData
	 */
	void updateRole(PageData pageData);
	
	/**
	 * 根据角色id查询权限url列表
	 * @param role
	 * @return
	 */
	Set<String> findAllMenuUrlByByRole(String role);

	/**
	 * 根据角色id查询权限id列表
	 * @param id
	 * @return
	 */
	List<String> findAllMenuByRoleId(String id);

	/**
	 * 角色列表
	 * @return
	 */
	List<PageData> findAllRoles();

	/**
	 * 根据id删除角色
	 * @param id
	 */
	void deleteRoleById(String id);

	/**
	 * 根据名称查询id
	 * @param string 如果id不为null，排除当前id
	 */
	List<PageData> findRoleByName(String id, String name);
	
	/**
	 * 删除角色的权限
	 * @param id
	 */
	void deletePermissionsByRoleId(String id);

	/**
	 * 角色详情
	 * @param pageData
	 * @return
	 */
	PageData findRoleById(String id);
}
