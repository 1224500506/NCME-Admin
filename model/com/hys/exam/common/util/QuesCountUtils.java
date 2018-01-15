package com.hys.exam.common.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTacticX;


/*
 * 快捷策略计算试题数量的工具类
 */

public class QuesCountUtils {
	
	/**
	 * 计算试题数量 
	 * @param quesList
	 * @return StatisticsPaper List
	 */
	public static Map<String,Integer> getQuesSize(ExamPaper paper,Map<String,Integer> shux) {
		
		
		List<ExamPaperFasterTacticX> pftList =  paper.getPaperFasterTacticx();
		
		Integer totCount = 0;//所选题型试题总数量
		Map<Integer,Integer>  lableNum = new HashMap<Integer,Integer>();//所选题型试题数量
		Map<String,Map<Integer,Integer>>  propNum = new HashMap<String,Map<Integer,Integer>>();//所选属性试题数量
		
		
		
		for (Iterator<ExamPaperFasterTacticX> iterator = pftList.iterator(); iterator.hasNext();) {
			ExamPaperFasterTacticX pftX = iterator.next();
			totCount = totCount + pftX.getNum();
			lableNum.put(pftX.getQuestion_label_id(), pftX.getNum());
			
			List<ExamPaperFasterTactic2> pft2List = pftX.getExamPaperFasterTactic2();
			for (Iterator<ExamPaperFasterTactic2> iterator2 = pft2List.iterator(); iterator2.hasNext();) {
				ExamPaperFasterTactic2 pft2 = iterator2.next();
				for (String	name : shux.keySet()) {
					if(name.equals(getpropValName(pft2))){
						shux.put(name, 0);
						propNum.put(name, null);
					}
				}
			}
		}
		
		for (String	name : propNum.keySet()) {
			Map<Integer,Integer> temp = new HashMap<Integer,Integer>();
			for (Iterator<ExamPaperFasterTacticX> iterator = pftList.iterator(); iterator.hasNext();) {
				ExamPaperFasterTacticX pftX = iterator.next();
				
				List<ExamPaperFasterTactic2> pft2List = pftX.getExamPaperFasterTactic2();
				for (Iterator<ExamPaperFasterTactic2> iterator2 = pft2List.iterator(); iterator2.hasNext();) {
					ExamPaperFasterTactic2 pft2 = iterator2
					.next();
					if(name.equals(getpropValName(pft2))){
						temp.put(pftX.getQuestion_label_id(), pft2.getAllQuestionNum());
					}
				}
			}
			propNum.put(name, temp);
		}
		for(String name : propNum.keySet()){
			Double d = 0.0;
//			int i = 0;
			for(Integer lableId : propNum.get(name).keySet()){
				if(lableNum.get(lableId) > propNum.get(name).get(lableId)){
					if(propNum.get(name).get(lableId)>0){
						d = d + propNum.get(name).get(lableId)/totCount.doubleValue();
					}
				}else {
					d = d + lableNum.get(lableId)/totCount.doubleValue();
				}
			}
			d = d * 100;
			shux.put(name, round(d,0));
		}
		
		return shux;
		
	}
	/**
	 * 设置试题数量 
	 * @param quesList
	 * @return StatisticsPaper List
	 */
	public static ExamPaper setQuesSize(ExamPaper paper,Map<String,Integer> shux,Integer totQuesCount) {
		
		List<ExamPaperFasterTacticX> pftList =  paper.getPaperFasterTacticx();
		
		Map<Integer,Integer>  lableNum = new HashMap<Integer,Integer>();//所选题型试题数量
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		for (String	name : shux.keySet()) {
			map.put(name, false);
		}
		
		
		for (Iterator<ExamPaperFasterTacticX> iterator = pftList.iterator(); iterator.hasNext();) {
			ExamPaperFasterTacticX pftX = iterator.next();
			Integer totCount = pftX.getNum();
			Integer temp = 0;
			lableNum.put(pftX.getQuestion_label_id(), pftX.getNum());
			
			List<ExamPaperFasterTactic2> pft2List = pftX.getExamPaperFasterTactic2();
			for (Iterator<ExamPaperFasterTactic2> iterator2 = pft2List.iterator(); iterator2.hasNext();) {
				ExamPaperFasterTactic2 pft2 = iterator2.next();
				String pft2Name = getpropValName(pft2).substring(1,getpropValName(pft2).length());
				if(map.get(pft2Name)){
					continue;
				}
				Integer shuxsl = 0;
				for (String	name : shux.keySet()) {
					if(name.equals(pft2Name)){
						Double prec = shux.get(name)/100.0;
						Integer num = 0;
						num = round(totCount * prec,0);
						if(temp <= totCount && pft2.getAllQuestionNum()>0){
							if(num > pft2.getAllQuestionNum()){
								num = pft2.getAllQuestionNum();
							}
							if(temp + num > totCount){
								pft2.setSelQuestionNum(totCount - temp);
								temp = totCount;
							}else {
								pft2.setSelQuestionNum(num);
								temp = temp + num;
								shuxsl = num;
							}
						}
					}
				}
				
				Double prec = shux.get(pft2Name)/100.0;
				Integer num = 0;
				num = round(totQuesCount * prec,0);
				if(shuxsl == num){
					map.put(pft2Name, true);
				}
				
			}
			if(temp < totCount && temp != 0){
				for (Iterator<ExamPaperFasterTactic2> iterator2 = pft2List.iterator(); iterator2.hasNext();) {
					ExamPaperFasterTactic2 pft2 = iterator2.next();
					if(pft2.getAllQuestionNum()>0 && pft2.getSelQuestionNum() + (totCount - temp)<=pft2.getAllQuestionNum()){
						pft2.setSelQuestionNum(pft2.getSelQuestionNum() + (totCount - temp));
						break;
					}
				}
			}
		}
		
		paper.setPaperFasterTacticx(pftList);
		
		return paper;
		
	}

	
	
	
	/**
	 * 检查题型试题数量是否满足 
	 * @param quesList
	 * @return StatisticsPaper List
	 */
	public static Map<Integer,Boolean> checkQuesSize(ExamPaper paper) {
		
		Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
		
		List<ExamPaperFasterTacticX> pftList =  paper.getPaperFasterTacticx();
		
		for (Iterator<ExamPaperFasterTacticX> iterator = pftList.iterator(); iterator.hasNext();) {
			ExamPaperFasterTacticX pftX = iterator.next();
			List<ExamPaperFasterTactic2> pft2List = pftX.getExamPaperFasterTactic2();
			
//			boolean flag = true;
			Integer countNum = pftX.getNum();
			Integer totNum = 0;
			for (Iterator<ExamPaperFasterTactic2> iterator2 = pft2List.iterator(); iterator2.hasNext();) {
				ExamPaperFasterTactic2 pft2 = iterator2.next();
				totNum = totNum + pft2.getAllQuestionNum();
			}
			
			map.put(pftX.getQuestion_label_id(), totNum >= countNum ? false : true);
		}
		return map;
	}
	
	private static String getpropValName(ExamPaperFasterTactic2 pft2){
		
		StringBuffer str = new StringBuffer();
		
		if(null != pft2.getStudy1_id()){
			str.append(pft2.getStudy1_id());
		}
		if(null != pft2.getStudy2_id()){
			str.append("-");
			str.append(pft2.getStudy2_id());
		}
		if(null != pft2.getStudy3_id()){
			str.append("-");
			str.append(pft2.getStudy3_id());
		}
		if(null != pft2.getUnit_id()){
			str.append("-");
			str.append(pft2.getUnit_id());
		}
		if(null != pft2.getPoint_id()){
			str.append("-");
			str.append(pft2.getPoint_id());
		}
		return str.toString();
	}
	
	private static int round(double v,int scale){
		BigDecimal b = new BigDecimal(Double.toString(v));
		
		BigDecimal one = new BigDecimal("1");
		
		return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).intValue();
	}
	
	
	public static void main(String[] args){ 
//		double d = 0.9; 
//		DecimalFormat df = new DecimalFormat("#"); 
//		System.out.println(df.format(d)); 
//		System.out.println(round(d,0)); 


//		//这样也可以
//		double num = 5.75557;
//		System.out.println(Math.floor(num));//取整
//		BigDecimal   b=new   BigDecimal(num);   
//		num=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();   
//	  
//		System.out.println(num);
	}
	
}
