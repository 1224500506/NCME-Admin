package com.hys.exam.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2010-1-4
 * 
 * 描述：
 * 
 * 说明:
 */
public class Constants {

	public static final String TABLE_EXAM_QUESTION = "EXAM_QUESTION";
	public static final String TABLE_SYSTEM_CLIENT = "SYSTEM_CLIENT";
	public static final String TABLE_SYSTEM_ORG = "SYSTEM_ORG";
	public static final String TABLE_SYSTEM_SITE = "SYSTEM_SITE";
	public static final String TABLE_SYSTEM_APPLICATION = "SYSTEM_APPLICATION";
	public static final String TABLE_SYSTEM_ROLE = "SYSTEM_ROLE";
	public static final String TABLE_CASE_PATIENT = "CASE_PATIENT";
	public static final String UPLOAD_FILE_PATH_CASE_PATIENT = "../storage/upload/case";
	public static final String UPLOAD_FILE_PATH_EXPERT = "../storage/upload/expert";
	public static final String UPLOAD_FILE_PATH_MATERIAL = "../storage/upload/material";
	public static final String UPLOAD_FILE_PATH_CVS = "../storage/upload/CVS";
	public static final String UPLOAD_FILE_PATH_CV = "../storage/upload/CV";
	public static final String TABLE_XIANGYI_PROVINCE = "XIANGYI_PROVINCE";
	/**
	 * 试题答案
	 */
	public static final String TABLE_EXAM_QUESTION_KEY = "EXAM_QUESTION_KEY";
	
	
	public static final Map<String,String> getPropNameAll(){
		Map<String,String> propTable = new HashMap<String,String>();
		propTable.put(EXAM_PROP_STUDY1, TABLE_EXAM_QUESTION_STUDY1);
		propTable.put(EXAM_PROP_STUDY2, TABLE_EXAM_QUESTION_STUDY2);
		propTable.put(EXAM_PROP_STUDY3, TABLE_EXAM_QUESTION_STUDY3);
		propTable.put(EXAM_PROP_UNIT, TABLE_EXAM_QUESTION_UNIT);
		propTable.put(EXAM_PROP_POINT, TABLE_EXAM_QUESTION_POINT);
		propTable.put(EXAM_PROP_SYS, TABLE_EXAM_QUESTION_SYS);
		propTable.put(EXAM_PROP_POINT2, TABLE_EXAM_QUESTION_POINT2);
		propTable.put(EXAM_PROP_COGNIZE, TABLE_EXAM_QUESTION_COGNIZE);
		propTable.put(EXAM_PROP_TITLE, TABLE_EXAM_QUESTION_TITLE);
		propTable.put(EXAM_PROP_SOURCE, TABLE_EXAM_QUESTION_SOURCE);
		propTable.put(EXAM_PROP_ICD10, TABLE_EXAM_QUESTION_ICD10);
		return propTable;
	}
	
	
	

	/**
	 * 试题一级学科
	 */
	public static final String TABLE_EXAM_QUESTION_STUDY1 = "EXAM_QUESTION_STUDY1";
	public static final String EXAM_PROP_STUDY1 = "1";

	/**
	 * 试题二级学科
	 */
	public static final String TABLE_EXAM_QUESTION_STUDY2 = "EXAM_QUESTION_STUDY2";
	public static final String EXAM_PROP_STUDY2 = "2";
	/**
	 * 试题三级学科
	 */
	public static final String TABLE_EXAM_QUESTION_STUDY3 = "EXAM_QUESTION_STUDY3";
	public static final String EXAM_PROP_STUDY3 = "3";

	/**
	 * 试题单元表
	 */
	public static final String TABLE_EXAM_QUESTION_UNIT = "EXAM_QUESTION_UNIT";
	public static final String EXAM_PROP_UNIT = "4";

	/**
	 * 试题知识点表
	 */
	public static final String TABLE_EXAM_QUESTION_POINT = "EXAM_QUESTION_POINT";
	public static final String EXAM_PROP_POINT = "5";
	/**
	 * 试题系统
	 */
	public static final String TABLE_EXAM_QUESTION_SYS = "EXAM_QUESTION_SYS";
	public static final String EXAM_PROP_SYS = "6";
	/**
	 * 试题副知识点---改为行业
	 */
	public static final String TABLE_EXAM_QUESTION_POINT2 = "EXAM_QUESTION_POINT2";
	public static final String EXAM_PROP_POINT2 = "7";
	/**
	 * 试题认知水平
	 */
	public static final String TABLE_EXAM_QUESTION_COGNIZE = "EXAM_QUESTION_COGNIZE";
	public static final String EXAM_PROP_COGNIZE = "8";
	/**
	 * 试题被收入题库
	 */
//	public static final String TABLE_EXAM_QUESTION_QUEST_LIB = "EXAM_QUESTION_QUEST_LIB";

	/**
	 * 试题职称表
	 */
	public static final String TABLE_EXAM_QUESTION_TITLE = "EXAM_QUESTION_TITLE";
	public static final String EXAM_PROP_TITLE = "9";
	
	public static final String TABLE_EXAM_QUESTION_SOURCE = "EXAM_QUESTION_SOURCE";
	public static final String EXAM_PROP_SOURCE = "10";
	
	public static final String TABLE_EXAM_QUESTION_ICD10 = "EXAM_QUESTION_ICD10";
	public static final String EXAM_PROP_ICD10 = "11";
	
	public static final int A1 = 1; // 单选题(A1)
	public static final int A2 = 2; // 单选题(A2)
	public static final int A3 = 3; // 单选题(A3)
	public static final int A3_1 = 4; // 单选题(A3-1)
	public static final int A3_2 = 5; // 多选题(A3-2)
	public static final int A4 = 6; // 单选题(A4)
	public static final int A4_1 = 7; // 单选题(A4-1)
	public static final int A4_2 = 8; // 多选题(A4-2)
	public static final int B1 = 9; // 单选题(B1)
	public static final int B1_1 = 10; // 单选题(B1-1)
	public static final int DXT = 11; // 多选题(X)
	public static final int TK = 12; // 填空题
	public static final int PD = 13; // 判断题
	public static final int JD = 14; // 简答题
	public static final int D1 = 15; // 简答题(D1)
	public static final int D2 = 16; // 简答题(D2)
	public static final int D2_1 = 17; // 简答题(D2-1)
	public static final int MCJS = 18; // 名词解释
	public static final int PDGC = 19; // 判断改错题
	public static final int NLFX = 20; // 案例分析题
	
	
	public static final int PAPER_MODE_FIX = 3; // 出卷方式  固定试卷
	
	public static final int PAPER_MODE_RM = 2; //出卷方式　精细策略
	
	public static final int PAPER_MODE_FT = 1; //出卷方式　快捷策略
	
	public static final int PAPER_MODE_FT2 = 5; //出卷方式　快捷策略2
	
	public static final int PAPER_MODE_S = 4; //出卷方式　卷中卷

	public static final String SUCCESS   = "SUCCESS";
	public static final String SUCCESS_1 = "SUCCESS_1";
	public static final String SUCCESS_2 = "SUCCESS_2";
	public static final String SUCCESS_3 = "SUCCESS_3";

	//课程分类状态
	public static final int STATUS_1 = 1 ; //正常状态
	public static final int STATUS_2 = -1 ;//删除状态
	public static final int STATUS_3 = 0 ; //默认状态

	// 缺省字符集
	final static public String ENCODING_DEFAULT = "UTF-8";
	// 缺省缓存信息
	final static public boolean NOCACHE_DEFAULT = true;
	
	public static final String MESSAGE = "MESSAGE" ;
	public static final String MESSAGE_1 = "保存成功!" ;
	public static final String MESSAGE_2 = "删除成功!" ;

	public static final String SESSION_TRAIN_ADMIN_USER = "SESSION_TRAIN_ADMIN_USER";//session用户key

	//课程类别信息
	public static final int STUDY_COURSE_TYPE_1 = 1 ; //单屏
	public static final int STUDY_COURSE_TYPE_2 = 2 ; //三屏
	public static final int STUDY_COURSE_TYPE_3 = 3 ; //flash
	public static final int STUDY_COURSE_TYPE_4 = 4 ; //静态页面
	public static final int STUDY_COURSE_TYPE_5 = 5 ; //混合
	
	/**
	 * 出卷方式  1：快捷策略 1 
	 */
	public static final Integer PAPER_MODE_KJ1 = 1;
	/**
	 * 出卷方式  5：快捷策略2  
	 */
	public static final Integer PAPER_MODE_KJ = 5;
	/**
	 * 出卷方式   2：精细策略  
	 */
	public static final Integer PAPER_MODE_JX = 2;
	/**
	 * 出卷方式  3：手工组卷   
	 */
	public static final Integer PAPER_MODE_SG = 3;
	/**
	 * 出卷方式  4：卷中卷
	 */
	public static final Integer PAPER_MODE_JZJ = 4;
	
	/**
	 * 监考类别
	 */
	public static final Integer INVIGILATE_TYPE_JK = 1;
	
	/**
	 * 判卷类别
	 */
	public static final Integer INVIGILATE_TYPE_PJ = 2;
	
	//课程分类信息map
	public final static Map<Integer, Integer> curTypeMap = new HashMap<Integer, Integer>() ;
	
	//试题题型Map
	public final static Map<Integer, String> questLabelMap = new HashMap<Integer, String>() ;
	
	//出题策略类型
	public final static Map<Integer, String> pageModeMap = new HashMap<Integer, String>() ;
	
	static{
		curTypeMap.put(STUDY_COURSE_TYPE_1, STUDY_COURSE_TYPE_1) ;
		curTypeMap.put(STUDY_COURSE_TYPE_2, STUDY_COURSE_TYPE_2) ;
		curTypeMap.put(STUDY_COURSE_TYPE_3, STUDY_COURSE_TYPE_3) ;
		curTypeMap.put(STUDY_COURSE_TYPE_4, STUDY_COURSE_TYPE_4) ;
		curTypeMap.put(STUDY_COURSE_TYPE_5, STUDY_COURSE_TYPE_5) ;
		
		//试题题型信息
		questLabelMap.put(A1,	"单选题(A1)");
		questLabelMap.put(A2,	"单选题(A2)");
		questLabelMap.put(A3,	"单选题(A3)");
		questLabelMap.put(A3_1,	"单选题(A3-1)");
		questLabelMap.put(A3_2,	"多选题(A3-2)");
		questLabelMap.put(A4,	"单选题(A4)");
		questLabelMap.put(A4_1,	"单选题(A4-1)");
		questLabelMap.put(A4_2,	"多选题(A4-2)");
		questLabelMap.put(B1,	"单选题(B1)");
		questLabelMap.put(B1_1,	"单选题(B1-1)");
		questLabelMap.put(DXT,	"多选题(X)");
		questLabelMap.put(TK,	"填空题");
		questLabelMap.put(PD,	"判断题");
		questLabelMap.put(JD,	"简答题");
		questLabelMap.put(D1,	"简答题(D1)");
		questLabelMap.put(D2,	"简答题(D2)");
		questLabelMap.put(D2_1, "简答题(D2-1)");
		questLabelMap.put(MCJS, "名词解释");
		questLabelMap.put(PDGC, "判断改错题");
		questLabelMap.put(NLFX, "案例分析题");
		
		pageModeMap.put(PAPER_MODE_KJ1, "快捷策略1");
		pageModeMap.put(PAPER_MODE_KJ, 	"快捷策略2");
		pageModeMap.put(PAPER_MODE_JX, 	"精细策略");
		pageModeMap.put(PAPER_MODE_SG, 	"手工组卷");
		pageModeMap.put(PAPER_MODE_JZJ, "卷中卷");
	}

	public static final String IS_RELOAD_ADD = "IS_RELOAD_ADD";//

	public static final String IS_RELOAD_DELETE = "IS_RELOAD_DELETE";//
	
	public static String[] RESULT = {"A","B","C","D","E","F","G","H"};
	
	public static  boolean validateLicense = false;
	
	public static int TREE_PARENT_ID = -1 ;//最高父节点ID 
	
	public static long SUB_SYSTEM_ID = 7 ; //当前系统ID
	
	public static Integer[] XSJ_QUESTION_ID = {1, 2, 3, 4, 5, 6, 9, 10, 11};// 新世界下的题库

	public static final int MILLISECOND = 1000;//毫秒
	
	//=============================================
	public static final int SYSTEM_INDUSTRY_ROOT_ID = 0;	//行业岗位根id为0
	public static final int SYSTEM_NORMAL_STATUS = 1;		//正常状态
	public static final int SYSTEM_DELETE_STATUS = -1;		//删除状态
	
	public static final Long AJPX_SYSTEM_SITE_ID = 1L;		//www.ncme.org.cn  YHQ，2017-04-04，24L;		//安监培训站点ID
	
	/**
	 * 导出全部数据时的一页记录
	 * 1页导出最大值
	 */
	final static public int EXPORT_MAX_NUM = 9999999;
	
	public static final Long AJPX_ORG_BJ_ID = 1L;			//北京 机构ID (system_org)
	public static final Long AJPX_ADMIN_ORG_BJ_ID = 2L;		//继教地区 北京 ID	(system_admin_org)
	public static final Long AJPX_SYSTEM_CROUSE_TYPE_TRAINING = 1L;		//全员培训
	public static final Long AJPX_SYSTEM_COURSE_TYPE_POSITION = 10083L;	//课程分类-岗位
	public static final Long AJPX_SYSTEM_COURSE_TYPE_ELECTIVE = 10102L;	//课程分类-选修
	
	public static final Long AJPX_SYSTEM_COURSE_TYPE_ENTERPRISESLEADER = 10181L;	//课程分类-企业负责人
	public static final Long AJPX_SYSTEM_COURSE_TYPE_ENTERPRISESLEADER_GET = 10182L;	//课程分类-企业负责人-取证
	public static final Long AJPX_SYSTEM_COURSE_TYPE_ENTERPRISESLEADER_CON = 10183L;	//课程分类-企业负责人-继教
	
	
	
	public static final int ANQUAN100_SITE_ID = 24;			//www.anquan100.com站点ID
	public static final String ANQUAN100_SITE = "www.anquan100.com";
	
	
	public static final int STUDY_COURSE_STATUS_NOPUBLISH = 0;		//未发布
	public static final int STUDY_COURSE_STATUS_PUBLISHED = 1;		//已发布
	public static final int STUDY_COURSE_STATUS_DOWNLINE = 2;		//已下线
	public static final int STUDY_COURSE_STATUS_CANCEL= 3;			//已作废
	public static final int STUDY_COURSE_STATUS_DELETED = -1;		//已删除
	
	public static final Double SYSTEM_CARD_DEFAULT_BALANCE = 0.0D;	//学习卡默认余额为0	
	
	public static final int SYSTEM_CARD_LENGTH = 11;				//学习卡长度,默认11位
	
	public static final String SYSTEM_CARD_TYPE_BLANK_NAME = "空白";		//空白卡类型
	
	
	public static final int SYSTEM_CARD_STATUS_EFFECTIVE = 1;			//有效
	public static final int SYSTEM_CARD_STATUS_TO_BE_EFFECTIVE = 0;		//待生效
	public static final int SYSTEM_CARD_STATUS_DISABLE = -2;			//禁用
	public static final int SYSTEM_CARD_STATUS_DELETE = -1;				//删除
	
	public static final int SYSTEM_PROP_TYPE_INDUSTRY = 7;			//属性--行业
	public static final int SYSTEM_PROP_TYPE_KNOW = 8;				//属性--知识分类
	public static final int SYSTEM_PROP_TYPE_APPLICABLE = 9;		//属性--适用对象 
	
	public static final String COURSE_IMG_PATH="/images/course_img";
	
	//保利威视的视频播放器宽高
	public static final String STUDY_COURSEWARE_PLAYER_WIDTH = "600";
	public static final String STUDY_COURSEWARE_PLAYER_HGIGHT = "480";
	
	public static final int PROP_LOCALAREA_TYPE	= 1;
	public static final int PROP_HOSPITAL_TYPE	= 2;
	public static final int PROP_DUTY_TYPE 		= 3;
	public static final int PROP_MAJOR_TYPE		= 4;
	public static final int PROP_MAJORLevel_TYPE = 5;       //专业级别
	public static final int TeacherType = 1;
	public static final int OtherTeacherType=2;
	
	
	//chenlb add
	public static final int cvset_project_stauts_1 = 1;		//未提交
	public static final int cvset_project_stauts_2 = 2;		//审核中
	public static final int cvset_project_stauts_3 = 3;		//审核合格
	public static final int cvset_project_stauts_4 = 4;		//审核不合格
	public static final int cvset_project_stauts_5 = 5;		//已发布
	public static final int cvset_project_stauts_6 = 6;		//已下线
	
	public static final int CVSET_TYPE_MANAGER=1;//项目负责人
	public static final int CVSET_TYPE_TEACHER=2;//项目老师
	
	//学习卡类型为空卡
	public static final int SystemCard_TYPE_NULL=21;
	
}
