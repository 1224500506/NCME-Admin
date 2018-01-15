package com.hys.exam.struts.action.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.framework.web.action.BaseAction;

public class MyMessageManage extends BaseAction{

	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getParameter("method");
		
		if(method != null && method.equals("readAll"))
		{
			return readAll(request, response);
		}
		if(method != null && method.equals("test"))
		{
			 return null;
		}
		
		
		
		else if(method != null && method.equals("deleteAll"))
		{
			return deleteAll(request, response);
		}
		else if(method != null && method.equals("sendMessage"))
		{
			return sendMessage(request, response);
		}
		else if(method != null && method.equals("deleteMessage"))
		{
			return deleteMessage(request, response);
		}
		
		else if(method != null && method.equals("saveMessage"))
		{
			 SaveMessage(request, response);
			 return null;
		}
		
		
		else
		{
			
			return list( mapping, form, request,response);
		}
	}

	//发送邮件或者站内消息
	private String sendMessage(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}


	private String list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private void SaveMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private String deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String deleteAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String readAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
