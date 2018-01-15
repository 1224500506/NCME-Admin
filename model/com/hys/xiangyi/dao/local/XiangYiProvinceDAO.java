package com.hys.xiangyi.dao.local;

import java.util.List;

import com.hys.xiangyi.model.XiangYiProvince;


/**
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-02
 * 
 * 描述：乡医省份信息 dao
 * 
 * 说明:
 */
public interface XiangYiProvinceDAO {
	public List<XiangYiProvince>  getXiangYiProvinceList();
	public XiangYiProvince getXiangYiProvinceByProvinceCode(String provinceCode);
}