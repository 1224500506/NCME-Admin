package com.hys.auth.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ParamUtils {

	/**
	 * Gets a parameter as a string.
	 * @param request The HttpServletRequest object, known as "request" in a
	 * JSP page.
	 * @param name The name of the parameter you want to get
	 * @param emptyStringsOK Return the parameter values even if it is an empty string.
	 * @return The value of the parameter or null if the parameter was not found.
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		String temp = request.getParameter(name);

		if (temp == null) {
			return "";
		}
		return temp;
	}

	public static String getParameter(HttpServletRequest request, String name,
			String defaultValue) {
		String temp = request.getParameter(name);
		if (temp == null || "".equals(temp)) {
			return defaultValue;
		}

		return temp;
	}

	/**
	 * Gets a parameter as a boolean.
	 * @param request The HttpServletRequest object, known as "request" in a
	 * JSP page.
	 * @param name The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String name) {
		return getBooleanParameter(request, name, false);
	}

	/**
	 * Gets a parameter as a boolean.
	 * @param request The HttpServletRequest object, known as "request" in a
	 * JSP page.
	 * @param name The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		} else if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return defaultVal;
		}
	}

	/**
	 * Gets a parameter as an int.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The int value of the parameter specified or the default value if
	 *      the parameter is not found.
	 */
	public static int getIntParameter(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a list of int parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into an int.
	 */
	public static int[] getIntParameters(HttpServletRequest request,
			String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * Gets a parameter as a double.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The double value of the parameter specified or the default value
	 *      if the parameter is not found.
	 */
	public static double getDoubleParameter(HttpServletRequest request,
			String name, double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a parameter as a long.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The long value of the parameter specified or the default value if
	 *      the parameter is not found.
	 */
	public static long getLongParameter(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a parameter as a long.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The long value of the parameter specified or the default value if
	 *      the parameter is not found.
	 */
	public static Short getShortParameter(HttpServletRequest request,
			String name, Short defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			Short num = defaultNum;
			try {
				num = Short.valueOf(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a list of long parameters.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 * can't be converted into a long.
	 */
	public static long[] getLongParameters(HttpServletRequest request,
			String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}
	
	public static String[] getStringParameters(HttpServletRequest request, String name) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new String[0];
		}
		String[] values = new String[paramValues.length];
		return values;
	}
	
	/**
	 * Gets a parameter as a string.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not
	 *      found or if the parameter is a zero-length string.
	 */
	public static String getAttribute(HttpServletRequest request, String name) {
		return getAttribute(request, name, false);
	}

	/**
	 * Gets a parameter as a string.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the parameter you want to get
	 * @param emptyStringsOK Return the parameter values even if it is an empty string.
	 * @return The value of the parameter or null if the parameter was not
	 *      found.
	 */
	public static String getAttribute(HttpServletRequest request, String name,
			boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(name);

		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return temp;
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets an attribute as a boolean.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the attribute you want to get
	 * @return True if the value of the attribute is "true", false otherwise.
	 */
	public static boolean getBooleanAttribute(HttpServletRequest request,
			String name) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets an attribute as a int.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the attribute you want to get
	 * @return The int value of the attribute or the default value if the
	 *      attribute is not found or is a zero length string.
	 */
	public static int getIntAttribute(HttpServletRequest request, String name,
			int defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets an attribute as a long.
	 * @param request The HttpServletRequest object, known as "request" in a
	 *      JSP page.
	 * @param name The name of the attribute you want to get
	 * @return The long value of the attribute or the default value if the
	 *      attribute is not found or is a zero length string.
	 */
	public static long getLongAttribute(HttpServletRequest request,
			String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static int getIntAttribute(HttpSession session, String name,
			int defaultNum) {
		String temp = (String) session.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a Attribute as a double.
	 * @param session The HttpSession object, known as "session" in a
	 *      JSP page.
	 * @param name The name of the Attribute you want to get
	 * @return The double value of the Attribute specified or the default value
	 *      if the Attribute is not found.
	 */
	public static double getDoubleAttribute(HttpSession session, String name,
			double defaultNum) {
		String temp = (String) session.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets a Attribute as a long.
	 * @param session The HttpSession object, known as "session" in a
	 *      JSP page.
	 * @param name The name of the Attribute you want to get
	 * @return The long value of the Attribute specified or the default value if
	 *      the Attribute is not found.
	 */
	public static long getLongAttribute(HttpSession session, String name,
			long defaultNum) {
		String temp = (String) session.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static String getAttribute(HttpSession session, String name) {
		String temp = (String) session.getAttribute(name);

		if (temp == null) {
			return "";
		}
		return temp;
	}

	public static String getAttribute(javax.servlet.http.HttpSession session,
			String name, String defaultValue) {
		String temp = (String) session.getAttribute(name);
		if (temp == null) {
			return defaultValue;
		}
		return temp;
	}

	public static String getUTF8Parameter(HttpServletRequest request,
			String name, String defaultValue) {
		String temp = request.getParameter(name);
		if (temp == null || "".equals(temp)) {
			return "";
		}
		String result = temp;
		try {
			byte[] b = temp.getBytes("ISO-8859-1");
			result = new String(b, "UTF-8");

		} catch (Exception e) {
		}
		return result;
	}
	
	public static Date getDateParamenter(HttpServletRequest request, 
			String name, String pattern){
		String temp = request.getParameter(name);
		if(temp == null || "".equals(temp)){
			return null;
		}

		if(pattern == null || "".equals(pattern)){
			pattern = "yyyy-MM-dd" ;
		}

		try{
			return new SimpleDateFormat(pattern).parse(temp) ;
		}catch(Exception e){
			return null;
		}
	}
	
	// boy 2016-12-06
	
	public static long[] getLongParametersFromString(HttpServletRequest request,
			String name, long defaultNum) {
		String param = request.getParameter(name);
		if (param == null) {
			return null;
		}
		
		String[] params = param.split(",");
		
		long[] values = new long[params.length];
		for (int i = 0; i < params.length; i++) {
			try {
				values[i] = Long.parseLong(params[i].trim());
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}
	
}