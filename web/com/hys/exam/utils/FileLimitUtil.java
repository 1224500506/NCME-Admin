/**
 * FileLimitUtil.java
 * com.hys.exam.utils
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月16日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.exam.utils;

import org.apache.struts.upload.FormFile;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * ClassName:FileLimitUtil
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月16日	下午12:56:06
 *
 * @see 	 
 *  
 */

public class FileLimitUtil {
	
	private static final  String[] list = {"jpg","png","jpeg","bmp","gif","mp3","wma","wav","mp4","wmv","rmvb","mkv","flv","ppt","pptx","doc","docx","pdf","txt","rar","zip","7z"};
	
	private static final String[] list10M ={"jpg","png","jpeg","bmp","gif"};
	private static final String[] list20M ={"mp3","wma","wav"};
	private  static final String[] list2G ={"mp4","wmv","rmvb","mkv","flv"};
	private static final String[] list50M ={"ppt","pptx","doc","docx","pdf","txt","rar","zip","7z"};
	public static JSONObject validate(FormFile file){
		JSONObject jsonObject = new JSONObject();
		
		if(file == null || file.getFileName() == null || file.getFileName().trim().equals("")){
			
			jsonObject.put("code",1);
			jsonObject.put("message", "文件为空！");
			return jsonObject;
		}
			int length = file.getFileName().split("[.]").length;
			
			String type = file.getFileName().split("[.]")[length-1];
			boolean isLimitType = isLimitType(type);		
			
			if(isLimitType){
				
				jsonObject.put("code", 0);
				jsonObject.put("message", "文件类型受限！");
				return jsonObject;
			}
			
			long size = file.getFileSize();
		

			if(isLimit10M(type)){
				
				if(size>10*1024*1024){
					
					
					jsonObject.put("code", 10);
					jsonObject.put("message", "文件大小受限，文件应不大于10M！");
				
					return jsonObject;
				}
				
			}
			if(isLimit20M(type)){
				
				if(size>20*1024*1024){
				jsonObject.put("code", 20);
				jsonObject.put("message", "文件大小受限，文件应不大于20M！");
		
				return jsonObject;
				
				}
				
			}
			if(isLimit2G(type)){
				
				if(size>2*1024*1024*1024){

				jsonObject.put("code", 2000);
				jsonObject.put("message", "文件大小受限，文件应不大于2G！");
			
				return jsonObject;
			}
			}
			if(isLimit50M(type)){
				if(size>50*1024*1024){

				jsonObject.put("code", 50);
				jsonObject.put("message", "文件大小受限，文件应不大于50M！");
			
				return jsonObject;
				
				}
				
			}
			
			
			jsonObject.put("code", 1);
			jsonObject.put("message", "文件类型符合要求！");
			return jsonObject;
			
			
			
			
			
	}
	/**
	 * blong:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param type
	 * @param  @return    
	 * @return boolean    
	 * @throws 
	 * @since  　version 1.0
	*/
	private static boolean blong(String type,String[] s){
		boolean b = false;
		for(int i = 0;i<s.length;i++){
			
			if(type.equals(s[i])){
				
				b = true;
				break;
			}
			
		}
		return b;
		
	}
	/**
	 * isLimit50M:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param multipartFile    
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private static boolean isLimit50M(String type) {
		
		return blong(type,list50M);
		
	}

	/**
	 * isLimit2G:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param multipartFile    
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private static boolean isLimit2G(String type) {
		
		return blong(type,list2G);
		
	}

	/**
	 * isLimit10M:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param multipartFile    
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private static boolean isLimit10M(String type) {
		
		return blong(type,list10M);
	}
	
	


	/**
	 * isLimit20M:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param multipartFile    
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private static boolean isLimit20M(String type) {
		
		return blong(type,list20M);
		
	}

	/**
	 * limitType:(这里用一句话描述这个方法的作用)
	 * TODO(这里描述这个方法适用条件 – 可选)
	 *
	 * @param  @param multipartFile    
	 * @return void    
	 * @throws 
	 * @since  　version 1.0
	*/
	
	private static boolean isLimitType(String type) {
		
		boolean b = true;
		for(int i = 0;i<list.length;i++){
			
			if(type.toLowerCase().equals(list[i])){
				b = false;
				break;
			}
			
		}
		return b;
		
		
		
		
	}
	


}
