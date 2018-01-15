package com.es;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2016 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   ESZip.java
 *     
 *     Description       :   解压缩文件
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2016-11-22                                    张建国	           Created
 ********************************************************************************
 */

public class ESZip {
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return void
     * @param  File、String
     * 方法说明  解压文件
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile,String path)throws IOException{
    	long starTime=System.currentTimeMillis();
        ZipFile zip = new ZipFile(zipFile);
        //记录文件个数
        int  i = 0;
        for(Enumeration entries =zip.entries();entries.hasMoreElements();){
        	i++;
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            //outPath输出目录
            //判断实处目录是否存在，如果不存在则创建
            isExist(path);
            String outPath = (path+"\\"+zipEntryName).replaceAll("\\*", "/");
            //System.out.println("outPath "+outPath);
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if(!file.exists()){
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if(new File(outPath).isDirectory()){
                continue;
            }
            //输出文件路径信息
            //System.out.println(outPath);
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while((len=in.read(buf1))>0){
                out.write(buf1,0,len);
            }
            in.close();
            out.close();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("解压完毕,共运行："+(endTime - starTime)+" 毫秒;共解压："+i+" 个文件.");
    }
    
    /**
     * @author 张建国
     * @time   2016-11-22
     * @return void
     * @param  File、String
     * 方法说明  判断文件夹是否存在
     */
    public static void isExist(String path) {
        File file = new File(path);
	    if (!file.exists()) {
	       file.mkdir();
	    }
    }

    public static void main(String[] args) throws Exception{
        unZipFiles(new File("D:\\studio.zip"),"D:\\zjg");
       
    } 
}
