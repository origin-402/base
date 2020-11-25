package com.jd.em.base.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Set;


/**
 * @author KING
 *
 * <p>Description: user</p>
 *
 * 2019年1月21日
 *
 */
@Alias(value = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3402454954080154160L;

	private String id;
	
	private String username;
	
	private String name;
	
	private String password;

	private Set<String> permissions;
	
	private String picture;

	private String thumb_img_url;
	
	private String phone;
	
	private String roleId;
	
	private String organId;

	private String type;

	private String rongyun_token;

	private String organName;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	
	public boolean hasPermissions(String code){
		if(this.permissions.contains(code)){
			return true;
		}else{
			return false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rongyun_token
	 */
	public String getRongyun_token() {
		return rongyun_token;
	}

	/**
	 * @param rongyun_token the rongyun_token to set
	 */
	public void setRongyun_token(String rongyun_token) {
		this.rongyun_token = rongyun_token;
	}

	public String getThumb_img_url() {
		return thumb_img_url;
	}

	public void setThumb_img_url(String thumb_img_url) {
		this.thumb_img_url = thumb_img_url;
	}

	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
}
