/**
 *
 * <p>课件导入</p>
 * @author chenlaibin
 * @version 1.0 2014-3-29
 */

package com.hys.exam.struts.action.course;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.hys.auth.model.HysUsers;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.StudyCoursewareManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;

public class CoursewareImportAction extends BaseAction{

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private StudyCoursewareManage studyCoursewareManage;
	
	private ExamPropValFacade localExamPropValFacade;
	
	public void setStudyCoursewareManage(StudyCoursewareManage studyCoursewareManage) {
		this.studyCoursewareManage = studyCoursewareManage;
	}
	
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//修改链接
		String method = request.getParameter("method");
		if(StringUtils.isNotBlank(method) && method.equals("update")){
			return this.updateCoursewarePath(mapping, form, request, response);
		}
		
		DynaActionForm uploadForm = (DynaActionForm) form;

		FormFile file = (FormFile) uploadForm.get("uploadfile");

		
		if(file.getFileSize()>1024000){
			request.setAttribute("errMsg", "导入文件不能大于1M！");					
			return "FAILURE";
		}
		
		String fileName = file.getFileName();
		if(fileName.indexOf(".xlsx")>-1){
			request.setAttribute("errMsg", "请使用后缀名为.xls的文件！");
			return "FAILURE";
		}
		
		InputStream is = file.getInputStream();

		Workbook rwb = Workbook.getWorkbook(is);
		
		int errRow = 0;
		try {
			if (rwb.getNumberOfSheets() > 0) {

				List<StudyCourseware> coursewareList = new ArrayList<StudyCourseware>();
				Sheet sheet = rwb.getSheet(0);
				// sheet 列数
				int sheetCount = getSheetColumnsLength(sheet);
				
				// sheet 行数
				int rowNum = getSheetRowLength(sheet);
				
				if(sheetCount != 14){		//14
					request.setAttribute("errMsg", "导入文件格试有误，请检查是否是14列！");					
					return "FAILURE";
				}
				if(rowNum > 1000){
					request.setAttribute("errMsg", "总课件数不能大于1000道！");					
					return "FAILURE";
				}
				
				//试题行业
				List<Long> p7list = localExamPropValFacade.getExamPropTypeList(Constants.SYSTEM_PROP_TYPE_INDUSTRY);
				
				//试题认知水平
				List<Long> p8list = localExamPropValFacade.getExamPropTypeList(Constants.SYSTEM_PROP_TYPE_KNOW);
				
				//试题适用对象表
				List<Long> p9list = localExamPropValFacade.getExamPropTypeList(Constants.SYSTEM_PROP_TYPE_APPLICABLE);
				
				for (int i = 1; i < rowNum; i++) {
					errRow = i;
					StudyCourseware ware = new StudyCourseware();
					
					//课件名称
					String name = sheet.getCell(0,i).getContents().trim();
					if(StringUtils.isNotBlank(name)){
						ware.setName(name);
					}else{
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,课件名称为空，导入失败！");
						return "FAILURE";
					}
					
					//类型
					String type = sheet.getCell(1,i).getContents().trim();
					if(StringUtils.isNotBlank(type)){
						ware.setType(Integer.valueOf(type));
					}else{
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,课件类型为空，导入失败！");
						return "FAILURE";
					}
					
					//时长
					String times = sheet.getCell(2,i).getContents().trim();
					if(StringUtils.isNotBlank(times))
						ware.setTimes(Integer.valueOf(times));
					
					//摘要
					String summary = sheet.getCell(3,i).getContents().trim();
					if(StringUtils.isNotBlank(summary))
						ware.setSummary(summary);
					
					//关键字
					String keyWord = sheet.getCell(4,i).getContents().trim();
					if(StringUtils.isNotBlank(keyWord))
						ware.setKeyWord(keyWord);
					
					//授课老师姓名
					String teacherName = sheet.getCell(5,i).getContents().trim();
					if(StringUtils.isNotBlank(teacherName))
						ware.setTeacherName(teacherName);
					
					//制作年份
					String makeYear = sheet.getCell(8,i).getContents().trim();
					if(StringUtils.isNotBlank(makeYear))
						ware.setMakeYear(makeYear);
					
					//课件地址
					String path = sheet.getCell(9,i).getContents().trim();
					if(StringUtils.isNotBlank(path)){
						StringBuffer sb = new StringBuffer();
						if(path.indexOf("5277db")>-1){
							sb.append("<script src='http://static.polyv.net/file/polyvplayer_v2.0.min.js'></script>");
							sb.append("<div id='plv_"+path+"'></div>");
							sb.append("<script>");
							sb.append("var player = polyvObject('#plv_"+path+"').videoPlayer({");
							sb.append("'width':'"+Constants.STUDY_COURSEWARE_PLAYER_WIDTH+"','height':'"+
									Constants.STUDY_COURSEWARE_PLAYER_HGIGHT+"','vid' : '"+path+"'");
							sb.append("});");
							sb.append("</script>");
							ware.setPath(sb.toString());
						}else{
							ware.setPath(path);
						}
					}
					
					//状态
					ware.setStatus(1);
					
					SystemUser user = LogicUtil.getSystemUser(request);
					ware.setCreateUserId(user.getUserId());
					ware.setLastUpdateUserId(user.getUserId());
					
					List<ExamPropVal> industryList = new ArrayList<ExamPropVal>();		//行业
					List<ExamPropVal> knowList = new ArrayList<ExamPropVal>();			//知识分类
					List<ExamPropVal> applicableList = new ArrayList<ExamPropVal>();	//适用对象
					String msg = "";
					
					//行业
					String questPoint2 = sheet.getCell(11,i).getContents().trim();
					if(!questPoint2.equals("")){
						msg = setProp(questPoint2,(errRow+1),Constants.EXAM_PROP_POINT2, industryList, p7list);
						if(!msg.equals("")){
							request.setAttribute("errMsg", msg);
							return "FAILURE";							
						}
						ware.setIndustryList(industryList);
					}
					
					
					//知识分类
					String questCognize = sheet.getCell(12,i).getContents().trim();
					if(!questCognize.equals("")){
						msg = setProp(questCognize,(errRow+1),Constants.EXAM_PROP_COGNIZE, knowList, p8list);
						if(!msg.equals("")){
							request.setAttribute("errMsg", msg);
							return "FAILURE";							
						}
						ware.setKnowList(knowList);
					}
					
					//适用对象
					String questTitle = sheet.getCell(13,i).getContents().trim();
					if(!questTitle.equals("")){
						msg = setProp(questTitle,(errRow+1),Constants.EXAM_PROP_TITLE, applicableList, p9list);
						if(!msg.equals("")){
							request.setAttribute("errMsg", msg);
							return "FAILURE";							
						}
						ware.setApplicableList(applicableList);
					}
					
					coursewareList.add(ware);
					
				}
				
				if(Utils.isListEmpty(coursewareList)){
					request.setAttribute("errMsg", "没有找到可导入的数据！");
					return "FAILURE";
				}else{
					this.studyCoursewareManage.importStudyCourseware(coursewareList);
				}
				
			}
		} catch (Exception e) {
			request.setAttribute("errMsg", "第" +  (errRow+1) + "行数据格式错误，导入失败！");
			e.printStackTrace();
			return "FAILURE";
		} finally {
			is.close();
			rwb.close();
		}
		
		
		request.setAttribute("errMsg", "导入成功");		
		return "SUCCESS";
		
	}
	
	
	protected String updateCoursewarePath(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DynaActionForm uploadForm = (DynaActionForm) form;

		FormFile file = (FormFile) uploadForm.get("updatefile");

		
		if(file.getFileSize()>1024000){
			request.setAttribute("errMsg", "导入文件不能大于1M！");					
			return "FAILURE";
		}
		
		String fileName = file.getFileName();
		if(fileName.indexOf(".xlsx")>-1){
			request.setAttribute("errMsg", "请使用后缀名为.xls的文件！");
			return "FAILURE";
		}
		
		InputStream is = file.getInputStream();

		Workbook rwb = Workbook.getWorkbook(is);
		
		int errRow = 0;
		try {
			if (rwb.getNumberOfSheets() > 0) {

				List<StudyCourseware> coursewareList = new ArrayList<StudyCourseware>();
				Sheet sheet = rwb.getSheet(0);
				
				// sheet 列数
				int sheetCount = getSheetColumnsLength(sheet);
				
				// sheet 行数
				int rowNum = getSheetRowLength(sheet);
				if(sheetCount != 4){
					request.setAttribute("errMsg", "导入文件格试有误，请检查是否是4列！");					
					return "FAILURE";
				}
				if(rowNum > 1000){
					request.setAttribute("errMsg", "总课件数不能大于1000道！");					
					return "FAILURE";
				}
				
				for (int i = 1; i < rowNum; i++) {
					errRow = i;
					StudyCourseware ware = new StudyCourseware();
					
					//课件ID
					String id = sheet.getCell(0,i).getContents().trim();
					if(null != id && !"".equals(id)){
						ware.setId(new Long(id));
					}else{
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,课件ID为空，导入失败！");
						return "FAILURE";
					}
					
					//课件名
					String name = sheet.getCell(1,i).getContents().trim();
					if(null != name || !"".equals(name)){
						ware.setName(name);
					}else{
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,课件名称为空，导入失败！");
						return "FAILURE";
					}
					
					//课件地址
					String id_path = sheet.getCell(2,i).getContents().trim();
					StringBuffer sb = new StringBuffer();
					if(null != id_path && !"".equals(id_path)){
						sb.append("<script src='http://static.polyv.net/file/polyvplayer_v2.0.min.js'></script>");
						sb.append("<div id='plv_"+id_path+"'></div>");
						sb.append("<script>");
						sb.append("var player = polyvObject('#plv_"+id_path+"').videoPlayer({");
						sb.append("'width':'"+Constants.STUDY_COURSEWARE_PLAYER_WIDTH+"','height':'"+
								Constants.STUDY_COURSEWARE_PLAYER_HGIGHT+"','vid' : '"+id_path+"'");
						sb.append("});");
						sb.append("</script>");
						ware.setPath(sb.toString());
					}else{
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,课件链接为空，导入失败！");
						return "FAILURE";
					}
					
					//课件地址
					String asPath = sheet.getCell(3,i).getContents().trim();
					if(null != asPath && !"".equals(asPath)){
						ware.setAsPath(asPath);
					}else{
						request.setAttribute("errMsg", "第" + (errRow+1) + "行,课件链接为空，导入失败！");
						return "FAILURE";
					}
					
					SystemUser user = LogicUtil.getSystemUser(request);
					ware.setLastUpdateUserId(user.getUserId());
					
					coursewareList.add(ware);
				}
				
				if(Utils.isListEmpty(coursewareList)){
					request.setAttribute("errMsg", "没有找到可导入的数据！");
					return "FAILURE";
				}else{
					this.studyCoursewareManage.inportStudyCourseware2Update(coursewareList);
				}
				
			}
		} catch (Exception e) {
			request.setAttribute("errMsg", "第" +  (errRow+1) + "行数据格式错误，导入失败！");
			e.printStackTrace();
			return "FAILURE";
		} finally {
			is.close();
			rwb.close();
		}
		
		
		request.setAttribute("errMsg", "导入成功");		
		return "SUCCESS";
		
	}
	
	
	/**
	 * 
	 * @param propStr 属性
	 * @param rowNum 行号
	 * @param key 属性KEY
	 * @param  questionPropMap 试题属性
	 */
	private String setProp(String propStr,int rowNum,String key,List<ExamPropVal> propList ,List<Long> propIdList){
		String msg = "";
		try{
			ArrayList<String> tmp = new ArrayList<String>();
			tmp = StringUtils.stringToArrayList(propStr, ",");
			for(String propId : tmp){
				if(propIdList.contains(Long.parseLong(propId))){
					ExamPropVal prop = new ExamPropVal();
					prop.setId(Long.parseLong(propId));
					propList.add(prop);
				} else{
					if(key.equals(Constants.EXAM_PROP_POINT2)){
						msg += "行业";
					}else if(key.equals(Constants.EXAM_PROP_COGNIZE)){
						msg += "知识分类";
					}else if(key.equals(Constants.EXAM_PROP_TITLE)){
						msg += "适用对象数据";
					}
					return "第" +  rowNum + "行," + msg + "数据错误，导入失败！";					
				}
			}
			
		}catch(Exception e){
			if(key.equals(Constants.EXAM_PROP_POINT2)){
				msg += "行业";
			}else if(key.equals(Constants.EXAM_PROP_COGNIZE)){
				msg += "知识分类";
			}else if(key.equals(Constants.EXAM_PROP_TITLE)){
				msg += "适用对象数据";
			}
			return "第" +  rowNum + "行," + msg + "数据格式错误，导入失败！";
		}
		
		return msg;
	}
	
	/**
	 * 
	 * 计算sheet的列数
	 * 
	 * @param sheet
	 *            Excel工作表
	 * @return: int
	 */
	private int getSheetColumnsLength(Sheet sheet) {
		for (int i = 1; i < sheet.getColumns(); i++) {
			if (sheet.getCell(i, 0).getContents().trim().equals("")) {
				return i;
			}
		}
		return sheet.getColumns();
	}
	
	/**
	 * 计算sheet的行数
	 * 
	 * @param sheet
	 *            Excel 工作表
	 * @return
	 * @return: int
	 */
	private int getSheetRowLength(Sheet sheet) {
		for (int i = 1; i < sheet.getRows(); i++) {
			if (sheet.getCell(0, i).getContents().trim().equals("")) {
				return i;
			}
		}
		return sheet.getRows();
	}

}


