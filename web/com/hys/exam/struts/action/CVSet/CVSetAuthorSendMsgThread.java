package com.hys.exam.struts.action.CVSet;


import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.exam.model.CVSet;
import com.hys.exam.model.SystemMessageModel;
import com.hys.exam.service.local.CVSetAuthorizationManage;
import com.hys.framework.web.action.BaseAction;
/**
 * 使用异步的方式进行初始化数据
 * @author taoLiang
 *
 */
public class CVSetAuthorSendMsgThread extends BaseAction implements Callable<String>{
	
	private List<CVSet> msgIds;
	private CVSetAuthorizationManage cVSetAuthorizationManage;
	private Long flag;
	
	public CVSetAuthorSendMsgThread(List<CVSet> msgIds, CVSetAuthorizationManage cVSetAuthorizationManage, Long flag) {
		this.msgIds = msgIds;
		this.cVSetAuthorizationManage = cVSetAuthorizationManage;
		this.flag = flag;
	}
	
	@Override
	public String call() {
		System.out.println("开始进行系统消息推送。。。。。。。。。");
		try{
			System.out.println("5秒后开始。。。。。。"+new Date());
			Thread.sleep(5000);
			this.initRecord();
		}catch (Exception e) {
			System.out.println("初始化异常！");
			e.printStackTrace();
			return "初始化异常！";
		}
		return null;
	}
	
	public void initRecord(){
		try{
			for(CVSet cvset : msgIds)
				cVSetAuthorizationManage.SendMessageForUser(cvset, flag);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
