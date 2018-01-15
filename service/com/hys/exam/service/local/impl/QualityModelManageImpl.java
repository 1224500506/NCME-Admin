package com.hys.exam.service.local.impl;

import java.util.List;
import java.util.Map;

import com.hys.exam.dao.local.QualityModelManageDAO;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.service.local.QualityModelManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class QualityModelManageImpl extends BaseMangerImpl implements
		QualityModelManage {
	
	private QualityModelManageDAO localQualityModelManageDAO;
	
	public QualityModelManageDAO getLocalQualityModelManageDAO() {
		return localQualityModelManageDAO;
	}

	public void setLocalQualityModelManageDAO(QualityModelManageDAO localQualityModelManageDAO) {
		this.localQualityModelManageDAO = localQualityModelManageDAO;
	}

	@Override
	public List<QualityModel> getQualityModelList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getQualityModelList(qualityModel);
	}
	
	@Override
	public List<QualityModel> getNextQualityModelList(QualityModel qualityModel) {
		return localQualityModelManageDAO.getNextQualityModelList(qualityModel);
	}

	@Override
	public boolean addQualityModel(QualityModel qualityModel) {
		return localQualityModelManageDAO.addQualityModel(qualityModel);
	}

	@Override
	public boolean deleteQualityModel(QualityModel qualityModel) {
		return localQualityModelManageDAO.deleteQualityModel(qualityModel);
	}

	@Override
	public boolean updateQualityModel(QualityModel qualityModel) {
		return localQualityModelManageDAO.updateQualityModel(qualityModel);
	}


	@Override
	public List<PropUnit> getZutiListByType() {
		return  localQualityModelManageDAO.getZutiListByType();
	
	}

	@Override
	public List<QualityModel> getQualityModelListByZutiIds(List<Long> idList) {
		return localQualityModelManageDAO.getQualityModelListByZutiIds(idList);
	}

	@Override
	public List<QualityModel> getDeepQualityModelListFromImageIds(
			long[] image_ids) {
		return localQualityModelManageDAO.getDeepQualityModelListFromImageIds(image_ids);
	}

	@Override
	public void getQualityPageList(PageList pl, QualityModel qualityModel) {
		localQualityModelManageDAO.getQualityPageList(pl,qualityModel);
		
	}

	@Override
	public void getNextQualityModelList(PageList pl, QualityModel qualityModel) {
		localQualityModelManageDAO.getNextQualityModelList(pl,qualityModel);
		
	}

	@Override
	public boolean compareQualityModel(QualityModel qualityModel) {
		
		return localQualityModelManageDAO.compareQualityModel(qualityModel);
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelOneListByUserImage(Map args) {
		return localQualityModelManageDAO.getProjectScheduleUnitQualityModelLevelOneListByUserImage(args) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelTwoListByLevelOne(Map args) {
		return localQualityModelManageDAO.getProjectScheduleUnitQualityModelLevelTwoListByLevelOne(args) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级以后，再根据能力模型二级和项目的人物画像获取第三级能力模型，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelThreeListByLevelTwoAndUserImage(Map args) {
		return localQualityModelManageDAO.getProjectScheduleUnitQualityModelLevelThreeListByLevelTwoAndUserImage(args) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-23
	 * 根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级以后，再根据能力模型二级和项目的人物画像获取第三级能力模型以后，再根据第三级获取第四级，YHQ 2017-02-23
	 */
	@Override
	public List<QualityModel> getProjectScheduleUnitQualityModelLevelFourListByLevelThree(Map args) {
		return localQualityModelManageDAO.getProjectScheduleUnitQualityModelLevelFourListByLevelThree(args) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-24
	 * 课程管理下课程单元富文本编辑中加入素材，YHQ 2017-02-24
	 */
	@Override
	public boolean insertCvUnitRefQualityByGroupClass(QualityModel qualityModel) {
		return localQualityModelManageDAO.insertCvUnitRefQualityByGroupClass(qualityModel) ;
	}
	
	/**
	 * @author 杨红强
	 * @time	2017-02-25
	 * 课程管理下课程单元富文本编辑中更新素材，YHQ 2017-02-25
	 */
	@Override
	public boolean updateCvUnitRefQualityByGroupClass(QualityModel qualityModel) {
		return localQualityModelManageDAO.updateCvUnitRefQualityByGroupClass(qualityModel) ;
	}

}
