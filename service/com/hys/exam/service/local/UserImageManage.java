package com.hys.exam.service.local;


import java.util.List;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface UserImageManage extends BaseService {
	List<UserImage> getUserImageList(UserImage userImage);
	
	boolean addUserImage(UserImage userImage);
	
	boolean deleteUserImage(UserImage userImage);
	
	boolean updateUserImage(UserImage userImage);
	
	List<PropUnit> getHospitalList();
	
	List<PropUnit> getAreaList();
	
	List<PropUnit> getDutyList();

	List<PropUnit> getMajorList();
	
	List<PropUnit> getMajorList(List<PropUnit> tempList);

	List<PropUnit> getMajorLevelList(List<PropUnit> tempList);

	List<UserImage> getUserImageListByXueke(UserImage userImage);

	List<PropUnit> getDutyList(Long typeId);

	boolean compareName(UserImage userImage);
	
	List<PropUnit> getAreaList(List<PropUnit> tempList);

	List<PropUnit> getDutyList( List<PropUnit> tempList);

	List<PropUnit> getHospitalList( List<PropUnit> tempList);

	void getUserImagePageList(PageList pl, UserImage userImage);

	List<PropUnit> getMajorLvlList();

	List<PropUnit> getMajorList(String level);
	
	List<UserImage> getUserImageListByTypeIdAndName(UserImage userImage);
}
