package com.hys.auth.displaytag;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class SimpleDateColumnWrapper implements DisplaytagColumnDecorator {

	public Object decorate(Object propertyValue, PageContext pageContext,
			MediaTypeEnum arg2) throws DecoratorException {
		Date updateDate = (Date) propertyValue;
		if (updateDate == null) {
			return "";
		}
		// else
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(updateDate);
	}

}
