package com.hys.auth.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.dozer.Mapper;

import com.hys.exam.constants.Constants;
import com.hys.auth.sessionfacade.AuthFacade;
import com.hys.auth.util.Cache;
import com.hys.auth.util.DozerMapperSingleton;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：BaseAction鸡肋
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class BaseActionSupport extends BaseAction {

	protected AuthFacade facade;

	protected Mapper dozer = DozerMapperSingleton.getInstance();

	protected Cache cache = Cache.getInstance();

	public void setFacade(AuthFacade facade) {
		this.facade = facade;
	}

	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!Constants.validateLicense) 
			throw new Exception("许可证无效,请重新导入许可证!");
		return null;
	}

}
