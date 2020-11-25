package com.jd.em.base.service.system.user;

import com.jd.em.base.domain.Page;
import com.jd.em.base.domain.PageData;
import com.jd.em.base.mapper.UserMapper;
import com.jd.em.base.utils.HttpStatusCodes;
import com.jd.em.base.utils.JsonResult;
import com.jd.em.base.utils.MD5;
import com.jd.em.base.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author KING
 *
 * <p>Description: userservice</p>
 *
 * 2019年1月29日
 *
 */
@Service("userService")
public class UserService {

	@Resource
	private UserMapper userMapper;
	/**
	 * 列表查询
	 * @param page
	 * @return
	 */
	public List<PageData> listPageUser(Page page) {
		PageData pageData = page.getPd();
		page.setPd(pageData);
		return userMapper.listPageUser(page);
	}

	/**
	 * 保存用户
	 * @param pageData
	 * @throws Exception
	 */
	@Transactional
	public JsonResult saveUser(PageData pageData) {
		int count = this.userMapper.countByUserName(null, pageData.getString("username"));
		if (count > 0) {
			return new JsonResult(HttpStatusCodes.SERVERERROR, "登录名已经存在");
		}
		String id = UuidUtil.get32UUID();
		pageData.put("id", id);
		pageData.put("password", MD5.md5(pageData.getString("password")));
		try {
			userMapper.saveUser(pageData);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(HttpStatusCodes.SERVERERROR, "用户创建异常");
		}
		return new JsonResult(HttpStatusCodes.CREATED, "用户创建成功");
	}


	/**
	 * id查询
	 * @throws Exception
	 */
	public JsonResult findById(String id) {
		PageData pageData = new PageData();
		PageData user = this.userMapper.findById(id);
		pageData.put("user", user);
		return new JsonResult(HttpStatusCodes.OK, "查询成功", pageData);
	}

	/**
	 * 修改
	 * @throws Exception
	 */
	public JsonResult updateUser(PageData pageData) {
		try {
			userMapper.updateUser(pageData);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(HttpStatusCodes.SERVERERROR, "修改异常");
		}
		return new JsonResult(HttpStatusCodes.CREATED, "修改成功");
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public JsonResult deleteUser(String id, String userId) {
		this.userMapper.deleteUser(id, userId);
		return new JsonResult(HttpStatusCodes.NO_CONTENT, "删除成功");
	}


	/**
	 * 修改密码
	 * @param pageData
	 * @throws Exception 
	 */
	public JsonResult updatePassword(PageData pageData) {
		if(StringUtils.isEmpty(pageData.getString("password"))){
			return new JsonResult(HttpStatusCodes.SERVERERROR, "缺少参数");
		}
		pageData.put("password", MD5.md5(pageData.getString("password")));
		try {
			this.userMapper.updatePassword(pageData);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(HttpStatusCodes.SERVERERROR, "修改失败");
		}
		return new JsonResult(HttpStatusCodes.OK, "修改成功");
	}


}
