package com.jd.em.base.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;


/**
 * @author KING
 *
 * <p>Description: 暂时没用。。</p>
 *
 * 2019年1月21日
 *
 */
@Alias("menu")
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2340172856291426981L;

	private String id;
	
	private String fid;
	
	private String url;
	
	private String name;
	
	private String type;
	
	private String icon;
	
	private int sort;
	
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
