package com.hys.exam.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.hys.auth.util.ParamUtils;


/**
 * 
 * 标题：住院医师
 * 
 * 作者：陈明凯 2011-1-5
 * 
 * 描述：分页
 * 
 * 说明:
 */
public class PageUtil {

	/**
	 * 分页-sql
	 * 
	 * @param sql
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	// public static String getPageSql(String sql, int pageSize, int
	// currentPage) {
	// StringBuffer pagingSelect = new StringBuffer(100);
	// pagingSelect
	// .append("select * from(select row_.*, rownum rownum_ from(");
	// pagingSelect.append(sql);
	// pagingSelect.append(") row_ where rownum<=");
	// pagingSelect.append(pageSize * (currentPage + 1));
	// pagingSelect.append(") where rownum_ >");
	// pagingSelect.append(pageSize * currentPage);
	//
	// return pagingSelect.toString();
	// }
	public static String getPageSql(String sql, int pageSize, int currentPage) {
/*		StringBuffer sb = new StringBuffer();
		sb.append("select * from(select row_.*, rownum rownum_ from(");
		sb.append(sql);
		sb.append(") row_ where rownum<=");
		sb.append(pageSize * currentPage);
		sb.append(") where rownum_>");
		sb.append(pageSize * (currentPage - 1));
		return sb.toString();
*/		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		sb.append(" limit ");
		sb.append(pageSize * (currentPage-1));
		sb.append(", ");
		sb.append(pageSize);
		return sb.toString();
	}

	/**
	 * 取page index
	 * 
	 * @param request
	 * @return
	 * @throws FrameworkException
	 */
	public static Integer getPageIndex(HttpServletRequest request)
			throws Exception {
		try {
			return GenericValidator.isBlankOrNull(request.getParameter("page")) ? 1
					: (Integer.parseInt(request.getParameter("page")));
		} catch (NumberFormatException ne) {
			return 1;
		}
	}
	
	/**
	 * 
	 * Description: 从页面取得分页信息_easyUI Datagrid 
	 * @param request
	 * @param page
	 * @return
	 */
	public static <T> Page<T> getPageByRequest(HttpServletRequest request) {
		Page<T> page = new Page<T>();
		int currentPage = ParamUtils.getIntParameter(request, "page", 1);
		page.setCurrentPage(currentPage);

		int pageSize = ParamUtils.getIntParameter(request, "pagesize", 20);
		page.setPageSize(pageSize);

		return page;
	}
	
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

		if (StringUtils.isNumeric(pageIndexValue)) {
			pageIndex = Integer.valueOf(request.getParameter(pageIndexName));
		} else if(currentPageNum != null) {
			pageIndex = currentPageNum;
		}

		String pageSizeStr = request.getParameter("pageSize");
		Integer pageSize;
		if (NumberUtils.isDigits(pageSizeStr) && Integer.valueOf(pageSizeStr) > 0) {
			pageSize = Integer.valueOf(pageSizeStr);
		} else {
			pageSize = 5;
		}
		request.setAttribute("pageSize", pageSize);
		Page<T> page = new Page<T>();
		page.setCurrentPage(pageIndex);
		page.setPageSize(pageSize);
		return page;
	}
}
