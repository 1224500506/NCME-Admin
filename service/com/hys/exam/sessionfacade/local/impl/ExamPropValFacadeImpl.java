package com.hys.exam.sessionfacade.local.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.*;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.ExamPropValManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

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
public class ExamPropValFacadeImpl extends BaseSessionFacadeImpl implements
		ExamPropValFacade {

	private ExamPropValManage localExamPropValManage;
	
	public ExamPropValManage getLocalExamPropValManage() {
		return localExamPropValManage;
	}
	public void setLocalExamPropValManage(ExamPropValManage localExamPropValManage) {
		this.localExamPropValManage = localExamPropValManage;
	}
	public Map<String, List<ExamPropVal>> getSysPropValBySysId()
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSysPropValBySysId();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamPropValTemp> getBasePropVal(Integer type)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getBasePropVal(type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamPropVal> getBaseRelPorp(int type) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getBaseRelPorp(type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getNextLevelProp(propQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getPropListByType(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamProp> getPropListUserImage(ExamProp prop,String flag) throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getPropListUserImage(prop, flag);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamPropVal> getCityList(){
		try {
			return localExamPropValManage.getCityList();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addPropVal(ExamProp prop) {
		boolean flag = false;
		try {
			localExamPropValManage.addPropVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
		
	}
	@SuppressWarnings("finally")
	public boolean deletePropVal(ExamProp prop) {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deletePropVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updatePropVal(ExamProp prop) {
		boolean flag = false;
		try {
			localExamPropValManage.updatePropVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@Override
	public List<ExamPropType> getExamPropTypeList()
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getExamPropTypeList();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public Map<String,ExamRelationProp> getExamRelationPropAll()
			throws FrameworkRuntimeException {
		try {
			Map<String,ExamRelationProp> m = new HashMap<String,ExamRelationProp>();
			List<ExamRelationProp> list = localExamPropValManage.getExamRelationPropAll();
			for(ExamRelationProp p : list){
				m.put(p.getPoint_prop_id().toString(), p);
			}
			return m;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public List<Long> getExamPropTypeList(int type)
			throws FrameworkRuntimeException {
		try {
			ExamProp prop = new ExamProp();
			prop.setType(type);
			List<Long> list = new ArrayList<Long>();
			List<ExamProp> propList = localExamPropValManage.getPropListByType(prop);
			
			for(ExamProp p : propList){
//				list.add(p.getId());
				list.add(p.getProp_val_id());
			}
			
			
			return list;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public ExamProp getSysPropVal(Long id) throws FrameworkRuntimeException {
		ExamProp prop = null;
		try {
			prop = localExamPropValManage.getSysPropVal(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
		} finally {
			return prop;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateMoveSysPropVal(Long target_id,Long current_id,Long propId,Integer type)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updateMoveSysPropVal(target_id, current_id,propId,type);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updatReplaceQuestionPropVal(Long targetId, Long propId,
			Integer type) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updatReplaceQuestionPropVal(targetId, propId, type)==0?false:true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}		
	}
	
	@Override
	public List<ExamRelationProp> getExamRelationPropList()
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getExamRelationPropAll();
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public void getIcdListByType(Page<ExamICD> page, ExamICD prop) throws FrameworkRuntimeException {
		try {
			localExamPropValManage.getIcdListByType(page, prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@SuppressWarnings("finally")
	public boolean addIcdVal(ExamICD prop) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.addIcdVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean deleteIcdVal(ExamICD prop) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteIcdVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean updateIcdVal(ExamICD prop) throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updateIcdVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	
	
	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSourceTypeList(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addSourceType(ExamSourceType prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.addSourceType(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteSourceType(ExamSourceType prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteSourceType(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateSourceType(ExamSourceType prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.updateSourceType(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@Override
	public List<ExamSource> getSourceValList(ExamSource prop)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSourceValList(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@Override
	public List<ExamSource> getSourceValList(String ids)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getSourceValList(ids);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addSourceVal(ExamSource prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamPropValManage.addSourceVal(prop);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteSourceVal(ExamSource prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteSourceVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateSourceVal(ExamSource prop)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updateSourceVal(prop);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public List<ExamHospital> getHospitalList(ExamHospital host)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getHospitalList(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	public List<ExamHospitalVO> getHospitalList(String searchWord)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getHospitalList(searchWord);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean addHospital(ExamHospital host)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.addHospital(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteHospital(ExamHospital host)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteHospital(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateHospital(ExamHospital host)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updateHospital(host);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		try {
			return localExamPropValManage.getMajorOrderList(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	@SuppressWarnings("finally")
	public boolean addMajorOrder(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.addMajorOrder(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean deleteMajorOrder(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.deleteMajorOrder(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	@SuppressWarnings("finally")
	public boolean updateMajorOrder(ExamMajorOrder major)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updateMajorOrder(major);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}
	public Long getParentPropId(Long id){
		return localExamPropValManage.getParentPropId(id);
	}

	@SuppressWarnings("finally")
	public ExamProp getExamPropValByPropId(Long id) throws FrameworkRuntimeException {
		ExamProp prop = null;
		try {
			prop = localExamPropValManage.getExamPropValByPropId(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
		} finally {
			return prop;
		}
	}
	@Override
	public List<ExamPropVal> getIdByName(ExamPropVal name) {
		
		return localExamPropValManage.getIdByName(name);
	}

	@Override
	public List<ExamICD> getICDListByPropIds(String ids) {
		return localExamPropValManage.getICDListByPropIds(ids);
	}
	
	@SuppressWarnings("finally")
	public boolean updatReplaceMaterialPropVal(Long targetId, Long propId) {
		boolean flag = false;
		try {
			flag = localExamPropValManage.updatReplaceMaterialPropVal(targetId, propId)==0?false:true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}		
	}
	@Override
	public List<ExamProp> getParentListByPropId(Long prop_val_id) {
		List<ExamProp> parentList = new ArrayList<ExamProp>();
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		Long tempParentId = prop_val_id;
		try{
		while(true){
			ExamProp tempParent = localExamPropValManage.getExamPropValByPropId(tempParentId);
			if (tempParentId==null || tempParentId<=0L || tempParent==null)
				break;

			if (tempParent.getType()>0 && tempParent.getType()<6){
				parentList.get(tempParent.getType()-1).setProp_val_id(tempParent.getProp_val_id());
				parentList.get(tempParent.getType()-1).setName(tempParent.getName());
				parentList.get(tempParent.getType()-1).setType(tempParent.getType());
			}
			else break;
			tempParentId = localExamPropValManage.getParentPropId(tempParentId);
		}
		}
		catch(Exception e){}
		return parentList;
	}
	
	@Override
	public ExamHospital getHospitalById(ExamHospital host)
			throws FrameworkRuntimeException {
		// TODO Auto-generated method stub
		return localExamPropValManage.getHospitalById(host);
	}
	@Override
	public List<ExamHospital> getHospitalList(PageList pl, ExamHospital hospital) {
		return localExamPropValManage.getHospitalList(pl, hospital);
	}
	@Override
	public void getSourceValPageList(PageList pl, ExamSource prop) {
		localExamPropValManage.getSourceValPageList(pl, prop);
	}
	@Override
	public List<ExamPropVal> getXuekeByImageName(UserImage userImage) {
		return localExamPropValManage.getXuekeByImageName(userImage);
	}
	
	@Override
	public List<ExamProp> getRegionListByPropId(Long prop_val_id) {
		List<ExamProp> parentList = new ArrayList<ExamProp>();
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		parentList.add(new ExamProp());
		Long tempParentId = prop_val_id;
		try{
		while(true){
			ExamProp tempParent = localExamPropValManage.getRegionListByPropId(tempParentId);
			if (tempParentId==null || tempParentId<=0L || tempParent==null)
				break;

			if (tempParent.getType()==20){
				parentList.get(0).setProp_val_id(tempParent.getProp_val_id());
				parentList.get(0).setName(tempParent.getName());
				parentList.get(0).setType(tempParent.getType());
			}
			else break;
			tempParentId = localExamPropValManage.getParentPropId(tempParentId);
		}
		}
		catch(Exception e){}
		return parentList;
	}
	
}
