package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.NcmeCourseCredit;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public interface NcmeCourseCreditFacade {
	/**
	 * 查询授权信息 分页
	 * 
	 * @param page
	 * @param courseware
	 */
	public void getNcmeCourseCreditList(Page<NcmeCourseCredit> page, NcmeCourseCredit ncmeCourseCredit);

	/**
	 * 新增
	 * 
	 * @param n
	 * @return
	 */
	public int addNcmeCourseCredit(NcmeCourseCredit n);

	/**
	 * 删除
	 * 
	 * @param n
	 * @return
	 */
	public int deleteNcmeCourseCredit(NcmeCourseCredit n);

	/**
	 * 新增
	 * 
	 * @param n
	 * @return
	 */
	public void addNcmeCourseCreditList(List<NcmeCourseCredit> list);

	/**
	 * 删除列表
	 * 
	 * @param n
	 * @return
	 */
	public void deleteNcmeCourseCreditList(List<NcmeCourseCredit> list);
}
