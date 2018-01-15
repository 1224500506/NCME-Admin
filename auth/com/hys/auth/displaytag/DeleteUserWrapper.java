package com.hys.auth.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class DeleteUserWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object propertyValue, PageContext pageContext, MediaTypeEnum arg2)
			throws DecoratorException {
		Long userId = (Long) propertyValue;
		String link = "<a href='javascript:deleteUser("+userId+");'>删除</a>";
		return link;
	}

}
