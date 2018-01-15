package com.hys.exam.interfaces.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hys.auth.util.StringUtil;
import com.hys.common.util.StringUtils;
import com.hys.exam.interfaces.ExamWebServiceLocal;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.queryObj.ExamQuestionQuery;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 16, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class TestExamWebServiceLocal {

	public static String url = "http://examquestionlib.haoyisheng.com/exam_question_lib/remote/";
//	public static String url = "http://127.0.0.1:8080/exam_question_lib3_3/remote/";
	
	private static HessianProxyFactory hessianFactory;
	
	
	private static ExamWebServiceLocal examWebSeviceLocal;
	
	private static Long[] idarr = {
		165282L
	};
	


    private static void initService() {
        try {
            System.out.println("远程服务初始化");
            hessianFactory = new HessianProxyFactory();
            examWebSeviceLocal = (ExamWebServiceLocal) hessianFactory.create(ExamWebServiceLocal.class,
                    url + "examWebServiceLocal");

        } catch (MalformedURLException e) {
            throw new RuntimeException("远程服务初始化失败");
        }    	
    }
    
	public static void main(String[] args) {
		initService();
		
//		long a[] = {703,705,706,742,761,762,763,712,715,717,722,724,728,729,730,731,741,707,708,709,710,711,713,714,716,718,719,720,721,723,725,726,727};
//		long a[] = {301,302,303,304};
//		for(int i=0;i<a.length;i++){
//			getQuestionList(a[i]);
//		}
		
//		addPaper();
		
		//getQuestionById(164219L);
		
//		getQuestionByIds(idarr);
		
		getQuestionList(1);
		
//		checkQuestionList();
	}
	
	
	public static void addPaper(){
   	ExamPaper paper = new ExamPaper();
    	
    	paper.setPaper_mode(1);
    	paper.setChild_paper_num(0);
    	ExamPaperFasterTactic paperFasterTactic = new ExamPaperFasterTactic();
    	paperFasterTactic.setQuestion_type_id("1,2,3,4");
    	
    	List<ExamPaperFasterTactic1> t1List = new ArrayList<ExamPaperFasterTactic1>();
    	
    	ExamPaperFasterTactic1 t1 = new ExamPaperFasterTactic1();
    	t1.setNum(200);
    	t1.setQuestion_label_id(1);
    	t1.setScore(1.0);
    	t1List.add(t1);
    	
    	ExamPaperFasterTactic1 t11 = new ExamPaperFasterTactic1();
    	t11.setNum(250);
    	t11.setQuestion_label_id(2);
    	t11.setScore(1.0);
    	t1List.add(t11);
    	
    	ExamPaperFasterTactic1 t111 = new ExamPaperFasterTactic1();
    	t111.setNum(20);
    	t111.setQuestion_label_id(9);
    	t111.setScore(1.0);
    	t1List.add(t111);    
    	
    	ExamPaperFasterTactic1 t1111 = new ExamPaperFasterTactic1();
    	t1111.setNum(30);
    	t1111.setQuestion_label_id(11);
    	t1111.setScore(1.0);
    	t1List.add(t1111);      	
    	
    	List<ExamPaperFasterTactic2> t2List = new ArrayList<ExamPaperFasterTactic2>();
    	ExamPaperFasterTactic2 t2 = new ExamPaperFasterTactic2();
    	t2.setStudy1_id(1014L);
    	t2.setStudy2_id(1021L);
    	t2.setStudy3_id(1074L);
    	t2.setUnit_id(1351L);
    	t2List.add(t2);
    	
    	ExamPaperFasterTactic2 t22 = new ExamPaperFasterTactic2();
    	t22.setStudy1_id(1014L);
    	t22.setStudy2_id(1021L);
    	t22.setStudy3_id(1075L);
//    	t22.setUnit_id(1360L);
    	t2List.add(t22);
    	
   
    	
    	paperFasterTactic.setExamPaperFasterTactic1(t1List);
    	paperFasterTactic.setExamPaperFasterTactic2(t2List);
    	
    	paper.setPaperFasterTactic(paperFasterTactic);
    	ExamPaperFasterTactic ss = examWebSeviceLocal.getQuestionSizeByFasterTactic(paperFasterTactic);
    	System.out.println(ss);
    	
//    	examWebSeviceLocal.addExamPaper(paper);
	}
	
    public static void getQuestionList(long pid){
    	try{
//    	
//    		List<ExamQuestion> list = examWebSeviceLocal.getQuestionListByPaperId(pid, 0).getReturnList();
//    		String n = examWebSeviceLocal.getExamPaperById(pid).getName();
//    		WritableWorkbook wwb = Workbook.createWorkbook(new File("/Users/zhaoming/Downloads/"+n+"_"+pid+".xls"));
//    		
    		ExamQuestionQuery query = new ExamQuestionQuery();
    		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
    		
    		ExamQuestionProp p1 = new ExamQuestionProp();
    		p1.setProp_val_id(1014L);
    		List<ExamQuestionProp> l1 = new ArrayList<ExamQuestionProp>();
    		l1.add(p1);    		
    		
    		ExamQuestionProp p2 = new ExamQuestionProp();
    		p2.setProp_val_id(1020L);
    		List<ExamQuestionProp> l2 = new ArrayList<ExamQuestionProp>();
    		l2.add(p2);    		
    		
    		ExamQuestionProp p3 = new ExamQuestionProp();
    		p3.setProp_val_id(1061L);
    		List<ExamQuestionProp> l3 = new ArrayList<ExamQuestionProp>();
    		l3.add(p3);
    		
    		ExamQuestionProp p4 = new ExamQuestionProp();
    		p4.setProp_val_id(1309L);
    		
    		ExamQuestionProp p4_1 = new ExamQuestionProp();
    		p4_1.setProp_val_id(1310L);    		
    		
    		List<ExamQuestionProp> l4 = new ArrayList<ExamQuestionProp>();
    		l4.add(p4); 
    		l4.add(p4_1);
    		
    		questionPropMap.put("1", l1);
    		questionPropMap.put("2", l2);
    		questionPropMap.put("3", l3);
    		questionPropMap.put("4", l4);
    		
    		query.setSubTypeId(1L);
    		query.setQuestionPropMap(questionPropMap);
    		query.setPageNo(1);
    		query.setPageSize(8000);
    		List<ExamQuestion> list = examWebSeviceLocal.getQuestionAllList(query);
    		
//    		List<ExamQuestion> list = examWebSeviceLocal.getQuestionListErrorPropId(3);
    		
    		
    		WritableWorkbook wwb = Workbook.createWorkbook(new File("/Users/zhaoming/Downloads/bbb.xls"));
    		
			int ncout = list.size();
			int maxnum = 50000; // 一次最多写入量
			int times = (ncout + maxnum - 1) / maxnum;
			for (int t = 0; t < times; t++) {
    		
				WritableSheet wsheet = wwb.createSheet("Sheet", 0);
				
				Label label = new Label(0, 0, "系统编号(必填)");
				wsheet.addCell(label);
				
				label = new Label(1, 0, "父试题编号");
				wsheet.addCell(label);
				
				label = new Label(2, 0, "题型ID");
				wsheet.addCell(label);
				
				label = new Label(3, 0, "试题内容");
				wsheet.addCell(label);
				
				label = new Label(4, 0, "可选答案A");
				wsheet.addCell(label);
				
				label = new Label(5, 0, "可选答案B");
				wsheet.addCell(label);
				
				label = new Label(6, 0, "可选答案C");
				wsheet.addCell(label);
				
				label = new Label(7, 0, "可选答案D");
				wsheet.addCell(label);
				
				label = new Label(8, 0, "可选答案E");
				wsheet.addCell(label);
				
				label = new Label(9, 0, "可选答案F");
				wsheet.addCell(label);
				
				label = new Label(10, 0, "答案");
				wsheet.addCell(label);
				
				label = new Label(11, 0, "试题分析");
				wsheet.addCell(label);
				
				label = new Label(12, 0, "来源");
				wsheet.addCell(label);
				
				
				label = new Label(13, 0, "一级学科");
				wsheet.addCell(label);
				
				label = new Label(14, 0, "二级学科");
				wsheet.addCell(label);
				
				label = new Label(15, 0, "三级学科");
				wsheet.addCell(label);
				
				label = new Label(16, 0, "单元");
				wsheet.addCell(label);
				
				label = new Label(17, 0, "知识点");
				wsheet.addCell(label);	
				
				
				label = new Label(18, 0, "副知识点");
				wsheet.addCell(label);
				
				label = new Label(19, 0, "认知水平");
				wsheet.addCell(label);
				
				label = new Label(20, 0, "职称");
				wsheet.addCell(label);
				
				label = new Label(21, 0, "时间");
				wsheet.addCell(label);
	    		
				label = new Label(22, 0, "author");
				wsheet.addCell(label);
				
				
				for (int i = 0; i < list.size(); i++) {
					ExamQuestion q = list.get(i);
					getQuestion(wsheet, q);
				}
			}
			
			
    		
			wwb.write();
			wwb.close();
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    

	private static void getQuestion(WritableSheet wsheet, ExamQuestion q)
			throws WriteException, RowsExceededException {
		Label label;
		int rowNum = wsheet.getRows();
		// 系统编号
		label = new Label(0, rowNum, String.valueOf(q.getId()));
		wsheet.addCell(label);
		
		// 系统编号
		label = new Label(1, rowNum, String.valueOf(q.getParent_id()));
		wsheet.addCell(label);		
		// 题型
		label = new Label(2, rowNum, String.valueOf(q.getQuestion_label_id()));
		wsheet.addCell(label);
		// 试题内容
		label = new Label(3, rowNum, q.getContent());
		wsheet.addCell(label);
		
		if (q.getQuestion_label_id() == 1 || q.getQuestion_label_id() == 2
				|| q.getQuestion_label_id() == 4
				|| q.getQuestion_label_id() == 5
				|| q.getQuestion_label_id() == 11) {
			
			for(int k=0;k<q.getQuestionKeyList().size();k++){
				ExamQuestionKey ek = q.getQuestionKeyList().get(k);
				label = new Label(4+k, rowNum, ek.getContent());
				wsheet.addCell(label);
			}
			
			label = new Label(10, rowNum, setKey(q.getQuestionKeyList()));
			wsheet.addCell(label);

		}
		
		if (q.getQuestion_label_id() == 12 || q.getQuestion_label_id() == 13
				|| q.getQuestion_label_id() == 14
				|| q.getQuestion_label_id() == 18
				|| q.getQuestion_label_id() == 20){
			label = new Label(10, rowNum, q.getQuestionKeyList().get(0).getContent());
			wsheet.addCell(label);
		}
		if(q.getQuestion_label_id() == 10){
			label = new Label(10, rowNum, q.getQuestionKeyList().get(0).getContent());
			wsheet.addCell(label);
		}
		
		//分析
		label = new Label(11, rowNum, q.getAnalyse());
		wsheet.addCell(label);
		//来源
		label = new Label(12, rowNum, q.getSource());
		wsheet.addCell(label);
		
		//来源
		label = new Label(21, rowNum, q.getCreate_date());
		wsheet.addCell(label);
		//来源
		label = new Label(22, rowNum, q.getAuthor());
		wsheet.addCell(label);
		
		
		if(q.getParent_id()==0){
			label = new Label(13, rowNum, setProp(q.getQuestionPropMap(),"1"));
			wsheet.addCell(label);
			
			label = new Label(14, rowNum, setProp(q.getQuestionPropMap(),"2"));
			wsheet.addCell(label);
			
			label = new Label(15, rowNum, setProp(q.getQuestionPropMap(),"3"));
			wsheet.addCell(label);
			
			label = new Label(16, rowNum, setProp(q.getQuestionPropMap(),"4"));
			wsheet.addCell(label);
			
			label = new Label(17, rowNum, setProp(q.getQuestionPropMap(),"5"));
			wsheet.addCell(label);
			
			label = new Label(18, rowNum, setProp(q.getQuestionPropMap(),"7"));
			wsheet.addCell(label);
			
			label = new Label(19, rowNum, setProp(q.getQuestionPropMap(),"8"));
			wsheet.addCell(label);
			
			label = new Label(20, rowNum, setProp(q.getQuestionPropMap(),"9"));
			wsheet.addCell(label);	
		}
		
		
//		 子试题信息
		if (q.getChildQuestionList() != null
				&& q.getChildQuestionList().size() > 0) {
			for (int i = 0; i < q.getChildQuestionList().size(); i++) {
				ExamQuestion examQuestion = q.getChildQuestionList().get(i);
				getQuestion(wsheet, examQuestion);
			}
		}
	}
	
	
	public static String setProp(Map<String,List<ExamQuestionProp>> questionPropMap,String key) {
		
		List<ExamQuestionProp> list = questionPropMap.get(key);
		
		
		String prop = "";
		
		if(list!=null){
			for(ExamQuestionProp p: list){
				if(prop.equals("")){
					prop = p.getProp_val_name()+"";
				}else{
					prop += "|"+p.getProp_val_name();
				}
			}
			return prop;
		}else{
			return prop;
		}
		
		
	}
    
    private static String setKey(List<ExamQuestionKey> keyList){
    	String key = "";
    		for(int i=0;i<keyList.size();i++){
    			ExamQuestionKey k = keyList.get(i);
    			if(k.getIsnot_true()==1){
    				key+=(char)(65+i);
    			}
    		}
    	return key;
    }
    
    
    
    private static void getQuestionById(Long id){
    	ExamQuestion q = examWebSeviceLocal.getExamQuestionById(id);
    	System.out.println(q.getAuthor()+"\t"+q.getCreate_date());
    }
    
    
    private static void getQuestionByIds(Long[] id){
    	List<ExamQuestion> list = examWebSeviceLocal.getQuestionIdByIdArr(id);
    	System.out.println(list);
    }
    
    public static void checkQuestionList(){
    	try{
        	    		
    		ExamQuestionQuery query = new ExamQuestionQuery();
    		
    		query.setQuestion_label_ids("1,2,3,9");
    		query.setSubTypeId(9L);
    		query.setPageNo(1);
    		query.setPageSize(8000);
    		
    		List<ExamQuestion> list = examWebSeviceLocal.getQuestionAllList(query);
    		
    		WritableWorkbook wwb = Workbook.createWorkbook(new File("/Users/zhaoming/Downloads/error.xls"));
    		
			int ncout = list.size();
			int maxnum = 50000; // 一次最多写入量
			int times = (ncout + maxnum - 1) / maxnum;
			for (int t = 0; t < times; t++) {
    		
				WritableSheet wsheet = wwb.createSheet("Sheet", 0);
				
				Label label = new Label(0, 0, "试题id");
				wsheet.addCell(label);
				
				label = new Label(1, 0, "父试题id");
				wsheet.addCell(label);
				
				label = new Label(2, 0, "题型ID");
				wsheet.addCell(label);
				
				label = new Label(3, 0, "试题内容");
				wsheet.addCell(label);
				
				label = new Label(4, 0, "ERROR");
				wsheet.addCell(label);
				
				for (int i = 0; i < list.size(); i++) {
					ExamQuestion q = list.get(i);
					checkQuestion(wsheet, q);
				}
			}
			
			
    		
			wwb.write();
			wwb.close();
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    
	private static void checkQuestion(WritableSheet wsheet, ExamQuestion q)
			throws WriteException, RowsExceededException {
		Label label;
		int rowNum = wsheet.getRows();
		
		if (q.getQuestion_label_id() == 1 
				|| q.getQuestion_label_id() == 2
				|| q.getQuestion_label_id() == 11
				|| q.getQuestion_label_id() == 12
				|| q.getQuestion_label_id() == 13
				|| q.getQuestion_label_id() == 14
				|| q.getQuestion_label_id() == 18
				|| q.getQuestion_label_id() == 20) {
			if(q.getQuestion_label_id() == 1 || q.getQuestion_label_id() == 2 ||q.getQuestion_label_id() == 11){
				boolean flag = true;
				String msg = "";
				if(q.getQuestionKeyList()!=null && !q.getQuestionKeyList().isEmpty()){
				
					for (int k = 0; k < q.getQuestionKeyList().size(); k++) {
						ExamQuestionKey ek = q.getQuestionKeyList().get(k);
						if(com.hys.exam.utils.StringUtils.checkNull(ek.getContent())){
							flag = false;
							msg = "选项为空";
							break;
						}
					}
					if(flag){
						if(setKey(q.getQuestionKeyList()).equals("")){
							msg = "答案为空";
							flag = false;
						}
					}
				} else{
					msg = "没有选项及答案";
					flag = false;
				}
				
				if(!flag){
					// 系统编号
					label = new Label(0, rowNum, String.valueOf(q.getId()));
					wsheet.addCell(label);
					// 系统编号
					label = new Label(1, rowNum, String.valueOf(q.getParent_id()));
					wsheet.addCell(label);
					// 题型
					label = new Label(2, rowNum, String.valueOf(q.getQuestion_label_id()));
					wsheet.addCell(label);
					// 试题内容
					label = new Label(3, rowNum, q.getContent());
					wsheet.addCell(label);
					// 试题内容
					label = new Label(4, rowNum, msg);
					wsheet.addCell(label);
				}
				
			}else{
				
			}
			
		}
		
		
		if(q.getQuestion_label_id() == 3 || q.getQuestion_label_id() == 9){
			boolean flag = true;
			
			String msg = "";
			
//				 子试题信息
			if (q.getChildQuestionList() != null
					&& q.getChildQuestionList().size() > 0) {
				for (int i = 0; i < q.getChildQuestionList().size(); i++) {
					ExamQuestion examQuestion = q.getChildQuestionList().get(i);
					checkQuestion(wsheet, examQuestion);
				}
			} else{
				msg = "没有子试题";
				flag = false;
			}
			
			if(!flag){
				// 系统编号
				label = new Label(0, rowNum, String.valueOf(q.getId()));
				wsheet.addCell(label);
				// 系统编号
				label = new Label(1, rowNum, String.valueOf(q.getParent_id()));
				wsheet.addCell(label);
				// 题型
				label = new Label(2, rowNum, String.valueOf(q.getQuestion_label_id()));
				wsheet.addCell(label);
				// 试题内容
				label = new Label(3, rowNum, q.getContent());
				wsheet.addCell(label);
				
				label = new Label(4, rowNum, msg);
				wsheet.addCell(label);
			}
		}
		
		if(q.getQuestion_label_id() == 4 || q.getQuestion_label_id() == 5 || q.getQuestion_label_id() == 10){
			boolean flag = true;
			String msg = "";
			if(q.getQuestionKeyList()!=null && !q.getQuestionKeyList().isEmpty()){
				if(q.getQuestion_label_id() == 4 || q.getQuestion_label_id() == 5){
					for (int k = 0; k < q.getQuestionKeyList().size(); k++) {
						ExamQuestionKey ek = q.getQuestionKeyList().get(k);
						if(com.hys.exam.utils.StringUtils.checkNull(ek.getContent())){
							flag = false;
							msg = "选项为空";
							break;
						}
					}
					if(flag){
						if(setKey(q.getQuestionKeyList()).equals("")){
							msg = "答案为空";
							flag = false;
						}
					}
					
					if(!flag){
						// 系统编号
						label = new Label(0, rowNum, String.valueOf(q.getId()));
						wsheet.addCell(label);
						// 系统编号
						label = new Label(1, rowNum, String.valueOf(q.getParent_id()));
						wsheet.addCell(label);
						// 题型
						label = new Label(2, rowNum, String.valueOf(q.getQuestion_label_id()));
						wsheet.addCell(label);
						// 试题内容
						label = new Label(3, rowNum, q.getContent());
						wsheet.addCell(label);
						
						label = new Label(4, rowNum, msg);
						wsheet.addCell(label);
					}
				} else {
					if (com.hys.exam.utils.StringUtils.checkNull(q.getQuestionKeyList().get(0)
							.getContent())) {
						msg = "答案为空";
						flag = false;
					}
				}
			} else{
				msg = "没有选项及答案";
				flag = false;
			}
			
			if(!flag){
				// 系统编号
				label = new Label(0, rowNum, String.valueOf(q.getId()));
				wsheet.addCell(label);
				// 系统编号
				label = new Label(1, rowNum, String.valueOf(q.getParent_id()));
				wsheet.addCell(label);
				// 题型
				label = new Label(2, rowNum, String.valueOf(q.getQuestion_label_id()));
				wsheet.addCell(label);
				// 试题内容
				label = new Label(3, rowNum, q.getContent());
				wsheet.addCell(label);
				
				label = new Label(4, rowNum, msg);
				wsheet.addCell(label);
			}
		}
		
	}
    

}
