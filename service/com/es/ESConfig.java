package com.es;

import com.hys.exam.util.NcmeProperties;

public class ESConfig {	
	private static String HostAndPort = null ;
	
	//设置默认Bucket
    //public static final String DEFAULT_BUCKET = "zhangjianguo";
    public static String DEFAULT_BUCKET = null;
    
    //设置默认文件夹
    //public static final String DEFAULT_FOLDER = "ceshi/";
    public static String DEFAULT_FOLDER = null;
    
	static {
		HostAndPort = NcmeProperties.getContextPropertyValue("hostAndPort") ;
		DEFAULT_BUCKET = NcmeProperties.getContextPropertyValue("sourceBucketName");
		DEFAULT_FOLDER =NcmeProperties.getContextPropertyValue("folderName");
	}
	
	//获取Bucket集合
	public static final String BUCKET_LIST = "http://" + HostAndPort + "/list_buckets.json";
	
	//创建Bucket
	public static final String CREATE_BUCKET = "http://" + HostAndPort + "/create_bucket.json";
	
	//删除Bucket
	public static final String DELETE_BUCKET = "http://" + HostAndPort + "/delete_bucket.json";
	
	//获取指定Bucket下的Object集合
	public static final String GET_OBJECTS = "http://" + HostAndPort + "/list_objects.json";
	
	//上传Object
	public static final String UPDATE_FILE = "http://" + HostAndPort + "/upload_file_by_file.json";
	
	//创建文件夹
	public static final String CREATE_FOLDER = "http://" + HostAndPort + "/create_folder.json";
	
	//删除Object
	public static final String DELETE_OBJECT = "http://" + HostAndPort + "/delete_object.json";
	
	//设置Object权限
	public static final String SET_OBJECT_ACL = "http://" + HostAndPort + "/set_object_acl.json";
	
	//复制Object
	public static final String COPY_OBJECT = "http://" + HostAndPort + "/copy_object.json";
	
	//获取Object下载地址
	public static final String GET_DOWNLOAD_URL = "http://" + HostAndPort + "/get_download_url.json"; 
}
