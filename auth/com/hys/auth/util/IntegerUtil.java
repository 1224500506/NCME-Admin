package com.hys.auth.util;

/**
 * 
 * 标题：
 * 
 * 作者：zdz，2009 7 17
 * 
 * 描述：
 * 
 * 说明:
 */
public class IntegerUtil {
    public static int parseInteger(String string) {

        if (string == null) {
            return 0;
        }

        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ne) {
            return 0;
        }
    }
}
