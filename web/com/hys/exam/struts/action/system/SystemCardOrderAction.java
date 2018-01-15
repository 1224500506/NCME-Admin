package com.hys.exam.struts.action.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.tags.TableTagParameters;

import com.hys.auth.util.ParamUtils;
import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemCardOrder;
import com.hys.exam.model.system.SystemCardType;
import com.hys.exam.model.systemQuery.SystemCardOrderQuery;
import com.hys.exam.model.systemQuery.SystemCardQuery;
import com.hys.exam.service.remote.SystemCardManage;
import com.hys.exam.struts.action.AppBaseAction;
import com.hys.exam.constants.Constants;

/**
*
* <p>Description: 学习卡订单</p>
* @author chenlaibin
* @version 1.0 2013-12-17
*/
public class SystemCardOrderAction extends AppBaseAction{

	private SystemCardManage systemCardManage;
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if(method.equals("list")){
			return this.list(mapping, form, request, response);
		}else if(method.equals("edit")){
			return this.edit(mapping, form, request, response);
		}else if(method.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(method.equals("delBatch")){
			return this.delete(mapping, form, request, response);
		}else if(method.equals("getCardList")){
			return this.getCardList(mapping, form, request, response);
		}else if(method.equals("createCard")){
			return this.createCard(mapping, form, request, response);
		}
		return null;
	}
	
	//查看学习卡订单列表
	@SuppressWarnings("static-access")
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SystemCardOrderQuery query = new SystemCardOrderQuery();
		query.setAccountName(ParamUtils.getParameter(request, "accountName"));
		query.setUserName(ParamUtils.getParameter(request, "realName"));
		query.setOrderDateStart(ParamUtils.getParameter(request, "orderDateStart"));
		query.setOrderDateEnd(ParamUtils.getParameter(request, "orderDateEnd"));
		query.setOrderType(ParamUtils.getIntParameter(request, "orderType", -100));
		//query.setOrderType(ParamUtils.getIntParameter(request, "status", -100));
		query.setPaymodeCode(ParamUtils.getParameter(request, "paymodeCode"));
		query.setInvoiceStatus(ParamUtils.getIntParameter(request, "invoiceStatus", -100));
		query.setOrderNumber(ParamUtils.getParameter(request, "orderNumber", ""));
		query.setCardTypeName(ParamUtils.getParameter(request, "cardTypeName"));
		query.setCardTypeId(ParamUtils.getLongParameter(request, "cardTypeId", -1L));
		query.setStatus(ParamUtils.getIntParameter(request, "status", -100));
		
		
		Page<SystemCardOrderQuery> page = this.createPage(request, "systemCardOrder");
		//export xls 用户是否点击了导出超链接，通过以下代码可以获取该值;如有导出时，导出全部查询记录 
		String exportValue = ParamUtils.getParameter(request,TableTagParameters.PARAMETER_EXPORTING);
		if (StringUtils.isNotBlank(exportValue)) {
			page.setCurrentPage(1);
			page.setPageSize(Constants.EXPORT_MAX_NUM);
		}
		
		this.systemCardManage.getSystemCardOrderList(page, query);
		
		List<SystemCardType> cardTypeList = systemCardManage.getSystemCardTypeList();
		
		request.setAttribute("page", page);
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
		request.setAttribute("serchInfo", query);
		request.setAttribute("cardTypeList",cardTypeList);
		return "list";
	}
	
	//订单详细
	@SuppressWarnings("static-access")
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long id = ParamUtils.getLongParameter(request, "id", 0);
		SystemCardOrderQuery order = this.systemCardManage.getSystemCardOrderById(id);
		request.setAttribute("order", order);
		
		//得到该订单下生成的的学习卡
		Page<SystemCardQuery> page = this.createPage(request, "systemCard");
		List<SystemCardQuery> cardList = this.systemCardManage.getSystemCardListByOrderId(id, page);
		request.setAttribute("cardList", cardList);
		
		//得到该卡类型下有空白卡的数量
		if(order != null){
			List<SystemCard> list = this.systemCardManage.getSystemCardNoBindListByCardType(order.getCardTypeId(), 0);
			request.setAttribute("noBindCount", list.size());
		}
		return "edit";
	}
	//订单更新
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		SystemCardOrder order = new SystemCardOrder();
		order.setId(ParamUtils.getLongParameter(request, "id", 0));
		order.setCardTypeId(ParamUtils.getLongParameter(request, "cardTypeId", 0));
		order.setUserId(ParamUtils.getLongParameter(request, "userId", 0));
		order.setOrderDate(ParamUtils.getDateParamenter(request, "orderDate", "yyyy-MM-dd"));
		order.setQuantity(ParamUtils.getLongParameter(request, "quantity", 0L));
		order.setPrice(ParamUtils.getDoubleParameter(request, "price", 0));
		order.setAmount(ParamUtils.getDoubleParameter(request, "amount", 0));
		order.setPayDate(ParamUtils.getDateParamenter(request, "payDate", "yyyy-MM-dd"));
		order.setPaymodeCode(ParamUtils.getParameter(request, "paymodeCode"));
		order.setInvoiceStatus(ParamUtils.getIntParameter(request, "invoiceStatus", 0));
		order.setInvoiceTitle(ParamUtils.getParameter(request, "invoiceTitle"));
		order.setRecipient(ParamUtils.getParameter(request, "recipient"));
		order.setTel(ParamUtils.getParameter(request, "tel"));
		order.setAddress(ParamUtils.getParameter(request, "address"));
		order.setOrderOper(ParamUtils.getParameter(request, "orderOper"));
		order.setOperDate(ParamUtils.getDateParamenter(request, "operDate", "yyyy-MM-dd"));
		order.setRemark(ParamUtils.getParameter(request, "remark"));
		order.setPostCode(ParamUtils.getParameter(request, "postCode"));
		order.setOrderNumber(ParamUtils.getParameter(request, "orderNumber"));
		order.setStatus(ParamUtils.getIntParameter(request, "status", -100));
		int row = 0;
		row = this.systemCardManage.updateSystemCardOrder(order);
		request.setAttribute("row", row);
		response.sendRedirect("OrderManage.do?method=list");
		return null;
	}
	
	//订单删除
	protected String delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String _orderIds = ParamUtils.getParameter(request, "orderIds");
		_orderIds = _orderIds.substring(0, _orderIds.length()-1);
		String [] ids = _orderIds.split(",");
		Long [] orderIds = this.switchStringtoLongArray(ids);
		this.systemCardManage.deleteSystemCardOrder(orderIds);
		return this.list(mapping, form, request, response);
	}
	
	//学习卡列表
	protected String getCardList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long orderId = ParamUtils.getLongParameter(request, "orderId", 0);
		@SuppressWarnings("static-access")
		Page<SystemCardQuery> page = this.createPage(request, "systemOrderCard");
		this.systemCardManage.getSystemCardListByOrderId(orderId, page);
		request.setAttribute("page", page);
		return "orderCardList";
	}
	
	//根据订单绑定学习卡
	protected String createCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Long orderId = ParamUtils.getLongParameter(request, "orderId", -1);
		Long cardTypeId = ParamUtils.getLongParameter(request, "cardTypeId", -1);
		Integer quantity = ParamUtils.getIntParameter(request, "quantity", -1);
		int row = this.systemCardManage.updateSystemCardByCardTypeAndOrder(cardTypeId, orderId, quantity);
		Utils.renderText(response, String.valueOf(row));
		return null;
	}

	public SystemCardManage getSystemCardManage() {
		return systemCardManage;
	}

	public void setSystemCardManage(SystemCardManage systemCardManage) {
		this.systemCardManage = systemCardManage;
	}
}


