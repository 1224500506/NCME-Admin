package com.hys.xiangyi.dao.local.jdbc;

import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.jdbc.AbstractJDBCDAO;
import com.hys.exam.model.SystemSite;
import com.hys.xiangyi.dao.local.XiangYiProvinceDAO;
import com.hys.xiangyi.model.XiangYiProvince;

/**
 * 
 * 标题：乡医模块
 * 
 * 作者：zxl 2017-06-02
 * 
 * 描述：省份信息 JdbcDao
 * 
 * 说明:
 */
public class XiangYiProvinceJDBCDAO extends AbstractJDBCDAO implements XiangYiProvinceDAO {
	public XiangYiProvinceJDBCDAO() {
		super(XiangYiProvince.class, Constants.TABLE_XIANGYI_PROVINCE);
	}
	
	@Override //取得所有省份信息
	public List<XiangYiProvince>  getXiangYiProvinceList() {
		String sql = "select * from xiangyi_province";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
	}

	@Override//根据省份代码获取省份
	public XiangYiProvince getXiangYiProvinceByProvinceCode(String provinceCode) {
		String sql= "select * from xiangyi_province where province_code=" + provinceCode;
		List<XiangYiProvince> provinceList = getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(XiangYiProvince.class));
		return provinceList.get(0);
	}
	
}