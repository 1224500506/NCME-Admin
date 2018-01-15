package com.hys.exam.utils;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2016 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 ******************************************************************************* 
 *     File Name         :   VCUtil.java
 *     
 *     Description       :   短信验证码发送功能类
 * -----------------------------------------------------------------------------
 *     No.        Date               Revised by              Description
 *     V1.0     2016-12-31                                 张建国	               Created
 ********************************************************************************
 */

public class VCUtil {

	/**
	 * @param    String
	 * @return   String
	 * @time     2016-12-31
	 * 方法说明： 发送短信验证码功能
	 */
	public static String sendSMS(String Mobile,String Content,String Cell,String SendTime) throws Exception{
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod("https://sdk2.028lk.com/sdk2/BatchSend2.aspx");
		NameValuePair[] postData = new NameValuePair[6];
		postData[0] = new NameValuePair("CorpID","bjjs000279");
		postData[1] = new NameValuePair("Pwd","zm0513@");
		postData[2] = new NameValuePair("Mobile",Mobile);
		postData[3] = new NameValuePair("Content",Content);
		postData[4] = new NameValuePair("Cell",Cell);
		postData[5] = new NameValuePair("SendTime",SendTime);
		postMethod.addParameters(postData); 
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
		int sendStatus = 0;
		int result = 0;
		try {
			//执行发送
			sendStatus = httpClient.executeMethod(postMethod);
			result = Integer.parseInt(postMethod.getResponseBodyAsString());
			//System.out.println("发送结果"+postMethod.getResponseBodyAsString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}finally{
			//释放资源
			postMethod.releaseConnection();
		}
		if(result>0 && result!=200 && sendStatus==200){
			return "success";
		}else{
			return "fail";
		}
	}

}
