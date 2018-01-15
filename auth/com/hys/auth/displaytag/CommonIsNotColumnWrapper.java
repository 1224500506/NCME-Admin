package com.hys.auth.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

/**
 * 通用DisplayTag,进行是否显示,0：否，1：是
 * 
 * @author zdz
 * 
 */
public class CommonIsNotColumnWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object obj, PageContext pageContext,
			MediaTypeEnum arg2) throws DecoratorException {
		Integer value = (Integer) obj;
		value = (value == null ? 0 : value);
		return (value == 0 ? "否" : "是");
	}

}
