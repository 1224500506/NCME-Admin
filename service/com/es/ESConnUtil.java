package com.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import net.sf.json.JSONObject;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2016 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   ESConnUtil.java
 *     
 *     Description       :   与ES对象存储REST连接处理类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2016-11-22                                    张建国	           Created
 ********************************************************************************
 */

public class ESConnUtil {
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * @param  String、Map
     * 方法说明  模拟Http连接方法
     */
	public static String ESConn(String url,Map<String,Object> map)throws Exception{
	  HttpClient httpClient = new HttpClient();  
      HttpMethod method = getMethod(url, createQueryString(map));  
      httpClient.executeMethod(method);  
      String response = new String(method.getResponseBodyAsString().getBytes("utf-8"));
      return response;
	}

	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * @param  Map
     * 方法说明  对请求参数做处理
     */
	public static String createQueryString(Map<String,Object> map){
		StringBuffer result = new StringBuffer();
		Collection<String> keyset= map.keySet();  
	    List<String> list = new ArrayList<String>(keyset);  
	    for (int i = 0; i < list.size(); i++) {  
	    	 if(i==list.size()-1){
	    		 result.append(list.get(i)+"="+map.get(list.get(i)));  
	    	 }else{
	    		 result.append(list.get(i)+"="+map.get(list.get(i))+"&");  
	    	 }
	    }
		return result.toString();
	}
	
	/**
     * @author 张建国
     * @time   2016-11-22
     * @return String
     * @param  String
     * 方法说明  拼接Http地址及参数
     */
	public static HttpMethod getMethod(String url,String param) throws IOException{  
		GetMethod get = null;
		if(null!=param && !"".equals(param)){
			get = new GetMethod(url+"?"+param);
		}else{
			get = new GetMethod(url);
		}
        get.releaseConnection();  
        return get;  
    }  
	
	/**
     * @author 张建国
     * @time   2016-11-23
     * @return String
     * @param  String
     * 方法说明  判断连接是否成功并返回信息
     */
	public static String analysiJSON(String result) throws Exception{
		//将Http返回结果进行解析
		JSONObject obj = JSONObject.fromObject(result);
		//判断请求是否成功
		String code  = obj.getString("code");
		String message = obj.getString("message");
		if(code!=null && "200".equals(code) && (message!=null && "successful".equals(message))){
			//请求成功
			return obj.getString("data");
		}else{
			if(Integer.parseInt(code)==201){
				System.out.println("参数错误");
			}else if(Integer.parseInt(code)==-100){
				System.out.println("服务异常");
			}else if(Integer.parseInt(code)==-101){
				System.out.println("违规参数");
			}else if(Integer.parseInt("code")==401){
				System.out.println("认证错误");
			}else if(Integer.parseInt("code")==404){
				System.out.println("资源无法访问");
			}
			return "fail";
		}
	}
	
}
