package com.hys.auth.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.upload.CommonsMultipartRequestHandler;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Servlet Filter implementation class FileFilter
 */
public class FileFilter implements Filter {

	private  volatile  String[] list = {"jpg","png","jpeg","bmp","gif","mp3","wma","wav","mp4","wmv","rmvb","mkv","flv","ppt","pptx","doc","docx","pdf","txt","rar","zip","7-zip"};
	
	private  final String[] list10M ={"jpg","png","jpeg","bmp","gif"};
	private  final String[] list20M ={"mp3","wma","wav"};
	private  final String[] list2G ={"mp4","wmv","rmvb","mkv","flv"};
	private  final String[] list50M ={"ppt","pptx","doc","docx","pdf","txt","rar","zip","7-zip"};
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("deprecation")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		httpServletResponse.setCharacterEncoding("UTF-8");  
		httpServletResponse.setContentType("application/json; charset=utf-8");  
		
		
		 FileUpload upload = new FileUpload();;
//		MultipartResolver multipartResolver = new CommonsMultipartResolver();
		if(FileUpload.isMultipartContent(httpServletRequest)){
			PrintWriter out = httpServletResponse.getWriter();
			JSONObject jsonObject = new JSONObject();
			List<FileItem> list = null;
			try {
				list = upload.parseRequest(httpServletRequest);
			} catch (FileUploadException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
//			httpServletRequest.ge
//			MultipartHttpServletRequest  multipartRequest=multipartResolver.resolveMultipart(httpServletRequest) ;
//			Map<String, MultipartFile> files= multipartRequest.getFileMap();
			
			Iterator<FileItem> iterator =list.iterator();
			
		
//			Iterator<Entry<String, MultipartFile>> iterator = files.entrySet().iterator();
			while(iterator.hasNext()){
				FileItem multipartFile = iterator.next();
				String fileName =  multipartFile.getName();
				int length = fileName.split("[.]").length;
				
				String type = fileName.split("[.]")[length-1];
				boolean isLimitType = this.isLimitType(type);		
				
				if(isLimitType){
					
					jsonObject.put("code", 300);
					jsonObject.put("message", "文件类型受限！");
					out.append(jsonObject.toString());
					out.flush();
					out.close();
					return ;
				}
				
				long size = multipartFile.getSize();
			

				if(this.isLimit10M(type)){
					
					if(size>10*1024*1024){
						
						
						jsonObject.put("code", 10);
						jsonObject.put("message", "文件大小受限，文件应不大于10M！");
						out.append(jsonObject.toString());
						out.flush();
						out.close();
						return ;
					}
					
				}
				if(this.isLimit20M(type)){
					
					if(size>20*1024*1024){
					jsonObject.put("code", 20);
					jsonObject.put("message", "文件大小受限，文件应不大于20M！");
					out.append(jsonObject.toString());
					out.flush();
					out.close();
					return ;
					
					}
					
				}
				if(this.isLimit2G(type)){
					
					if(size>2000*1024*1024){

					jsonObject.put("code", 2000);
					jsonObject.put("message", "文件大小受限，文件应不大于2G！");
					out.append(jsonObject.toString());
					out.flush();
					out.close();
					return ;
				}
				}
				if(this.isLimit50M(type)){
					if(size>20*1024*1024){

					jsonObject.put("code", 50);
					jsonObject.put("message", "文件大小受限，文件应不大于50M！");
					out.append(jsonObject.toString());
					out.flush();
					out.close();
					return ;
					
					}
					
				}
				
				
				
				
				
			}

		}
		chain.doFilter(request, response);

		
		
		
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
		boolean b = false;
		for(int i = 0;i<s.length-1;i++){
			
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
	
	private boolean isLimitType(String type) {
		
		boolean b = true;
		for(int i = 0;i<list.length-1;i++){
			
			if(type.toLowerCase().equals(list[i])){
				b = false;
				break;
			}
			
		}
		return b;
		
		
		
		
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
