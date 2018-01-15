package com.hys.auth.util;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private Map<String, Object> cache = new HashMap<String, Object>();

	private static Cache instance = new Cache();

	private Cache() {

	}

	public static Cache getInstance() {
		return instance;
	}

	public void put(String key, Object value) {
		cache.put(key, value);
	}

	public Object get(String key) {
		Object value = null;
		if (cache.containsKey(key)) {
			value = cache.get(key);
		}
		return value;
	}

	public void clear() {
		cache.clear();
	}

	public void remove(String key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
		}
	}
}
