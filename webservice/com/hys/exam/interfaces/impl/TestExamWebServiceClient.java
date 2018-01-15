package com.hys.exam.interfaces.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hys.exam.interfaces.ExamWebService;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 14, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class TestExamWebServiceClient {
	
	public static String url = "http://127.0.0.1:8080/exam_question_lib3_2/remote/";
	
	private static HessianProxyFactory hessianFactory;
	
	private static ExamWebService examWebService;
	
    private static void initService() {
    	try {
            System.out.println("远程服务初始化");
            hessianFactory = new HessianProxyFactory();
            examWebService = (ExamWebService) hessianFactory.create(ExamWebService.class,
                    url + "examWebService");
        } catch (MalformedURLException e) {
            throw new RuntimeException("远程服务初始化失败");
        }    	
    }
    
    
    public static void main(String args[]){
    	  initService();
//    	  get();
    	  getQuestionList();
    }
    
    public static void getQuestionList(){
    	try{
    	
    		String key = examWebService.beginTransaction();
    		Long[] idArr = {333L,444L,555L,10212L};
    		List<ExamQuestion> list = examWebService.getQuestionIdByIdArr(key, idArr);
    		examWebService.commit(key);
    		
    		for(ExamQuestion q : list){
    			System.out.println(q.getContent()+"\t"+q.getCreate_date());
    		}
    		
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    
    public static void get(){
    	List<ExamQuestionType> tl =new ArrayList<ExamQuestionType>();
    	ExamQuestionTypeQuery query = new ExamQuestionTypeQuery();
    	query.setParent_id(0L);
    	String key = null;
		try {
			key = examWebService.beginTransaction();
			tl = examWebService.getExamQuestionTypeListByParentId(key, query).getReturnList();
			examWebService.commit(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		for(ExamQuestionType t: tl){
			System.out.println(t.getName());
		}
		
    }
}
