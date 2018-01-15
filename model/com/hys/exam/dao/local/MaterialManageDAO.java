package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;


public interface MaterialManageDAO {
	
	List<MaterialInfo> getMaterialList(MaterialInfo material, String[] upload_date, String[] deli_date, int orderBy);
	
	MaterialInfo getMaterialInfo(Long id);
	
	MaterialInfo getMaterialInfo(String materialName);
	
	boolean addMaterialInfo(MaterialInfo material);
	
	boolean updateMaterialInfo(MaterialInfo material);
	
	boolean deleteMaterialInfo(Long id);
	
	boolean updateStatesOfMaterial(Long[] ids, int state, String opinion, String userName);

	List<MaterialInfo> getMaterialList(MaterialInfo material);
	
	List<Long> getMaterialPropById(Long id);

	boolean addMaterialProp(MaterialInfo material);

	boolean updateMaterialProp(MaterialInfo material);

	void updateMaterialSourceId(Long oldId, Long newId);

}
