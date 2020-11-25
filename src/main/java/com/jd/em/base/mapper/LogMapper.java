package com.jd.em.base.mapper;

import com.jd.em.base.domain.Page;
import com.jd.em.base.domain.PageData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author KING
 *
 * <p>Description: LogMapper</p>
 *
 * 2019年1月21日
 *
 */
@Mapper
public interface LogMapper {

	/**
	 * 保存日志
	 * @param pageData
	 */
	void saveLog(PageData pageData) throws Exception;

	/**
	 * 日志列表
	 * @param page
	 * @return
	 */
	List<PageData> listPageLog(Page page);
}
