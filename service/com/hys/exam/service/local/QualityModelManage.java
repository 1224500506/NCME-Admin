package com.hys.exam.service.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.QualityModel;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface QualityModelManage extends BaseService {
	
	List<QualityModel> getQualityModelList(QualityModel qualityModel);
	
	List<QualityModel> getNextQualityModelList(QualityModel qualityModel);
	
	boolean addQualityModel(QualityModel qualityModel);
	
	boolean deleteQualityModel(QualityModel qualityModel);
	
	boolean updateQualityModel(QualityModel qualityModel);

	List<PropUnit> getZutiListByType();

	List<QualityModel> getQualityModelListByZutiIds(List<Long> idList);

	List<QualityModel> getDeepQualityModelListFromImageIds(long[] image_ids);

	void getQualityPageList(PageList pl, QualityModel qualityModel);

	void getNextQualityModelList(PageList pl, QualityModel qualityModel);

	boolean compareQualityModel(QualityModel qualityModel);
	
	List<QualityModel> getProjectScheduleUnitQualityModelLevelOneListByUserImage(Map args); //根据项目的人物画像获取能力模型一级，YHQ 2017-02-23
	List<QualityModel> getProjectScheduleUnitQualityModelLevelTwoListByLevelOne(Map args); //根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级，YHQ 2017-02-23
	List<QualityModel> getProjectScheduleUnitQualityModelLevelThreeListByLevelTwoAndUserImage(Map args); //根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级以后，再根据能力模型二级和项目的人物画像获取第三级能力模型，YHQ 2017-02-23
	List<QualityModel> getProjectScheduleUnitQualityModelLevelFourListByLevelThree(Map args); //根据项目的人物画像获取能力模型一级以后，再根据能力模型一级获取能力模型二级以后，再根据能力模型二级和项目的人物画像获取第三级能力模型以后，再根据第三级获取第四级，YHQ 2017-02-23
	boolean insertCvUnitRefQualityByGroupClass(QualityModel qualityModel); //课程管理下课程单元富文本编辑中加入素材，YHQ 2017-02-24
	boolean updateCvUnitRefQualityByGroupClass(QualityModel qualityModel); //课程管理下课程单元富文本编辑中更新素材，YHQ 2017-02-25
}
