package com.hys.exam.struts.form;


import org.apache.struts.upload.FormFile;

import com.hys.exam.model.PeixunOrg;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 form
 * 
 * 说明:
 */
public class OrgForm extends BaseForm{

	
	private static final long serialVersionUID = 189625609171665011L;
	
	private String method ;
	
	private PeixunOrg model = new PeixunOrg();
	
	private FormFile mafFile;
	
	private FormFile matFile1;
	
	//logo图片路径
	private String logoPath;
	
	//机构图片
	private String orgPath;

	public FormFile getMafFile() {
		return mafFile;
	}
	public void setMafFile(FormFile mafFile) {
		this.mafFile = mafFile;
	}
	public FormFile getMatFile1() {
		return matFile1;
	}
	public void setMatFile1(FormFile matFile1) {
		this.matFile1 = matFile1;
	}
	public PeixunOrg getModel() {
		return model;
	}
	public void setModel(PeixunOrg model) {
		this.model = model;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

}
