package com.hys.exam.displaytag;

import java.util.List;
import org.displaytag.decorator.TableDecorator;

import com.hys.exam.model.PropUnit;
import com.hys.exam.model.UserImage;

/*
 * @Author : Choe
 * @Create Date : 2017/01/12
 * @For show UserImage's General Property
 * 
 */
public class QualityOverOutWrapper extends TableDecorator {   
	
	public String getLink2(){

		UserImage image = (UserImage)getCurrentRowObject();
		List<PropUnit> areaList = image.getGeneralUserImage().getAreaPropList();
		List<PropUnit> hospitalList = image.getGeneralUserImage().getHospitalPropList();
		List<PropUnit> dutyList = image.getGeneralUserImage().getDutyPropList();
		
		String res = "";
		int size = 0;
		if(hospitalList.size()<areaList.size()){
			size = hospitalList.size();
		}else{
			size = areaList.size();
		}
		if(dutyList.size()<hospitalList.size()){
			size = dutyList.size();
		}
		for (int i=0; i<size; i++) {
			res += areaList.get(i).getName() + "->";
			res += hospitalList.get(i).getName() + "->";
			res += dutyList.get(i).getName() + "</br>";
		}
		
		return res;
	}
}
