package com.hys.exam.service.local.impl;

import com.hys.exam.common.util.Page;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.ExamPropValDAO;
import com.hys.exam.dao.local.ExamQuestionDAO;
import com.hys.exam.dao.local.ExamQuestionPropDAO;
import com.hys.exam.dao.local.ExamQuestionPropValCascadeDAO;
import com.hys.exam.model.*;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.ExamPropValManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.service.impl.BaseMangerImpl;

import java.util.*;

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
public class ExamPropValManageImpl extends BaseMangerImpl implements
		ExamPropValManage {

	private ExamPropValDAO localExamPropValDAO;
	
	private ExamQuestionDAO localExamQuestionDAO;
	
	private ExamQuestionPropValCascadeDAO localExamQuestionPropValCascadeDAO;
	
	private ExamQuestionPropDAO localExamQuestionPropDAO;
	
	public ExamQuestionDAO getLocalExamQuestionDAO() {
		return localExamQuestionDAO;
	}

	public void setLocalExamQuestionDAO(ExamQuestionDAO localExamQuestionDAO) {
		this.localExamQuestionDAO = localExamQuestionDAO;
	}

	public ExamPropValDAO getLocalExamPropValDAO() {
		return localExamPropValDAO;
	}

	public void setLocalExamPropValDAO(ExamPropValDAO localExamPropValDAO) {
		this.localExamPropValDAO = localExamPropValDAO;
	}
	

	public ExamQuestionPropValCascadeDAO getLocalExamQuestionPropValCascadeDAO() {
		return localExamQuestionPropValCascadeDAO;
	}

	public void setLocalExamQuestionPropValCascadeDAO(
			ExamQuestionPropValCascadeDAO localExamQuestionPropValCascadeDAO) {
		this.localExamQuestionPropValCascadeDAO = localExamQuestionPropValCascadeDAO;
	}

	public ExamQuestionPropDAO getLocalExamQuestionPropDAO() {
		return localExamQuestionPropDAO;
	}

	public void setLocalExamQuestionPropDAO(
			ExamQuestionPropDAO localExamQuestionPropDAO) {
		this.localExamQuestionPropDAO = localExamQuestionPropDAO;
	}

	@Override
	public Map<String, List<ExamPropVal>> getSysPropValBySysId() {
		return localExamPropValDAO.getSysPropValBySysId();
	}


	@Override
	public List<ExamPropValTemp> getBasePropVal(Integer type) {
		return localExamPropValDAO.getBasePropVal(type);
	}


	public List<ExamPropVal> getBaseRelPorp(int type) {
		return localExamPropValDAO.getBaseRelPorp(type);
	}


	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery) {
		return localExamPropValDAO.getNextLevelProp(propQuery);
	}


	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) {
		return localExamPropValDAO.getPropListByType(prop);
	}

	@Override
	public List<ExamProp> getPropListUserImage(ExamProp prop,String flag) {
		return localExamPropValDAO.getPropListUserImage(prop,flag);
	}
	
	@Override
	public List<ExamPropVal> getCityList() {
		return localExamPropValDAO.getCityList();
	}


	@Override
	public ExamProp addPropVal(ExamProp prop) throws Exception {
		localExamPropValDAO.addPropVal(prop);
		localExamPropValDAO.addSysPropVal(prop);
		if(prop.getType()>1 && prop.getProp_val1()>0){
			localExamPropValDAO.addRel(prop);
		}
		return prop;
	}


	@Override
	public boolean deletePropVal(ExamProp prop) {
		return localExamPropValDAO.deletePropVal(prop);
	}


	@Override
	public void updatePropVal(ExamProp prop) throws Exception {
		localExamPropValDAO.updatePropVal(prop);
	}


	@Override
	public List<ExamPropType> getExamPropTypeList() {
		return localExamPropValDAO.getExamPropTypeList();
	}


	@Override
	public List<ExamRelationProp> getExamRelationPropAll() {
		return localExamPropValDAO.getExamRelationPropAll();
	}


	@Override
	public List<ExamProp> getNextLevelProp(Long propValId) {
		return localExamPropValDAO.getNextLevelProp(propValId);
	}


	@Override
	public ExamProp getSysPropVal(Long id) {
		return localExamPropValDAO.getSysPropVal(id);
	}


	@Override
	public void updateMoveSysPropVal(Long targetId, Long currentId,Long propId,Integer type) {
		
		ArrayList<ExamQuestionPropValCascade> cascadeList = new ArrayList<ExamQuestionPropValCascade>();
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		
		//取要移动属性下的所有主试题
		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionListByPropVal(propId, type);
		
		//移动属性
		localExamPropValDAO.updateMoveSysPropVal(targetId, currentId);
		
		//如果要移动属性下的有试题
		if(questionList!=null && !questionList.isEmpty()){
			//所有关联属性以知识点为key
			Map<String,ExamRelationProp> relPropMap = new HashMap<String,ExamRelationProp>();
			List<ExamRelationProp> list = localExamPropValDAO.getExamRelationPropAll();
			for(ExamRelationProp p : list){
				relPropMap.put(p.getPoint_prop_id().toString(), p);
			}
			
			//为移动属性下的试题赋予新的关联属性关系
			List<Long> idArr = new ArrayList<Long>();
			for(ExamQuestion q : questionList){
				List<ExamQuestionProp> questionPropList = localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()).get(Constants.EXAM_PROP_POINT);
				setRelProp(questionPropList,cascadeList,questionPropMap,relPropMap,q.getId());
				idArr.add(q.getId());
			}
			
			
			List<Object[]> batch = new ArrayList<Object[]>();
			for (int i = 0; i < idArr.size(); i++) {
				Object[] values = new Object[] { idArr.get(i),
						idArr.get(i) };
				batch.add(values);
			}
			
			//删除试题级联属性
			localExamQuestionPropDAO.deleteQuestionProprByQuestionId(batch);
			
			//删除试题属性对应关系
			localExamQuestionPropValCascadeDAO.deleteQuestionPropValCascadeByQuestionId(batch);
			
			//添加试题属性
			localExamQuestionPropDAO.addQuestionProp(questionPropMap);
			//保存试题属性对应关系
			localExamQuestionPropValCascadeDAO.addQuestionPropValCascade(cascadeList);
		}
		
	}

	
	private void setRelProp(List<ExamQuestionProp> questionPropList,
			ArrayList<ExamQuestionPropValCascade> cascadeList,
			Map<String, List<ExamQuestionProp>> questionPropMap,
			Map<String, ExamRelationProp> relPropMap,Long qid) {
		try{
			ExamQuestionPropValCascade propValCascade = new ExamQuestionPropValCascade();
			
			List<ExamQuestionProp> study1List = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> study2List = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> study3List = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> unitList = new ArrayList<ExamQuestionProp>();
			List<ExamQuestionProp> pointList = new ArrayList<ExamQuestionProp>();
			
			if(questionPropMap.get(Constants.EXAM_PROP_STUDY1)!=null){
				study1List = questionPropMap.get(Constants.EXAM_PROP_STUDY1);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_STUDY2)!=null){
				study2List = questionPropMap.get(Constants.EXAM_PROP_STUDY2);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_STUDY3)!=null){
				study3List = questionPropMap.get(Constants.EXAM_PROP_STUDY3);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_UNIT)!=null){
				unitList = questionPropMap.get(Constants.EXAM_PROP_UNIT);
			}
			if(questionPropMap.get(Constants.EXAM_PROP_POINT)!=null){
				pointList = questionPropMap.get(Constants.EXAM_PROP_POINT);
			}
			
			
			String cid="";
			String cname = "";
			for(ExamQuestionProp propId : questionPropList){
				if(relPropMap.containsKey(propId.getProp_val_id()+"")){
					ExamRelationProp relProp = relPropMap.get(propId.getProp_val_id()+"");
					setProp(relProp.getStudy1_prop_id(),qid,study1List);
					setProp(relProp.getStudy2_prop_id(),qid,study2List);
					setProp(relProp.getStudy3_prop_id(),qid,study3List);
					setProp(relProp.getUnit_prop_id(),qid,unitList);
					setProp(relProp.getPoint_prop_id(),qid,pointList);
					if(cid.equals("")){
						cid = relProp.getRelationPropId();
						cname = relProp.getRelationPropName();
					}else{
						if(cid.indexOf(relProp.getRelationPropId()) == -1){
							cid += "+"+relProp.getRelationPropId();
							cname +="+"+relProp.getRelationPropName();
						}
					}
				}
			}
			questionPropMap.put(Constants.EXAM_PROP_STUDY1, removeDuplicateWithOrder(study1List));
			questionPropMap.put(Constants.EXAM_PROP_STUDY2, removeDuplicateWithOrder(study2List));
			questionPropMap.put(Constants.EXAM_PROP_STUDY3, removeDuplicateWithOrder(study3List));
			questionPropMap.put(Constants.EXAM_PROP_UNIT, removeDuplicateWithOrder(unitList));
			questionPropMap.put(Constants.EXAM_PROP_POINT, removeDuplicateWithOrder(pointList));
			propValCascade.setPropval_id(cid);
			propValCascade.setPropval_name(cname);
			propValCascade.setQuestion_id(qid);
			cascadeList.add(propValCascade);
			
			
		}catch(Exception e){
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	private void setProp(Long propId,Long qid,List<ExamQuestionProp> list){
		ExamQuestionProp prop = new ExamQuestionProp();
		prop.setProp_val_id(propId);
		prop.setQuestion_id(qid);
		list.add(prop);
	}
	
	private List<ExamQuestionProp> removeDuplicateWithOrder(
			List<ExamQuestionProp> list) {
		Set<ExamQuestionProp> set = new HashSet<ExamQuestionProp>();
		List<ExamQuestionProp> newList = new ArrayList<ExamQuestionProp>();
		for (ExamQuestionProp p : list) {
			if (set.add(p)) {
				newList.add(p);
			}
		}
		return newList;
	}

	@Override
	public int updatReplaceQuestionPropVal(Long targetId, Long propId,
			Integer type) {
/*		ArrayList<ExamQuestionPropValCascade> cascadeList = new ArrayList<ExamQuestionPropValCascade>();
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
*/		
		return localExamQuestionDAO.updatReplaceQuestionPropVal(targetId, propId, type);
		//取要移动属性下的所有主试题
/*		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionListByPropVal(propId, type);
		
		//如果要移动属性下的有试题
		if(questionList!=null && !questionList.isEmpty()){
			//所有关联属性以知识点为key
			Map<String,ExamRelationProp> relPropMap = new HashMap<String,ExamRelationProp>();
			List<ExamRelationProp> list = localExamPropValDAO.getExamRelationPropAll();
			for(ExamRelationProp p : list){
				relPropMap.put(p.getPoint_prop_id().toString(), p);
			}
			
			//为移动属性下的试题赋予新的关联属性关系
			List<Long> idArr = new ArrayList<Long>();
			for(ExamQuestion q : questionList){
				List<ExamQuestionProp> questionPropList = localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()).get(Constants.EXAM_PROP_POINT);
				
				for(ExamQuestionProp p : questionPropList){
					if(p.getProp_val_id().toString().equals(propId.toString())){
						p.setProp_val_id(targetId);
					}
				}
				
				setRelProp(removeDuplicateWithOrder(questionPropList),cascadeList,questionPropMap,relPropMap,q.getId());
				idArr.add(q.getId());
			}
			
			
			List<Object[]> batch = new ArrayList<Object[]>();
			for (int i = 0; i < idArr.size(); i++) {
				Object[] values = new Object[] { idArr.get(i),
						idArr.get(i) };
				batch.add(values);
			}
			
			//删除试题级联属性
			localExamQuestionPropDAO.deleteQuestionProprByQuestionId(batch);
			
			//删除试题属性对应关系
			localExamQuestionPropValCascadeDAO.deleteQuestionPropValCascadeByQuestionId(batch);
			
			//添加试题属性
			localExamQuestionPropDAO.addQuestionProp(questionPropMap);
			//保存试题属性对应关系
			localExamQuestionPropValCascadeDAO.addQuestionPropValCascade(cascadeList);
		}*/
	}


	@Override
	public void getIcdListByType(Page<ExamICD> page, ExamICD prop) {
		localExamPropValDAO.getIcdListByType(page, prop);
	}

	@Override
	public ExamICD addIcdVal(ExamICD prop) throws Exception {
		localExamPropValDAO.addIcdVal(prop);
		return prop;
	}

	@Override
	public boolean deleteIcdVal(ExamICD prop) {
		return localExamPropValDAO.deleteIcdVal(prop);
	}

	@Override
	public void updateIcdVal(ExamICD prop) throws Exception {
		localExamPropValDAO.updateIcdVal(prop);
	}

	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop) {
		return localExamPropValDAO.getSourceTypeList(prop);
	}

	@Override
	public ExamSourceType addSourceType(ExamSourceType prop) throws Exception {
		return localExamPropValDAO.addSourceType(prop);
	}

	@Override
	public boolean deleteSourceType(ExamSourceType prop) {
		return localExamPropValDAO.deleteSourceType(prop);
	}

	@Override
	public void updateSourceType(ExamSourceType prop) throws Exception {
		localExamPropValDAO.updateSourceType(prop);
	}

	@Override
	public List<ExamSource> getSourceValList(ExamSource prop) {
		return localExamPropValDAO.getSourceValList(prop);
	}
	@Override
	public List<ExamSource> getSourceValList(String ids) {
		return localExamPropValDAO.getSourceValList(ids);
	}

	@Override
	public ExamSource addSourceVal(ExamSource prop) throws Exception {
		return localExamPropValDAO.addSourceVal(prop);
	}

	@Override
	public boolean deleteSourceVal(ExamSource prop) {
		return localExamPropValDAO.deleteSourceVal(prop);
	}

	@Override
	public boolean updateSourceVal(ExamSource prop) {
		return  localExamPropValDAO.updateSourceVal(prop);
	}

	@Override
	public List<ExamHospital> getHospitalList(ExamHospital host) {
		return localExamPropValDAO.getHospitalList(host);
	}
	@Override
	public List<ExamHospitalVO> getHospitalList(String searchWord) {
		return localExamPropValDAO.getHospitalList(searchWord);
	}

	@Override
	public boolean addHospital(ExamHospital host) {
		return localExamPropValDAO.addHospital(host);
	}

	@Override
	public boolean deleteHospital(ExamHospital host) {
		return localExamPropValDAO.deleteHospital(host);
	}

	@Override
	public boolean updateHospital(ExamHospital host) {
		return localExamPropValDAO.updateHospital(host);
	}

	@Override
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major) {
		return localExamPropValDAO.getMajorOrderList(major);
	}

	@Override
	public boolean addMajorOrder(ExamMajorOrder major) {
		return localExamPropValDAO.addMajorOrder(major);
	}

	@Override
	public boolean deleteMajorOrder(ExamMajorOrder major) {
		return localExamPropValDAO.deleteMajorOrder(major);
	}

	@Override
	public boolean updateMajorOrder(ExamMajorOrder major) {
		return localExamPropValDAO.updateMajorOrder(major);
	}

	@Override
	public Long getParentPropId(Long id) {
		return localExamPropValDAO.getParentPropId(id);
	}

	@Override
	public ExamProp getExamPropValByPropId(Long id) {
		return localExamPropValDAO.getExamPropValByPropId(id);
	}
	@Override
	public List<ExamPropVal> getIdByName(ExamPropVal name) {
		return localExamPropValDAO.getIdByName(name);
	}

	@Override
	public List<ExamICD> getICDListByPropIds(String ids) {
		return localExamPropValDAO.getICDListByPropIds(ids);
	}

	@Override
	public int updatReplaceMaterialPropVal(Long targetId, Long propId) {
		return localExamQuestionDAO.updatReplaceMaterialPropVal(targetId, propId);
	}

	@Override
	public ExamHospital getHospitalById(ExamHospital host) {
		// TODO Auto-generated method stub
		return localExamPropValDAO.getHospitalById(host);
	}

	@Override
	public List<ExamHospital> getHospitalList(PageList pl, ExamHospital hospital) {
		return localExamPropValDAO.getHospitalList(pl, hospital);
	}
	@Override
	public void getSourceValPageList(PageList pl, ExamSource prop) {
		localExamPropValDAO.getSourceValPageList(pl, prop);
	}
	
	@Override
	public List<ExamPropVal> getXuekeByImageName(UserImage userImage) {
		return localExamPropValDAO.getXuekeByImageName(userImage);
	}
	
	@Override
	public ExamProp getRegionListByPropId(Long id) {
		return localExamPropValDAO.getRegionListByPropId(id);
	}
}
