package com.hys.exam.struts.action.liveservice.comm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * @author taoliang
 * @desc post
 */
public class CommDoPost {
	public static String sendRequestByPost(String url,
			Map<String, String> params) throws Exception{
		
		StringBuffer stringBuffer = new StringBuffer();
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		PostMethod method = new PostMethod(url){public String getRequestCharSet(){return "UTF-8";}};

		NameValuePair[] postData = null;
		if (params != null && params.size() > 0) {
			postData = new NameValuePair[params.size()];//
			Iterator<String> it = params.keySet().iterator();
			int index = 0;
			while (it.hasNext()) {
				String key = it.next();
				String value = params.get(key);
				postData[index++] = new NameValuePair(key, value);
			}
		}
		/////
		//postData[params.size()] = new NameValuePair("sign", generateSign(params));
		/////
		method.addParameters(postData);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		try {
			httpClient.executeMethod(method);

			BufferedReader rd = new BufferedReader(new InputStreamReader(method
					.getResponseBodyAsStream(), "UTF-8"));
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}

		return stringBuffer.toString();
	}
	

	
	/**
	 * 根据特定参数排序组装通过MD5加密生成验证码
	 * 
	 * @param siteId
	 * @return
	 * @throws ErrorResponseException
	 */
	/*public static String generateSign(Map<String, String> params) {
		StringBuffer sign = new StringBuffer();
		sign.append(params.get(Constant.KEY_USERNAME));
		sign.append(params.get(Constant.KEY_PASSWORD));
		sign.append(params.get(Constant.KEY_SITEID));
		sign.append(params.get(Constant.KEY_FUNCTIONID));
		sign.append(params.get(Constant.KEY_CONTENTJSON));
		sign.append(params.get(Constant.KEY_MESSAGEID));
		sign.append(params.get(Constant.KEY_MESSAGETIME));
		sign.append(params.get(Constant.KEY_SESSIONKEY));
		byte[] bytes = encryptHMAC(sign.toString(),Constant.MD5_KEY);
		return byte2hex(bytes);
	}*/
	
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	private static byte[] encryptHMAC(String data, String secret) {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret
					.getBytes("UTF-8"), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
}
