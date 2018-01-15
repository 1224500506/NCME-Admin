package com.hys.exam.struts.action.system;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.model.HysUsers;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemCardCreateRecord;
import com.hys.exam.model.system.SystemCardType;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.struts.action.AppBaseAction;

public class EditManageAction extends AppBaseAction{
	
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("save")){
			return this.save(mapping, form, request, response);
		} 
		return null;
	}
	
	
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return "list";
	}	
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		
		
		return null;
	}

}
