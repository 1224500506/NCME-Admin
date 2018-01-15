package com.es;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2016 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   ESUtil.java
 *     
 *     Description       :   操作ES对象存储操作
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2016-11-22                                    张建国	           Created
 ********************************************************************************
 */

public class ESUtil {

	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  获取指定用户下所有的Bucket信息
     */
	public static String getBucketList() throws Exception{
		 return ESConnUtil.ESConn(ESConfig.BUCKET_LIST, new HashMap<String,Object>());
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  创建Bucket
     */
	public static String createBucket(String bucketName) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		return ESConnUtil.ESConn(ESConfig.CREATE_BUCKET, map);
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  删除Bucket
     */
	public static String deleteBucket(String bucketName) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		return ESConnUtil.ESConn(ESConfig.DELETE_BUCKET, map);
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  创建文件夹
     */
	public static String createFolder(String bucketName,String folder) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		//文件夹名称
		map.put("folderName", folder);
		return ESConnUtil.ESConn(ESConfig.CREATE_FOLDER, map);
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  获取指定Bucket下所有的Object信息
     */
	public static String getObjectList(String bucketName,String sourceKey) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		//Object名称(可选)
		map.put("sourceKey", sourceKey);
		return ESConnUtil.ESConn(ESConfig.GET_OBJECTS, map);
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  上传Object
     */
	public static String updateFile(File file,String filePath,String bucketName) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
        map.put("filePath",filePath);
	    map.put("sourceBucketName",bucketName);
        //File file = new File("D://zjg.txt");
	    System.out.println("updateFile-----toPrivateCloud----" + ESConfig.UPDATE_FILE);
        String res = HttpClientUtil.getInstance().sendHttpPost1(ESConfig.UPDATE_FILE,map,file);
        return res;
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  删除Object(非文件夹)
     */
	public static String deleteObject(String bucketName,String sourceKey) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		///Object名称(必选)
		map.put("sourceKey", sourceKey);
		return ESConnUtil.ESConn(ESConfig.DELETE_OBJECT, map);
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  设置Object权限
     */
	public static String setObjectAcl(String bucketName,String sourceKey,String accessControl) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		///Object名称(必选)
		map.put("sourceKey", sourceKey);
		//Object权限(必选)
		map.put("accessControl", accessControl);
		return ESConnUtil.ESConn(ESConfig.SET_OBJECT_ACL, map);
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  复制Object
     */
	public static String copyObject(String bucketName,String sourceKey,String purpoBucketName,String purposeKey) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		//Object名称(必选)
		map.put("sourceKey", sourceKey);
		//目标Bucket名称(移动时必选)
		map.put("purpoBucketName", purpoBucketName);
		//目标Object名称(重命名时必选)
		map.put("purposeKey", purposeKey);
		return ESConnUtil.ESConn(ESConfig.COPY_OBJECT, map);
	}

	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * 方法说明  获取Object下载Url
     */
	public static String getDownloadUrl(String bucketName,String sourceKey) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//Bucket名称(必选)
		map.put("sourceBucketName", bucketName);
		///Object名称(必选)
		map.put("sourceKey", sourceKey);
		return ESConnUtil.ESConn(ESConfig.GET_DOWNLOAD_URL, map);
	}

}
