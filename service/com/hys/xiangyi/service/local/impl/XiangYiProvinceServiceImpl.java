package com.hys.xiangyi.service.local.impl;

import java.util.List;

import com.hys.framework.service.impl.BaseMangerImpl;
import com.hys.xiangyi.dao.local.XiangYiProvinceDAO;
import com.hys.xiangyi.model.XiangYiProvince;
import com.hys.xiangyi.service.local.XiangYiProvinceService;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-02
 * 
 * 描述：省份信息 
 * 
 * 说明:
 */
public class XiangYiProvinceServiceImpl extends BaseMangerImpl implements XiangYiProvinceService {
	
	private XiangYiProvinceDAO xiangYiProvinceDAO ;
	

	public XiangYiProvinceDAO getXiangYiProvinceDAO() {
		return xiangYiProvinceDAO;
	}

	public void setXiangYiProvinceDAO(XiangYiProvinceDAO xiangYiProvinceDAO) {
		this.xiangYiProvinceDAO = xiangYiProvinceDAO;
	}



	@Override //获取所有乡医省份信息
	public List<XiangYiProvince> getXiangYiProvinceList() {
		return this.xiangYiProvinceDAO.getXiangYiProvinceList();
	}

	@Override//根据省份代码获取省份
	public XiangYiProvince getXiangYiProvinceByProvinceCode(String provinceCode) {
		return this.xiangYiProvinceDAO.getXiangYiProvinceByProvinceCode(provinceCode);
	}
}
