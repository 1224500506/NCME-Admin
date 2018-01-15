package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.UserImageManageDAO;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;

import com.hys.exam.service.local.UserImageManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class UserImageManageImpl extends BaseMangerImpl implements UserImageManage {
	
	private UserImageManageDAO localUserImageManageDAO;

	public UserImageManageDAO getLocalUserImageManageDAO() {
		return localUserImageManageDAO;
	}

	public void setLocalUserImageManageDAO(UserImageManageDAO localUserImageManageDAO) {
		this.localUserImageManageDAO = localUserImageManageDAO;
	}

	@Override
	public List<UserImage> getUserImageList(UserImage userImage) {
		return localUserImageManageDAO.getUserImageList(userImage);
	}

	@Override
	public boolean addUserImage(UserImage userImage) {
		return localUserImageManageDAO.addUserImage(userImage);
	}

	@Override
	public boolean deleteUserImage(UserImage userImage) {
		return localUserImageManageDAO.deleteUserImage(userImage);
	}

	@Override
	public boolean updateUserImage(UserImage userImage) {
		return localUserImageManageDAO.updateUserImage(userImage);
	}

	@Override
	public List<PropUnit> getHospitalList() {
		
		return localUserImageManageDAO.getHospitalList();
	}

	@Override
	public List<PropUnit> getAreaList() {
		
		return localUserImageManageDAO.getAreaList();
	}

	@Override
	public List<PropUnit> getDutyList() {
		
		return localUserImageManageDAO.getDutyList();
	}
	public List<PropUnit> getDutyList(Long TypeID) {
		
		return localUserImageManageDAO.getDutyList(TypeID);
	}

	@Override
	public List<PropUnit> getMajorList() {
		
		return localUserImageManageDAO.getMajorList();
	}
	
	@Override
	public List<PropUnit> getMajorList(List<PropUnit> tempList) {
		
		return localUserImageManageDAO.getMajorList(tempList);
	}
	
	@Override
	public List<PropUnit> getMajorLevelList(List<PropUnit> tempList) {
		
		return localUserImageManageDAO.getMajorLevelList(tempList);
	}
	
	@Override
	public List<UserImage> getUserImageListByXueke(UserImage userImage) {
		return localUserImageManageDAO.getUserImageListByXueke(userImage);
	}

	@Override
	public boolean compareName(UserImage userImage) {
		return localUserImageManageDAO.compareName(userImage);
	}

	@Override
	public List<PropUnit> getAreaList( List<PropUnit> tempList) {
		
		return localUserImageManageDAO.getAreaList(tempList);
	}

	@Override
	public List<PropUnit> getDutyList( List<PropUnit> tempList) {

		return localUserImageManageDAO.getDutyList(tempList);
	}

	@Override
	public List<PropUnit> getHospitalList( List<PropUnit> tempList) {
		return localUserImageManageDAO.getHospitalList(tempList);
	}

	@Override
	public void getUserImagePageList(PageList pl, UserImage userImage) {
		localUserImageManageDAO.getUserImagePageList(pl,userImage);
		
	}

	@Override
	public List<PropUnit> getMajorLvlList() {
		
		return localUserImageManageDAO.getMajorLvlList();
	}

	@Override
	public List<PropUnit> getMajorList(String level) {
		
		return localUserImageManageDAO.getMajorList(level);
	}
	/**
	 * 根据人物画像
	 */
	@Override
	public List<UserImage> getUserImageListByTypeIdAndName(UserImage userImage) {
		// TODO Auto-generated method stub
		return localUserImageManageDAO.getUserImageListByTypeIdAndName(userImage);
	}
	
}
