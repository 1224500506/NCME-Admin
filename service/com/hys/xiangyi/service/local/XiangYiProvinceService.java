package com.hys.xiangyi.service.local;

import java.util.List;

import com.hys.exam.model.system.SystemSiteVO;
import com.hys.xiangyi.model.XiangYiProvince;
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
public interface XiangYiProvinceService {
	//得到省份列表
	public List<XiangYiProvince> getXiangYiProvinceList();
	
	//根据省份代码获取省份
	public XiangYiProvince getXiangYiProvinceByProvinceCode(String provinceCode);
}
