package com.hys.exam.service.local;

import java.util.List;
import java.util.Map;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.*;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.service.BaseService;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-22
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPropValManage extends BaseService {
	
	/**
	 * 无关联属性值
	 * @return
	 */
	public Map<String,List<ExamPropVal>> getSysPropValBySysId();
	
	/**
	 * 系统基本属性
	 * @param type
	 * @return
	 */
	public List<ExamPropValTemp> getBasePropVal(Integer type);
	
	
	/**
	 * 关联属性导出
	 * @param type 0:基本ID,1:系统ID
	 * @return
	 */
	public List<ExamPropVal> getBaseRelPorp(int type);
	
	
	/**
	 * 一级学科属性
	 * @return
	 */
	public List<ExamProp> getPropListByType(ExamProp prop);

	/**
	 * 一级学科-添加人物画像模块
	 * 2017.07.06 xuehong
	 */
	public List<ExamProp> getPropListUserImage(ExamProp prop,String flag) throws FrameworkRuntimeException;
	
	/**
	 * 下级属性
	 * @param propId
	 * @return
	 */
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery);
	
	
	/**
	 * 通过属性值取下级属性
	 * @param propValId
	 * @return
	 */
	public List<ExamProp> getNextLevelProp(Long  propValId);
	
	/**
	 * 添加属性
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamProp addPropVal(ExamProp prop) throws Exception;
	
	/**
	 * 删除属性
	 * @param prop
	 */
	public boolean deletePropVal(ExamProp prop);
	
	/**
	 * 修改属性
	 * @param prop
	 * @throws Exception 
	 */
	public void updatePropVal(ExamProp prop) throws Exception;
	
	
	/**
	 * 取属性值类别
	 * @return
	 */
	public List<ExamPropType> getExamPropTypeList();
	
	/**
	 * 取出所有关系属性列表及对应关系
	 * @return
	 */
	public List<ExamRelationProp> getExamRelationPropAll();
	
	
	/**
	 * 取系统属性值
	 * @param id
	 * @return
	 */
	public ExamProp getSysPropVal(Long id);
	
	/**
	 * 移动 属性
	 * @param 目标 target_id
	 * @param 要移动 current_id
	 * @param 属性id
	 * @param 属性类别
	 */
	public void updateMoveSysPropVal(Long target_id,Long current_id,Long propId,Integer type);
	
	/**
	 * 移动试题属性
	 * @param 目标 target_id
	 * @param 属性id
	 * @param 属性类别
	 */
	public int updatReplaceQuestionPropVal(Long target_id,Long propId,Integer type);

	/**
	 * 查询ICD属性
	 * @param prop
	 * @return
	 */
	public void getIcdListByType(Page<ExamICD> page, ExamICD prop);
	
	/**
	 * 添加Icd
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamICD addIcdVal(ExamICD prop) throws Exception;
	
	/**
	 * 删除Icd
	 * @param prop
	 */
	public boolean deleteIcdVal(ExamICD prop);
	
	/**
	 * 修改Icd
	 * @param prop
	 * @throws Exception 
	 */
	public void updateIcdVal(ExamICD prop) throws Exception;

	/**
	 * 查询来源类型
	 * @param prop
	 * @return
	 */
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop);
	
	/**
	 * 添加来源类型
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamSourceType addSourceType(ExamSourceType prop) throws Exception;
	
	/**
	 * 删除来源类型
	 * @param prop
	 */
	public boolean deleteSourceType(ExamSourceType prop);
	
	/**
	 * 修改来源类型
	 * @param prop
	 * @throws Exception 
	 */
	public void updateSourceType(ExamSourceType prop) throws Exception;

	/**
	 * 查询来源目录
	 * @param prop
	 * @return
	 */
	public List<ExamSource> getSourceValList(ExamSource prop);

	public List<ExamSource> getSourceValList(String ids);

	/**
	 * 添加来源
	 * @param propVal
	 * @return
	 * @throws Exception 
	 */
	public ExamSource addSourceVal(ExamSource prop) throws Exception;
	
	/**
	 * 删除来源
	 * @param prop
	 */
	public boolean deleteSourceVal(ExamSource prop);
	
	/**
	 * 修改来源
	 * @param prop
	 */
	public boolean updateSourceVal(ExamSource prop);

	/**
	 * 取得医院列表
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamHospital> getHospitalList(ExamHospital host);

	/**
	 * 取得医院列表
	 * @return
	 */
	public List<ExamHospitalVO> getHospitalList(String searchWord);

	/**
	 * 取得医院列表
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public ExamHospital getHospitalById(ExamHospital host);
	
	/**
	 * 添加医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean addHospital(ExamHospital host);
	
	/**
	 * 删除医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean deleteHospital(ExamHospital host);
	
	/**
	 * 修改医院
	 * @param host
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean updateHospital(ExamHospital host);
	
	/**
	 * 取得专业排名列表
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major);
	
	/**
	 * 添加专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean addMajorOrder(ExamMajorOrder major);
	
	/**
	 * 删除专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean deleteMajorOrder(ExamMajorOrder major);
	
	/**
	 * 修改专业排名
	 * @param major
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public boolean updateMajorOrder(ExamMajorOrder major);

	public Long getParentPropId(Long id);

	public ExamProp getExamPropValByPropId(Long id);

	public List<ExamPropVal> getIdByName(ExamPropVal name);

	public List<ExamICD> getICDListByPropIds(String ids);

	public int updatReplaceMaterialPropVal(Long targetId, Long propId);

	public List<ExamHospital> getHospitalList(PageList pl, ExamHospital hospital);

	public void getSourceValPageList(PageList pl, ExamSource prop);
	
	//SCP根据人物画像名查学科
	public List<ExamPropVal> getXuekeByImageName(UserImage userImage);

	public ExamProp getRegionListByPropId(Long id);
	
	
	public List<ExamPropVal> getCityList();
}
