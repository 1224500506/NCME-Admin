package com.hys.auth.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public final class DozerMapperSingleton {

	private static Mapper instance = new DozerBeanMapper();

	public static Mapper getInstance() {
		return instance;
	}
}
