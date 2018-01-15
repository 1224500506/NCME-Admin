package com.hys.xiangyi.model;

import com.hys.exam.model.BaseObject;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-02
 * 
 * 描述：乡医省份信息
 * 
 * 说明:
 */
public class XiangYiProvince extends BaseObject{

	private static final long serialVersionUID = -5024686004693337176L;

	/**
	 * 主键
	 */
	private Integer provinceId;

	/**
	 * 省份名称
	 */
	private String provinceName;

	/**
	 * 排序
	 */
	private Integer orderNumber;

	/**
	 * 省份代码
	 */
	private String provinceCode;

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}
