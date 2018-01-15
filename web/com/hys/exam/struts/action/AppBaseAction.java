/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2013-12-15
 */

package com.hys.exam.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.hys.exam.common.util.Page;
import com.hys.framework.web.action.BaseAction;

public class AppBaseAction extends BaseAction{
	
	private static final int PAGE_SIZE = 20;

	@Override
	protected String actionExecute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		
		return null;
	}
	
	//分页
	public static <T> Page<T> createPage(HttpServletRequest request,
			String tableId) {
		String pageIndexName = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE);

		String pageIndexValue = request.getParameter(pageIndexName);
		Integer currentPageNum = (Integer)request.getSession().getAttribute(pageIndexName);
		String  onePage = request.getParameter("onePage");//
		Integer pageIndex = 1;

		if(null!=onePage&&"1".equals(onePage)){//
			currentPageNum=null;
			request.getSession().removeAttribute(pageIndexName);
		}
		request.getSession().removeAttribute(pageIndexName);
		if (StringUtils.isNumeric(pageIndexValue)) {
			pageIndex = Integer.valueOf(request.getParameter(pageIndexName));
		}
		request.getSession().setAttribute(pageIndexName, pageIndex);
		//查询时，默认从第一页开始
		/* else if(currentPageNum != null) {
			pageIndex = currentPageNum;
		}*/

		String pageSizeStr = request.getParameter("pageSize");
		Integer pageSize;
		if (NumberUtils.isDigits(pageSizeStr) && Integer.valueOf(pageSizeStr) > 0) {
			pageSize = Integer.valueOf(pageSizeStr);
		} else {
			pageSize = PAGE_SIZE;
		}
		request.setAttribute("pageSize", pageSize);
		Page<T> page = new Page<T>();
		page.setCurrentPage(pageIndex);
		page.setPageSize(pageSize);
		return page;
	}
	
	
	
	
	//分页
	public static <T> Page<T> createPage_temp_2017(HttpServletRequest request,
			String tableId) {
		String pageIndexName = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE);

		String pageIndexValue = request.getParameter(pageIndexName);
		Integer currentPageNum = (Integer)request.getSession().getAttribute(pageIndexName);
		String  onePage = request.getParameter("onePage");//
		Integer pageIndex = 1;

		if(null!=onePage&&"1".equals(onePage)){//
			currentPageNum=null;
			request.getSession().removeAttribute(pageIndexName);
		}
		request.getSession().removeAttribute(pageIndexName);
		if (StringUtils.isNumeric(pageIndexValue)) {
			pageIndex = Integer.valueOf(request.getParameter(pageIndexName));
		}else{
			
			//添加 跳转页码获取方法
			
			
			if(StringUtils.isNotBlank(pageIndexValue)){
				
				pageIndex = Integer.valueOf(pageIndexValue.split("page=")[1]);
				
			}
			
		}
		
		
		
		request.getSession().setAttribute(pageIndexName, pageIndex);
		//查询时，默认从第一页开始
		/* else if(currentPageNum != null) {
			pageIndex = currentPageNum;
		}*/

		String pageSizeStr = request.getParameter("pageSize");
		Integer pageSize;
		if (NumberUtils.isDigits(pageSizeStr) && Integer.valueOf(pageSizeStr) > 0) {
			pageSize = Integer.valueOf(pageSizeStr);
		} else {
			pageSize = PAGE_SIZE;
		}
		request.setAttribute("pageSize", pageSize);
		Page<T> page = new Page<T>();
		page.setCurrentPage(pageIndex);
		page.setPageSize(pageSize);
		return page;
	}

	//String数组转化为Long数组
	public Long [] switchStringtoLongArray(String [] strArr){
		if(null == strArr || strArr.length==0)
			return new Long[0];
		Long [] longArr = new Long[strArr.length];
		for(int i=0; i< strArr.length; i++){
			if(StringUtils.isNotBlank(strArr[i])){
				longArr[i]=Long.parseLong(strArr[i]);
			}
		}
		return longArr;		
	}
	
	//String数组参数转化为Long数组
	public static Long[] getLongParameters(HttpServletRequest request,
			String name, Long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new Long[0];
		}
		Long[] values = new Long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}
	
}


