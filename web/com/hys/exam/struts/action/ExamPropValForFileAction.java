package com.hys.exam.struts.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：zdz 2010-10-25
 * 
 * 描述：
 * 
 * 说明:生成试题属性文件
 */
public class ExamPropValForFileAction extends BaseAction {

	private ExamPropValFacade localExamPropValFacade;


	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}


	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}


	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String sid = request.getParameter("sid") == null ? "0" : request.getParameter("sid");
		
		String type = request.getParameter("type") == null ? "0" : request.getParameter("type");
		
		List<ExamPropValTemp> list = localExamPropValFacade.getBasePropVal(Integer.valueOf(sid));
		
		String fname = "data";

		
		if(sid.equals("1")){
			if(!type.equals("0")){ //妇社 后二级
				fname = sid+"_3_"+fname;
				list = list.get(0).getSubItems().get(0).getSubItems().get(0).getSubItems();
			} else {
				fname = sid+"_"+fname; //妇社全
			}
		} else if(sid.equals("0")) {
			if(type.equals("1")){ //全
				List<ExamPropValTemp> list1 = localExamPropValFacade.getBasePropVal(1); //全
				list.addAll(list1);
				fname = fname+"_all";
			}
		}
		
		StringBuffer sb = new StringBuffer("function getLoc() { return ");
		String jsonstr = "";
		jsonstr = JSONArray.fromObject(list).toString();
		sb.append(jsonstr);
		sb.append("}");
//		System.out.println(sb.toString());

		File file = new File(this.getWebRealPath()+"/js/"+fname+".js");
		if(file.exists()&&file.isDirectory()) {
			file.delete();
		}
		
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"utf-8");

		out.write(sb.toString());
		out.close();

		
		response.setContentType("text/html;charset=UTF-8");
		StringBuffer tmp = new StringBuffer();
		tmp.append("<script type=\"text/javascript\">");
		tmp.append("alert('文件生成成功 FileName:"+fname+".js');");
		tmp.append("</script>");
		response.getWriter().write(tmp.toString());	
		response.getWriter().flush();
		response.getWriter().close();
		
		return null;
	}
	
	
	public String getWebRealPath(){
		String web_path = this.getClass().getResource("/").getPath();
		if(System.getProperty("os.name").equals("Linux") || System.getProperty("os.name").equals("Mac OS X")){
			web_path=web_path.substring(0, web_path.indexOf("WEB-INF"));
		}else{
			web_path=web_path.substring(1, web_path.indexOf("WEB-INF"));
		}
		return web_path;
	}



}
