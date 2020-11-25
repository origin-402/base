package com.jd.em.base.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author KING
 *
 * <p>Description: json_util</p>
 *
 * 2019年1月21日
 *
 */
public class JacksonMapper extends ObjectMapper {

	private static final long serialVersionUID = 27476334584914123L;
	
	private static final Logger LOGGER = LogManager.getLogger();

	public JacksonMapper() {
	}


	/**
	 * 带是否时间格式化的构造方法
	 * 
	 * @param dateFormat
	 *          格式如:yyyy-MM-dd
	 * 
	 */
	public JacksonMapper(String dateFormat) {
		setDateFormat(new SimpleDateFormat(dateFormat));
	}


	/**
	 * 把Object转化为JSON字符串
	 * 
	 * @param object
	 *          can be pojo entity,list,map etc.
	 */
	public String toJson(Object object) {
		try {
			return this.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getStackTrace(), e);
			throw new RuntimeException("对象转化为JSON时,解析对象错误");
		}
	}

	/**
	 * JSON转List
	 * 
	 * @param json
	 *          json字符串
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> toList(String json) {
		try {
			return this.readValue(json, List.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析json错误");
		}
	}

	/**
	 * json转换为java对象
	 * 
	 * @param json
	 *          字符串
	 * @param clazz
	 *          对象的class
	 * @return 返回对象
	 */
	public <T> T toObject(String json, Class<T> clazz) {
		try {
			return this.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("JSON转化为对象时,解析JSON错误");
		}
	}

	/**
	 * 返回分页列表的JSON格式
	 * 
	 * @param list
	 * @param totalCounts
	 * 
	 * @return
	 */
	public String toPageJson(List<?> list, Integer totalCounts) {
		StringBuffer sb = new StringBuffer("{\"success\":true,\"totalCounts\":").append(totalCounts).append(",\"result\":");
		sb.append(toJson(list));
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 返回带有结果的JSON格式
	 * 
	 * @param object
	 *          can be pojo entity,list,map etc.
	 * 
	 * @return {success:true,data:['...']}
	 */
	public String toDataJson(Object object) {
		StringBuffer sb = new StringBuffer("{\"success\":true,\"data\":");
		sb.append(toJson(object));
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 返回带有结果的JSON格式
	 * 
	 * @param object
	 *          can be pojo entity,list,map etc.
	 * 
	 * @return {success:true,result:['...']}
	 */
	public String toResultJson(Object object) {
		StringBuffer sb = new StringBuffer("{success:true,result:");
		sb.append(toJson(object));
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 返回带有结果的JSON格式
	 * 
	 * @param object
	 *          can be pojo entity,list,map etc.
	 * 
	 * @return {success:true,aaData:['...']}
	 */
	public String toDataTablesJson(Object object) {
		StringBuffer sb = new StringBuffer("{\"data\":");
		sb.append(toJson(object));
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 返回带有结果的JSON格式
	 * 
	 * @param object
	 *          can be pojo entity,list,map etc.
	 * 
	 * @return {success:true,aaData:['...']}
	 */
	public String toWebDataTablesJson(Object object) {
		StringBuffer sb = new StringBuffer("{\"success\":true,\"aaData\":");
		sb.append(toJson(object));
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 返回分页列表的JSON格式
	 * 
	 * @param list
	 * @param totalCounts
	 * 
	 * @return
	 */
	public String toDataTablesPageJson(List<?> list, Integer totalCounts, String sEcho) {
		StringBuffer sb = new StringBuffer("{\"success\":true,\"iTotalRecords\":").append(totalCounts).append(",\"iTotalDisplayRecords\":").append(totalCounts).append(",\"sEcho\":")
				.append(sEcho).append(",\"aaData\":");
		sb.append(toJson(list));
		sb.append("}");
		return sb.toString();
	}

}
