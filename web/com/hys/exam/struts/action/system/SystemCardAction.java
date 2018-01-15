package com.hys.exam.struts.action.system;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemCardCreateRecord;
import com.hys.exam.model.system.SystemCardType;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.model.systemQuery.SystemCardTypeQuery;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.struts.action.AppBaseAction;

/**
*
* <p>Description: 学习卡</p>
* @author chenlaibin
* @version 1.0 2013-12-14
*/
public class SystemCardAction extends AppBaseAction{

	private SystemCardManage systemCardManage;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("selectCardType")){
			return this.selectCardType(mapping, form, request, response);
		}else if(method.equals("edit")){
			return this.edit(mapping, form, request, response);
		}
		else if(method.equals("delBatch")){
			return this.delBatch(mapping, form, request, response);
		}else if(method.equals("save")){
			return this.save(mapping, form, request, response);
		}else if(method.equals("allotList")){
			return this.allotList(mapping, form, request, response);
		}else if(method.equals("cardTypeList")){
			return this.cardTypeList(mapping, form, request, response);
		}else if(method.equals("allotCardType")){
			return this.allotCardType(mapping, form, request, response);
		}else if(method.equals("allotCardTypeByNum")){
			return this.allotCardTypeByNum(mapping, form, request, response);
		}else if(method.equals("createCards")){
			return this.createCards(mapping, form, request, response);
		}else if(method.equals("doCreateCards")){
			return this.doCreateCards(mapping, form, request, response);
		}else if(method.equals("createCardList")){
			return this.createCardList(mapping, form, request, response);
		}else if(method.equals("exportCards")){
			return this.exportCards(mapping, form, request, response);
		}else if(method.equals("dirictProcessCard")){
			
		}
		return null;
	}
	
	//查看学习卡列表
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		boolean isAll = true;
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
		String cardNumber = ParamUtils.getParameter(request, "cardNumber", "");
		String cardNumberEnd = ParamUtils.getParameter(request, "cardNumberEnd", "");
		//Page<SystemCardQuery> page = this.createPage(request, "systemCard");
		Page<SystemCardQuery> page = this.createPage(request, "p");
		String source = ParamUtils.getParameter(request, "s");
		if(!source.equals("index")){
			this.systemCardManage.getSystemCardList(page, isAll, cardTypeId, cardNumber, cardNumberEnd);
		}
		request.setAttribute("page", page);
		request.setAttribute("cardTypeName", request.getParameter("cardTypeName"));
		if(request.getParameter("cardTypeId") != null && !"".equals(request.getParameter("cardTypeId"))){
			Long cTId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
			String cardTypeName = systemCardManage.getSystemCardTypeById(cTId).getCardTypeName();
			request.setAttribute("cardTypeName", cardTypeName);
		}
		request.setAttribute("cardTypeId", request.getParameter("cardTypeId"));
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardNumberEnd", cardNumberEnd);
		Integer row = (Integer)request.getAttribute("row");
		String meg = "";
		if(null != row){
			if(row >0){
				meg = "保存成功！";
			}else{
				meg = "网络原因，保存不成功,请稍后再试！";
			}
		}
		request.setAttribute("meg", meg);
		return "list";
	}

	//选择卡类型
	@SuppressWarnings("static-access")
	protected String selectCardType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String cardTypeName = ParamUtils.getParameter(request, "cardTypeName", "");
		Page <SystemCardTypeQuery> page = this.createPage(request, "isystemCardType");
		this.systemCardManage.getSystemCardTypeList(page, cardTypeName, "", "");
		request.setAttribute("page", page);
		return "selectCardType";
	}
	
	//修改
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
		//Integer sellStyle = ParamUtils.getIntParameter(request, "sellStyle", 1);
		
		if(id>0){
			SystemCardQuery card = this.systemCardManage.getSystemCardById(id);
			request.setAttribute("card", card);
			if(null != card && StringUtils.isNotBlank(card.getCardNumber()))
				request.setAttribute("cardNumber", card.getCardNumber());
		}/*else{
			request.setAttribute("cardNumber", this.systemCardManage.getSystemCardSerialNumber(sellStyle));
		}*/
		List<SystemCardType> typeList = this.systemCardManage.getSystemCardTypeList();
		request.setAttribute("typeList", typeList);
		request.setAttribute("cardTypeId", cardTypeId);
		return "edit";
	}
	
	//批量导入学习卡
	protected String importSystemCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return null;
	}
	
	//删除
	protected String delBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		logger.warn("into SystemCard.delBatch...");
		int count = 0;
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0, _cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = this.switchStringtoLongArray(ids);
		count = this.systemCardManage.delSystemCard(cardIds);
		Utils.renderText(response, String.valueOf(count));
		return null;
	}
	
	//直接处理学习卡
	protected String dirictProcessCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		logger.warn("into SystemCard.dirictProcessCard...");
		//Long id = ParamUtils.getLongParameter(request, "cardId", 0);
		//SystemCardQuery card = this.systemCardManage.getSystemCardById(id);
		
		//得到卡类型列表
		
		
		return null;
	}
	
	//保存
	protected String save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		int row = 0;
		SystemCard c = new SystemCard();
		Date date = new Date();
		c.setId(ParamUtils.getLongParameter(request, "id", 0));
		c.setCardNumber(ParamUtils.getParameter(request, "cardNumber"));
		c.setCardPassword(ParamUtils.getParameter(request, "cardPassword"));
		c.setCardType(ParamUtils.getLongParameter(request, "cardType", 0));
		c.setIsselled(0);
		c.setSellStyle(ParamUtils.getIntParameter(request, "sellStyle", 1));
		c.setUsefulDate(ParamUtils.getDateParamenter(request, "usefulDate", "yyyy-MM-dd"));
		//c.setIsnotBind(ParamUtils.getIntParameter(request, "isnotBind", 0));
		c.setIsnotBind(0);	//默认值
		c.setFaceValue(ParamUtils.getIntParameter(request, "faceValue", 0));
		if(c.getIsnotBind() == 1){
			c.setBindDate(date);
		}
		c.setImportDate(null);
		c.setCreateDate(date);
		c.setBalance(ParamUtils.getDoubleParameter(request, "balance", 0.0D));
		SystemUser user = LogicUtil.getSystemUser(request);
		c.setCreateUserId(user.getUserId());
		
		c.setStatus(Constants.SYSTEM_NORMAL_STATUS);
		                          
		row = this.systemCardManage.saveSysgemCard(c);
		request.setAttribute("row", row);
		return this.list(mapping, form, request, response);
	}
	
	//卡列表（for allot）
	@SuppressWarnings("static-access")
	protected String allotList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		boolean isAll = false;
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
		String cardNumber = ParamUtils.getParameter(request, "cardNumber", "");
		String cardNumberEnd = ParamUtils.getParameter(request, "cardNumberEnd", "");
		Page<SystemCardQuery> page = this.createPage_temp_2017(request, "systemCard");
		String source = ParamUtils.getParameter(request, "s");
		if(!"index".equals(source)){
			this.systemCardManage.getSystemCardList(page, isAll, cardTypeId, cardNumber, cardNumberEnd);
		}
		request.setAttribute("page", page);
		request.setAttribute("cardTypeName", request.getParameter("cardTypeName"));
		request.setAttribute("cardTypeId", request.getParameter("cardTypeId"));
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardNumberEnd", cardNumberEnd);
		return "allotList";
	}
	
	//选择卡类型
	@SuppressWarnings("static-access")
	protected String cardTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String cardTypeName = ParamUtils.getParameter(request, "cardTypeName", "");
		Page <SystemCardTypeQuery> page = this.createPage(request, "isystemCardType");
		
		String cardNumber = ParamUtils.getParameter(request, "cardNumber", "");
		String cardNumberEnd = ParamUtils.getParameter(request, "cardNumberEnd", "");
		
		this.systemCardManage.getSystemCardTypeList(page, cardTypeName, "", "");
		request.setAttribute("page", page);
		request.setAttribute("cardIds", ParamUtils.getParameter(request, "cardIds"));
		request.setAttribute("beforeTypeId", ParamUtils.getLongParameter(request, "beforeTypeId", -1));
		request.setAttribute("allotNum", ParamUtils.getIntParameter(request, "allotNum", -1));
		
		request.setAttribute("cardNumber", cardNumber);
		request.setAttribute("cardNumberEnd", cardNumberEnd);
		return "cardTypeList";
	}
	
	//分配卡类型
	protected String allotCardType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Long typeId = ParamUtils.getLongParameter(request, "cardTypeId", 0);
		String _cardIds = ParamUtils.getParameter(request, "cardIds");
		_cardIds = _cardIds.substring(0,_cardIds.length()-1);
		String [] ids = _cardIds.split(",");
		Long [] cardIds = switchStringtoLongArray(ids);
		int row = this.systemCardManage.saveSystemCardAllotCardType(typeId, cardIds);
		Utils.renderText(response, String.valueOf(row));
		return null;
	}
	
	//分配卡类型(批量)
	protected String allotCardTypeByNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long beforeTypeId = ParamUtils.getLongParameter(request, "beforeTypeId", -1);
		Integer allotNum = ParamUtils.getIntParameter(request, "allotNum", -1);
		Long afterTypeId = ParamUtils.getLongParameter(request, "afterTypeId", -1);
		String cardNumber = ParamUtils.getParameter(request, "cardNumber", "");
		String cardNumberEnd = ParamUtils.getParameter(request, "cardNumberEnd", "");
		int row = 0;
		if(allotNum > 0){
			row = this.systemCardManage.saveSystemCardTypeAllotByNum(beforeTypeId, afterTypeId, allotNum, cardNumber, cardNumberEnd);
		}
		Utils.renderText(response, String.valueOf(row));
		return null;
	}
	
	//生成卡号页面
	public String createCards(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<SystemCardType> typeList = this.systemCardManage.getSystemCardTypeList();
		if(null != typeList && !typeList.isEmpty()){
			for (Iterator<SystemCardType> iterator = typeList.iterator(); iterator.hasNext();) {
				SystemCardType sct = iterator.next();
				if(!sct.getCardTypeName().contains(Constants.SYSTEM_CARD_TYPE_BLANK_NAME)){
					iterator.remove();
				}
			}
		}
		request.setAttribute("typeList", typeList);
		return "createCards";
	}
	
	//制卡列表
	public String createCardList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		@SuppressWarnings("static-access")
		Page<SystemCardCreateRecord> page = this.createPage(request, "cardRecord");
		this.systemCardManage.getSystemCardCreateRecordList(page);
		request.setAttribute("page", page);
		return "createCardList";
	}
	
	//制卡记录导出
	public String exportCards(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, WriteException{
		
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=\"card.xls\"");
		OutputStream os = response.getOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		Long recordId = ParamUtils.getLongParameter(request, "recordId", -1);
		List<SystemCard> cardList = this.systemCardManage.getSystemCardListByRecordId(recordId);
		/*
                if(!Utils.isListEmpty(cardList)){
			createSheet(wwb, cardList);
		}*/
		createSheet(wwb, cardList);
		wwb.write();
		wwb.close();
		os.close();
		response.flushBuffer();
		
		return null;
	}
	
	//生成卡号,以及保存制卡记录
	public String doCreateCards(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		Integer num = ParamUtils.getIntParameter(request, "num", 0);
		//String code = ParamUtils.getParameter(request, "code");
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", 0L);
		Integer status = ParamUtils.getIntParameter(request, "status", 0);
		Integer sellStyle = ParamUtils.getIntParameter(request, "sellStyle", 1);
		Long cost = ParamUtils.getLongParameter(request, "cost", 0L);
		Date date = new Date();
		SystemCard c = new SystemCard();
		c.setId(0L);
		//c.setCardPassword(SystemCardUtil.get6Random());
		c.setCardType(cardTypeId);
		//c.setUsefulDate(ParamUtils.getDateParamenter(request, "usefulDate", "yyyy-MM-dd"));
		c.setIsnotBind(0);	//默认值
		c.setImportDate(null);
		
		String dateFt = "yyyy-MM-dd HH:mm:ss" ;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFt) ;
		String nowSdf = sdf.format(date) ;		
		try {
			c.setCreateDate(new SimpleDateFormat(dateFt).parse(nowSdf));
		} catch (Exception e) {}
				
		c.setBalance(0.0D);
		SystemUser user = LogicUtil.getSystemUser(request);
		c.setCreateUserId(user.getUserId());
		//c.setStatus(status);
        c.setStatus(1);//YHQ，2017-03-23，生效
		
		c.setSellStyle(sellStyle);	//售卡方式
		c.setIsselled(0);			//是否售出
		
		c.setCost(cost);
		
		SystemCardCreateRecord record = new SystemCardCreateRecord();
		
		//record.setCreateDate(date);
		try {
			record.setCreateDate(new SimpleDateFormat(dateFt).parse(nowSdf));
		} catch (Exception e) {}
		
		record.setDescription(ParamUtils.getParameter(request, "description"));
		record.setCardSum(num);
		
		this.systemCardManage.createSystemCard(num, c, record, sellStyle);

		response.sendRedirect("SystemCard.do?method=createCardList");

		return null;

		//return this.createCardList(mapping, form, request, response);
	}
	
	private void createSheet(WritableWorkbook wwb,List<SystemCard> list) throws WriteException, RowsExceededException {
		
		String name = "学习卡";
		
		WritableSheet wsheet = wwb.createSheet("system_card",wwb.getSheets().length);
		WritableFont wfc = new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD);
		
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);  
		wcfFC.setAlignment(Alignment.CENTRE);
		
		
		Label label = new Label(0, 0, "序号", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(1, 0, name + "ID", wcfFC);
		wsheet.addCell(label);
		
		label = new Label(2, 0, name + "卡号",wcfFC);
		wsheet.addCell(label);
		
		label = new Label(3, 0, name + "密码",wcfFC);
		wsheet.addCell(label);
		
		if(!Utils.isListEmpty(list)){
			for (int j = 0; j < list.size(); j++) {
				SystemCard val = list.get(j);
				wfc = new WritableFont(WritableFont.createFont("宋体"),10);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(0, j+1, String.valueOf(j+1),wcfFC);
				wsheet.addCell(label);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(1, j+1, String.valueOf(val.getId()),wcfFC);
				wsheet.addCell(label);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(2, j+1, val.getCardNumber(),wcfFC);
				wsheet.addCell(label);
				
				wcfFC = new WritableCellFormat(wfc);  
				wcfFC.setAlignment(Alignment.CENTRE);
				label = new Label(3, j+1, val.getCardPassword(),wcfFC);
				wsheet.addCell(label);
			}
		}
		
	}
	
	public SystemCardManage getSystemCardManage() {
		return systemCardManage;
	}

	public void setSystemCardManage(SystemCardManage systemCardManage) {
		this.systemCardManage = systemCardManage;
	}
}


