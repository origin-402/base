package com.jd.em.base.mapper;

import com.jd.em.base.domain.Page;
import com.jd.em.base.domain.PageData;
import com.jd.em.base.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author KING
 * 
 *
 * <p>Description: userMapper</p>
 *
 * 2019年1月21日
 *
 */
@Mapper
public interface UserMapper {


	/**
	 * 根据id查询用户
	 * @return
	 */
    PageData findById(String id);

    /**
     * 用户列表
     * @param page
     * @return
     */
    List<PageData> listPageUser(Page page);

    /**
     * 登录
     * @param pd
     * @return
     */
	User login(PageData pd);

	/**
	 * 保存用户
	 * @param pageData
	 * @throws Exception
	 */
	void saveUser(PageData pageData) throws Exception;

	/**
	 * 修改用户
	 * @param pageData
	 * @throws Exception
	 */
	void updateUser(PageData pageData) throws Exception;

	/**
	 * 修改用户密码
	 * @param pageData
	 * @throws Exception
	 */
	void updatePassword(PageData pageData) throws Exception;
	
	/**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 */
	List<PageData> findUserByRoleId(String roleId);
	
	/**
	 * 统计用户名数量
	 * @param id  
	 * 			if id not null , exclude this id
	 * @param userName
	 * @return
	 */
	int countByUserName(String id, String userName);

	/**
	 * 删除用户
	 * 		软删除
	 * @param id
	 */
	void deleteUser(String id, String userId);

	/**
	 * 查询所有平台用户id
	 * @return
	 */
	List<String> listAllSystemUserId();

}