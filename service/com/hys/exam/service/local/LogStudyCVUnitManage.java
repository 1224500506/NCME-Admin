package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.LogStudyCVUnitVideo;
import com.hys.framework.service.BaseService;


/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVUnitManage.java
 *     
 *     Description       :   学习记录(单元)Service接口
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-16                                   张建国	               Created
 ********************************************************************************
 */

public interface LogStudyCVUnitManage extends BaseService {

	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    LogStudyCVSet
	 * @return   void
	 * 方法说明： 添加学习记录(项目)
	 */
	public Long addLogStudyCVUnit(LogStudyCVUnit log);
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记录是否存在
	 */
	public List<LogStudyCVUnit> queryLogStudyCVUnitByUnitId(LogStudyCVUnit cvs);
	
	/**
	 * @author   张建国
	 * @param    Long
	 * @return   List
	 * 方法说明： 根据单元id查询记修改单元学习记录
	 */
    public void updLogStudyCVUnit(Long unitId, Long userId);
  
    
    public void updLogStudyCVUnitById(LogStudyCVUnit cvs);
    
    /**
     * @author taoliang
     * @param record
     * @return
     */
    public Long addLogStudyCVUnitVideo(LogStudyCVUnitVideo record);
    
    /**
     * @author taoliang
     * @param recordList
     * @desc 批量初始化回放或者点播单元学习记录
     */
    public void addBatchLogStudyCVUnit(List<LogStudyCVUnit> recordList);
    
    /**
     * @author taoliang
     * @param record
     * @return
     * @desc 查询学习单元记录--通用方法
     */
    public List<LogStudyCVUnit> getLogStudyCVUnitRecord(LogStudyCVUnit record);
    
    /**
     * @author taoliang
     * @param recordList
     * @desc 批量初始化回放或者点播单元学习记录情况表
     */
    public void addBatchLogStudyCVUnitVideo(List<LogStudyCVUnitVideo> recordList);
}
