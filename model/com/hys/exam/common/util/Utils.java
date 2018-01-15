package com.hys.exam.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.hys.exam.constants.Constants;

/**
 * 
 * 标题：住院医师
 * 
 * 作者：张伟清 2011-1-5
 * 
 * 描述：常用工具类
 * 
 * 说明:
 */
public class Utils {

	// 判断List结果集是否为空!
	public static <T> boolean isListEmpty(List<T> list) {
		if (null == list || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	// 判断Map结果集是否为空!
	public static <K, V> boolean isMapEmpty(Map<K, V> map) {
		if (null == map || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	//判断数组是否为空!
	public static <T> boolean isArrayEmpty(T[] array){
		if (null == array || array.length == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// 判断当前值是否为空
	public static String isStringEmpty(String tempVal, String defaultVal) {
		if (null == tempVal || "".equals(tempVal)) {
			return defaultVal;
		} else {
			return tempVal;
		}
	}
	
	private static void render(final HttpServletResponse response,
			final String contentType, final String content) {
		try {
			String encoding = Constants.ENCODING_DEFAULT;
			boolean noCache = Constants.NOCACHE_DEFAULT;

			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException("");
		}
	}

	public static void renderText(final HttpServletResponse response,
			final String text) {
		render(response, "text/plain", text);
	}

	public static void renderXml(final HttpServletResponse response,
			final String text) {
		render(response, "text/xml", text);
	}
	
	//arrayList 去重
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }
}