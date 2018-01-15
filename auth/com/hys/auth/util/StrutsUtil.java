package com.hys.auth.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class StrutsUtil {

	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	private static void render(final HttpServletResponse response,
			final String contentType, final String content) {
		try {
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;

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
}
