package com.hys.exam.service.local.impl;

import com.hys.exam.dao.local.CVUnitManageDAO;
import com.hys.exam.model.CVUnit;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CVUnitManageImpl extends BaseMangerImpl implements CVUnitManage  {

	private CVUnitManageDAO cvUnitManageDAO;

    public CVUnitManageDAO getCvUnitManageDAO() {
        return cvUnitManageDAO;
    }

    public void setCvUnitManageDAO(CVUnitManageDAO cvUnitManageDAO) {
        this.cvUnitManageDAO = cvUnitManageDAO;
    }
	
        
	
	@Override
	public CVUnit findCvunit(CVUnit cvu) {
		// TODO Auto-generated method stub
		
		return cvUnitManageDAO.findCvunit(cvu);
	}

    

}
