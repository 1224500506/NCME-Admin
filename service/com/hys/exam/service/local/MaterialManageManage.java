package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.MaterialInfo;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.framework.service.BaseService;


public interface MaterialManageManage extends BaseService {

	List<MaterialInfo> getMaterialList(MaterialInfo material);
	
	MaterialInfo getMaterialInfo(MaterialInfo material);
	
	MaterialInfo getMaterialInfo(Long id);
	
	MaterialInfo getMaterialInfo(String materialName);
	
	boolean addMaterialInfo(MaterialInfo material);
	
	boolean updateMaterialInfo(MaterialInfo material);
	
	boolean deleteMaterialInfo(MaterialInfo material);
	
	boolean deleteMaterialInfo(Long id);
	
	boolean updateStatesOfMaterial(Long[] ids, int state, String opinion, String userName);

	List<MaterialInfo> getMaterialList(MaterialInfo material,
			String[] upload_date, String[] deli_date, int orderBy);

	boolean updateStatesOfMaterial(Long[] ids, int state);
	
	List<Long> getMaterialPropById(Long id);

	boolean addMaterialProp(MaterialInfo materialInfo);

	boolean updateMaterialProp(MaterialInfo materialInfo);

	void updateMaterialSourceId(Long oldId, Long newId);
}
