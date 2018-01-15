package com.hys.exam.model.system;

import java.io.Serializable;
import java.util.List;

public class SystemMenu  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String system_type;
	private String menu_type;
	private String url;
	private Integer state;
	
	//二级子菜单
	private List<SystemMenu> systemMenuList;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSystem_type() {
		return system_type;
	}
	public void setSystem_type(String system_type) {
		this.system_type = system_type;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<SystemMenu> getSystemMenuList() {
		return systemMenuList;
	}
	public void setSystemMenuList(List<SystemMenu> systemMenuList) {
		this.systemMenuList = systemMenuList;
	}

	
}
