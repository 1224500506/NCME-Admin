package com.hys.auth.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class UpdateUserWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object propertyValue, PageContext pageContext, MediaTypeEnum arg2)
			throws DecoratorException {
		Long doctorId = (Long) propertyValue;
		String path = pageContext.getServletContext().getContextPath();
		String link = "<a  href='" + path + "/userManage/preUpdateUser.do?id=" + doctorId
				+ "'>更新</a>";
		return link;
	}

}
