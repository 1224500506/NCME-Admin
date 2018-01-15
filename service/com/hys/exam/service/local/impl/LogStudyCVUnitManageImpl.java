package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.LogStudyCVUnitManageDAO;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.LogStudyCVUnitVideo;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.framework.service.impl.BaseMangerImpl;


/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitManageImpl.java
 *     
 *     Description       :   学习记录(单元)Service实现类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-16                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVUnitManageImpl extends BaseMangerImpl implements LogStudyCVUnitManage{
	
	private LogStudyCVUnitManageDAO localLogStudyCVUnitDAO;

	public LogStudyCVUnitManageDAO getLocalLogStudyCVUnitDAO() {
		return localLogStudyCVUnitDAO;
	}

	public void setLocalLogStudyCVUnitDAO(LogStudyCVUnitManageDAO localLogStudyCVUnitDAO) {
		this.localLogStudyCVUnitDAO = localLogStudyCVUnitDAO;
	}

	/**
	 * @author   张建国
	 * @time     2017-01-16
	 * @param    LogStudyCVUnit
	 * @return   void
	 * 方法说明： 添加学习记录(单元)
	 */
	@Override
	public Long addLogStudyCVUnit(LogStudyCVUnit log) {
		return localLogStudyCVUnitDAO.addLogStudyCVUnit(log);
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记录是否存在
	 */
	public List<LogStudyCVUnit> queryLogStudyCVUnitByUnitId(LogStudyCVUnit cvs){
		return localLogStudyCVUnitDAO.queryLogStudyCVUnitByUnitId(cvs);
	}
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记修改单元学习记录
	 */
    public void updLogStudyCVUnit(Long unitId, Long userId){
    	localLogStudyCVUnitDAO.updLogStudyCVUnit(unitId, userId);
    }
    

	@Override
	public void updLogStudyCVUnitById(LogStudyCVUnit cvs) {
		// TODO Auto-generated method stub
		  localLogStudyCVUnitDAO.updLogStudyCVUnitById(cvs);
	}

	@Override
	public Long addLogStudyCVUnitVideo(LogStudyCVUnitVideo record) {
		return localLogStudyCVUnitDAO.addLogStudyCVUnitVideo(record);
	}

	@Override
	public void addBatchLogStudyCVUnit(List<LogStudyCVUnit> recordList) {
		localLogStudyCVUnitDAO.addBatchLogStudyCVUnit(recordList);
	}

	@Override
	public List<LogStudyCVUnit> getLogStudyCVUnitRecord(LogStudyCVUnit record) {
		return localLogStudyCVUnitDAO.getLogStudyCVUnitRecord(record);
	}

	@Override
	public void addBatchLogStudyCVUnitVideo(List<LogStudyCVUnitVideo> recordList) {
		localLogStudyCVUnitDAO.addBatchLogStudyCVUnitVideo(recordList);
	}
}
