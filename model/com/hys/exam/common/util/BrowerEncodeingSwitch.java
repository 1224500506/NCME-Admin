package com.hys.exam.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Demo class
 *
 * @author wanghq
 * @date 2017/10/18
 */
public class BrowerEncodeingSwitch {
    public static final String USERAGENT_SAFARI="Safari";
    /**
     * 根据不同浏览器 User-Agent，生成不同的 Content_disposition
     * @param fileName
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getContentDisposition(String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        String contentDisposition = "";
        String userAgent = request.getHeader("User-Agent");
        //Safari
        if (userAgent.contains(USERAGENT_SAFARI)) {
            // name.getBytes("UTF-8")处理safari的乱码问题
            byte[] bytes = fileName.getBytes("UTF-8");
            // 各浏览器基本都支持ISO编码
            fileName = new String(bytes, "ISO-8859-1");
            // 文件名外的双引号处理firefox的空格截断问题
            contentDisposition = String.format("attachment; filename=\"%s\"", fileName);
        } else {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            contentDisposition = "attachment;filename=" + fileName;
        }
        return contentDisposition;
    }
}
