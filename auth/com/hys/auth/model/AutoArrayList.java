package com.hys.auth.model;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class AutoArrayList extends ArrayList {

	private static final long serialVersionUID = -7155167951430376715L;

	private Class itemClass;

	public AutoArrayList(Class itemClass) {
		this.itemClass = itemClass;
	}

	public Object get(int index) {
		try {
			while (index >= size()) {
				add(itemClass.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.get(index);
	}
}