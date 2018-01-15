/**
 * FileInterceptor.java
 * com.hys.auth.interceptor
 * Function： TODO add descript
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2017年8月15日       lyj
 *
 * Copyright (c) 2017, TNT All Rights Reserved.
*/

package com.hys.auth.interceptor;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ClassName:FileInterceptor
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lyj
 * @version  
 * @since    version 1.0
 * @Date	 2017年8月15日	下午3:36:13
 *
 * @see 	 
 *  
 */

public class FileInterceptor extends HandlerInterceptorAdapter{

	private  volatile  String[] list = {"jpg","png","jpeg","bmp","gif","mp3","wma","wav","mp4","wmv","rmvb","mkv","flv","ppt","pptx","doc","docx","pdf","txt","rar","zip","7-zip"};
	
	private  final String[] list10M ={"jpg","png","jpeg","bmp","gif"};
	private  final String[] list20M ={"mp3","wma","wav"};
	private  final String[] list2G ={"mp4","wmv","rmvb","mkv","flv"};
	private  final String[] list50M ={"ppt","pptx","doc","docx","pdf","txt","rar","zip","7-zip"};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json; charset=utf-8");  
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		
		System.out.println("go  拦截器 lyj*****************************************************");
		
		MultipartResolver multipartResolver = new CommonsMultipartResolver();
		if(multipartResolver.isMultipart(request)){
			
			System.out.println("go  拦截器 lyj*****************************************************");
			MultipartHttpServletRequest  multipartRequest=(MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files= multipartRequest.getFileMap();
			
			Iterator<String> iterator = files.keySet().iterator();
			
			while(iterator.hasNext()){
				
				MultipartFile multipartFile = multipartRequest.getFile((String) iterator.next());
				multipartFile.getSize();
				int length = multipartFile.getName().split("[.]").length;
				
				String type = multipartFile.getName().split("[.]")[length-1];
				boolean isLimitType = this.isLimitType(type);		
				
				if(isLimitType){
					
					jsonObject.put("code", 300);
					jsonObject.put("message", "文件类型受限！");
					out.append(jsonObject.toString());
					return false;
				}
				
				long size = multipartFile.getSize();

				if(this.isLimit10M(type)){
					
					if(size>10*1024*1024){
						
						
						jsonObject.put("code", 10);
						jsonObject.put("message", "文件大小受限，文件应不大于10M！");
						out.append(jsonObject.toString());
						return false;
					}
					
				}
				if(this.isLimit20M(type)){
					jsonObject.put("code", 20);
					jsonObject.put("message", "文件大小受限，文件应不大于20M！");
					out.append(jsonObject.toString());
					return false;
					
				}
				if(this.isLimit2G(type)){
					jsonObject.put("code", 20000);
					jsonObject.put("message", "文件大小受限，文件应不大于2G！");
					out.append(jsonObject.toString());
					return false;
					
				}
				if(this.isLimit50M(type)){
					
					jsonObject.put("code", 50);
					jsonObject.put("message", "文件大小受限，文件应不大于50M！");
					out.append(jsonObject.toString());
					return false;
					
				}
				
				
				
				
				
			}

		}
		
		
		
		return true;
		
	}
	
	
	
	/**
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		
		System.out.println("after lyj*************************************************");
		super.afterCompletion(request, response, handler, ex);
		
	}



	/**
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post lyj*************************************************");

		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		
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
	private boolean blong(String type,String[] s){

		for(int i = 0;i<s.length-1;i++){
			
			if(type.equals(list[i])){
				
				return true;
			}
			
		}
		return false;
		
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
	
	private boolean isLimit50M(String type) {
		
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
	
	private boolean isLimit2G(String type) {
		
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
	
	private boolean isLimit10M(String type) {
		
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
	
	private boolean isLimit20M(String type) {
		
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
	
	private boolean isLimitType(Serializable type) {
		
		
		
		for(int i = 0;i<list.length-1;i++){
			
			if(!type.toString().toLowerCase().equals(list[i])){
				
				return true;
			}
			
		}
		return false;
		
		
		
		
	}
	
	

}
