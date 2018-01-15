package com.hys.exam.sessionfacade.local.impl;

import java.util.ArrayList;
import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseType;
import com.hys.exam.model.SystemSiteCourseTypeExam;
import com.hys.exam.service.local.SystemSiteCourseTypeManage;
import com.hys.exam.service.local.SystemSiteManage;
import com.hys.exam.sessionfacade.local.SystemSiteFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-28
 * 
 * 描述：站点信息 facadeImpl
 * 
 * 说明:
 */
public class SystemSiteFacadeImpl extends BaseSessionFacadeImpl implements SystemSiteFacade {
	
	private SystemSiteManage systemSiteManage ;
	private SystemSiteCourseTypeManage systemSiteCourseTypeManage ;

	
	@Override  
	public List<SystemSite> getListByItem(SystemSite item){
		try {
			List<SystemSite> list = systemSiteManage.getListByItem(item) ;
			
			return getSystemSiteCourseTypeToSite(list);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	//查询站点课程分类明细,通过站点ID
	private List<SystemSite> getSystemSiteCourseTypeToSite(List<SystemSite> list){
		if(list!=null && list.size()>0){
			for (SystemSite site : list){
				SystemSiteCourseType courseType = new SystemSiteCourseType();
				courseType.setSiteId(site.getId());
				List<SystemSiteCourseType> siteCourseTypes = systemSiteCourseTypeManage.getListByItem(courseType);
				
				//拼接课程分类ID及NAME
				if(siteCourseTypes!=null && siteCourseTypes.size()>0){
					String courseTypeNames= ""	;
					String courseTypeIds = "";
					for (SystemSiteCourseType type : siteCourseTypes){
						if (!courseTypeIds.equals("")){
							courseTypeNames += " | ";
							courseTypeIds += ",";
						}
						courseTypeNames += type.getCourseTypeName();
						courseTypeIds 	+= type.getCourseTypeId();
					}
					site.setCourseTypeNames(courseTypeNames);
					site.setCourseTypeIds(courseTypeIds);
				}
				
				site.setSiteCourseTypes(siteCourseTypes);
			}
		}
		return list;
	}
	
	@Override
	public void querySystemSiteList(Page<SystemSite> page,
			SystemSite item) {
		try {
			systemSiteManage.querySystemSiteList(page, item);
			page.setList(getSystemSiteCourseTypeToSite(page.getList()));
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}

	}

	@Override
	public Integer save(SystemSite item) {
		try {
			return systemSiteManage.save(item);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
		
	}
	
	@Override
	public SystemSite getItemById(Long id) {
		try {
			SystemSite site = systemSiteManage.getItemById(id);
			List<SystemSite> list = new ArrayList<SystemSite>();
			list.add(site);
			return getSystemSiteCourseTypeToSite(list).get(0);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int update(SystemSite site) {
		try {
			/*//分隔符成组_组装对象
			String courseTypeIds = site.getCourseTypeIds();
			String[] courseTypeIdArr = StringUtils.split(courseTypeIds, ",");
			//先删后增
			if (courseTypeIdArr.length>0 && systemSiteCourseTypeManage.deleteBySiteId(site.getId())){
				for ( String  courseTypeId : courseTypeIdArr){
					SystemSiteCourseType obj = new SystemSiteCourseType();
					obj.setCourseTypeId(LongUtil.parseLong(courseTypeId));
					obj.setSiteId(site.getId());
					systemSiteCourseTypeManage.save(obj);
				}
			}*/
			return systemSiteManage.update(site);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}
	
	@Override
	public int deleteList(long[] ids,String idColName) {
		try {
			return systemSiteManage.deleteList(ids,idColName);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public int delete(long id, String idColName) {
		return systemSiteManage.delete(id,idColName);
	}

	public void setSystemSiteManage(SystemSiteManage systemSiteManage) {
		this.systemSiteManage = systemSiteManage;
	}

	public void setSystemSiteCourseTypeManage(
			SystemSiteCourseTypeManage systemSiteCourseTypeManage) {
		this.systemSiteCourseTypeManage = systemSiteCourseTypeManage;
	}

	@Override
	public List<ExamExamination> getExamExaminationList(Long siteId,
			Long courseTypeId) {
		try {
			return systemSiteManage
					.getExamExaminationList(siteId, courseTypeId);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	@Override
	public void addSystemSiteCourseTypeExamList(
			List<SystemSiteCourseTypeExam> list) {
		try {
			systemSiteManage.addSystemSiteCourseTypeExamList(list);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000, e);
		}
	}

	/**
	 * 新增站点的时候，查询改站点是否存在
	 * @param site
	 * @return
	 */
	@Override
	public List<SystemSite> getListByItem(String site, Long id) {
		try {
			List<SystemSite> list = systemSiteManage.getListByItem(site, id) ;
			
			return getSystemSiteCourseTypeToSite(list);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
}