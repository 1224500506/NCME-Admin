package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamProp;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.PageList;

public interface UserImageManageDAO {
	List<UserImage> getUserImageList(UserImage userImage);
	
	boolean addUserImage(UserImage userImage);
	
	boolean updateUserImage(UserImage userImage);
	
	boolean deleteUserImage(UserImage userImage);
	
	List<PropUnit> getHospitalList();
	
	List<PropUnit> getAreaList();
	
	List<PropUnit> getDutyList(Long TypeID);
	List<PropUnit> getDutyList();
	
	List<PropUnit> getMajorList();
	List<PropUnit> getMajorList( List<PropUnit> tempList);
	List<PropUnit> getMajorLevelList(List<PropUnit> tempList);

	List<UserImage> getUserImageListByXueke(UserImage userImage);

	boolean compareName(UserImage userImage);
	List<PropUnit> getAreaList(List<PropUnit> tempList);

	List<PropUnit> getDutyList( List<PropUnit> tempList);

	List<PropUnit> getHospitalList( List<PropUnit> tempList);

	void getUserImagePageList(PageList pl, UserImage userImage);

	List<PropUnit> getMajorLvlList();

	List<PropUnit> getMajorList(String level);
	List<UserImage> getUserImageListByTypeIdAndName(UserImage userImage);
}
