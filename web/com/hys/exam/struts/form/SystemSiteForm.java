package com.hys.exam.struts.form;

import java.util.ArrayList;

import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseType;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemSiteForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 189625609171665011L;
	private String method ;
	
	private SystemSite model = new SystemSite();
	
	/**
	 * 取得list对象
	 *
	 */
	private ArrayList<SystemSiteCourseType> siteCourseTypes = new ArrayList<SystemSiteCourseType>(); //对应页面上的列表
	//提交页面时动态构造list对象的核心方法
	public SystemSiteCourseType getSystemSiteCourseTypeItem(int i) {
		if (siteCourseTypes == null)
			siteCourseTypes = new ArrayList<SystemSiteCourseType>();
		if (siteCourseTypes.size() <= i) {
			for (int j = siteCourseTypes.size(); j <= i; j++)
				siteCourseTypes.add(new SystemSiteCourseType());
		}
		return (SystemSiteCourseType) siteCourseTypes.get(i);
	}
	
	
	public ArrayList<SystemSiteCourseType> getSiteCourseTypes() {
		return siteCourseTypes;
	}

	public void setSiteCourseTypes(ArrayList<SystemSiteCourseType> siteCourseTypes) {
		this.siteCourseTypes = siteCourseTypes;
	}

	
	public SystemSite getModel() {
		return model;
	}
	public void setModel(SystemSite model) {
		this.model = model;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	 

	
	
}
