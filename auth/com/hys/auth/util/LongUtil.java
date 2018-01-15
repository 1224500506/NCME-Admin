package com.hys.auth.util;

/**
 * 
 * 标题：
 * 
 * 作者：zdz，2009 7 16
 * 
 * 描述：
 * 
 * 说明:
 */
public class LongUtil {
    public static long parseLong(String string) {

        if (string == null) {
            return 0;
        }

        try {
            return Long.valueOf(string);
        } catch (NumberFormatException ne) {

            return 0;
        }
    }
}
