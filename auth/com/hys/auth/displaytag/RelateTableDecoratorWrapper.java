package com.hys.auth.displaytag;

import org.displaytag.decorator.TableDecorator;

import com.hys.auth.model.HysResources;


public class RelateTableDecoratorWrapper extends TableDecorator {

	public String getRsOperation() {
		HysResources rs = (HysResources) getCurrentRowObject();
		Long id = rs.getId();
		StringBuilder sb = new StringBuilder();
		sb.append("<a href='javascript:showRelate(" + id + ");'>维护</a>").append(" | ");
		sb.append("<a href='javascript:doShowResource(" + id + ")'>修改</a>").append(" | ");
		sb.append("<a href='javascript:doDeleteResource(" + id + ")'>删除</a>");
		return sb.toString();
	}
}
