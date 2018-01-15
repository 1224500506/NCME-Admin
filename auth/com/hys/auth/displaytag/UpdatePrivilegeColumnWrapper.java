package com.hys.auth.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class UpdatePrivilegeColumnWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object propertyValue, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		Long userId = (Long) propertyValue;
		String link = "<a href=\"javascript:showEditRole('"+userId+"')\">修改权限</html:link>";
		return link;
	}

}
