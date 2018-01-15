package com.hys.exam.common.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

public class Log {
    /**
     * 存储路径
     */
    private static String path = "/";
    /**
     * 文件大小
     */
    private static int fileSize;
    /**
     * 日志级别
     */
    private static int logLevel;

    private static FileOutputStream fos;
    private static PrintWriter pw;
    private static File dir;
    private static File file;
    private static DateFormat df;
    private final static String pattern = "yyyy-MM-dd HH:mm:ss";
    private final static String arrow = " > ";

    private static Log log;

    private Log() {
    }

    private static Log getInstance() {
        if (log == null) {
            initFile();
            log = new Log();
        }
        return log;
    }

    /**
     * 初始化日志文件
     */
    private static void initFile() {
        try {
            dir = new File(path);
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdirs();
            }
            //以日期时间作为日志文件名
            file = new File(dir, getCurrentDate("yyyyMMddHHmmss") + ".log");
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file, true); //续写
            pw = new PrintWriter(fos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 返回指定日期格式的字符串
     * @param pattern String
     * @return String
     */
    private static String getCurrentDate(String pattern) {
        df = new SimpleDateFormat(pattern);
        return df.format(Calendar.getInstance().getTime());
    }

    /**
     * 记录日志信息
     * @param message Object 日志信息
     * @param level int      日志级别 1 error, 2 warn, 3 info
     */
    private static void log(Object message, int level) {
        if (logLevel >= level) {
            //如果文件大小<指定大小，在原文件中续写
            if (file.length() <= fileSize) {
                System.out.println(message);
                pw.println(getCurrentDate(pattern) + arrow + message);
                pw.flush();
            } else {
                //否则重新生成一个日志文件
                try {
                    pw.flush();
                    pw.close();
                    fos.close();
                    getInstance().initFile();
                    getInstance().log(message, level);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 记录错误级别的日志
     * @param message Object
     */
    public static void error(Object message) {
        getInstance().log(message, 1);
    }

    /**
     * 记录警告级别的日志
     * @param message Object
     */
    public static void warn(Object message) {
        getInstance().log(message, 2);
    }

    /**
     * 记录信息级别的日志
     * @param message Object
     */
    public static void info(Object message) {
        getInstance().log(message, 3);
    }

    public int getLogLevel() {
        return logLevel;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getPath() {
        return path;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize * 1024 * 1024;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
