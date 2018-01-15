package com.hys.exam.service.local;

import com.hys.exam.model.CVUnit;
import com.hys.framework.service.BaseService;

/**
 * 
 * 
 * CVUnitManage
 * 创建人:程宏业 时间：2017-3-6-下午3:46:16 
 * @version 1.0.0
 *
 */
public interface CVUnitManage extends BaseService {
	
/**
 * 	
 * 查询单元信息
 * 方法名：findCvunit
 * 创建人：程宏业
 * 时间：2017-3-6-下午3:47:15 
 * 手机:15210211487
 * @param cvu
 * @return CVUnit
 * @exception 
 * @since  1.0.0
 */
public CVUnit findCvunit(CVUnit cvu);
	
	
	
	
	

}
