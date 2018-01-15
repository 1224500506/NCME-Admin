/**
 *
 */
package com.hys.auth.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;


/**
 * 
 * 1.产生公钥和私钥对，并且保存在文件中，公钥 pk.dat，私钥  sk.dat
 * 2.私钥加密mac串生成license文件输出到指定文件夹dirPath下
 * 3.复制license.txt和pk.dat至src目录下
 * 4.重新部署项目重启即可
 * @author 郭津
 * 
 */
public class LicenseManage {
	//加密种子
	private static String keyInfo = "!@#$^&12";
	//注册文件
	private static String licenseName = "license.txt";
	//公钥
	private static String pk_dat = "pk.dat";
	//私钥
	private static String sk_dat = "sk.dat";
	
	private static String dirPath = "D:/license/";
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//生成license
//		
//		String grantMacAddr = LocalSystemTool.getMacAddress();//当前主机MAC
//		produceLicense(dirPath,grantMacAddr);
		 
		validateLicense();//把验证
	}
	
	/**
	 * 生成license.txt 及 pk.dat
	 * @param dirPath 文件输出目录 / grantMacAddr  授权MAC串
	 * 
	 */
	public static void produceLicense(String dirPath,String grantMacAddr) throws Exception {
		if (dirPath==null || dirPath.trim().equals("") ) 
			dirPath= "c:/";//默认为C盘根目录
		
		String str = grantMacAddr;
		System.out.println("MAC：" + str);

		//加密 
		LicenseManage rsa = new LicenseManage();
		rsa.genKeys(keyInfo,dirPath);//生成pk.dat
		//加密消息
		RSAPrivateKey privateKey = (RSAPrivateKey) rsa.readFromFile(dirPath+sk_dat);
		byte[] encbyte = rsa.encrypt(str, privateKey);
		String encStr = toHexString(encbyte);
		//数字签名
		byte[] signBytes = rsa.sign(str, privateKey);
		String signature = toHexString(signBytes);
		
		FilesUtil.writeFile("message="+encStr+"\nsignature="+signature, dirPath+licenseName, "iso8859-1");//生成license及签名
		
//		String message = FilesUtil.readPropertiesData(dirPath+licenseName, "message");
//		System.out.println("message="+message);
//		String signature1 = FilesUtil.readPropertiesData(dirPath+licenseName, "signature");
//		System.out.println("signature="+signature1);
		
	}
	
	public static void total() throws Exception {
		String str = "00-21-6B-42-6E-F8";
		System.out.println("原文：" + str);

		LicenseManage rsa = new LicenseManage();
		RSAPrivateKey privateKey = (RSAPrivateKey) rsa.readFromFile(sk_dat);
		RSAPublicKey publickKey = (RSAPublicKey) rsa.readFromFile(pk_dat);

		byte[] encbyte = rsa.encrypt(str, privateKey);
		System.out.println("私钥加密后：");
		String encStr = toHexString(encbyte);
		System.out.println(encStr);

		byte[] signBytes = rsa.sign(str, privateKey);
		System.out.println("签名值：");
		String signStr = toHexString(signBytes);
		System.out.println(signStr);

		byte[] decByte = rsa.decrypt(encStr, publickKey);
		System.out.println("公钥解密后：");
		String decStr = new String(decByte);
		System.out.println(decStr);

		if (rsa.verifySign(str, signStr, publickKey)) {
		System.out.println("rsa sign check success");
		} else {
		System.out.println("rsa sign check failure");
		}
	}

	/**
	 * 根据keyInfo产生公钥和私钥，并且保存到以C:/pk.dat和D:/sk.dat文件中
	 * 
	 * @param keyInfo
	 * @throws Exception
	 */
	public void genKeys(String keyInfo,String dirPath) throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom();
		random.setSeed(keyInfo.getBytes());
		// 初始加密，长度为512，必须是大于512才可以的
		keygen.initialize(512, random);
		// 取得密钥对
		KeyPair kp = keygen.generateKeyPair();
		// 取得公钥
		PublicKey publicKey = kp.getPublic();
		saveFile(publicKey, dirPath,pk_dat);
		// 取得私钥
		PrivateKey privateKey = kp.getPrivate();
		saveFile(privateKey, dirPath,sk_dat);

	}
	
	
	/**
	 * 
	 * license校验
	 * @return
	 */
	public static boolean validateLicense() {
		//系统中取出mac
		String mac = LocalSystemTool.getMacAddress();

		String rootPath = LicenseManage.class.getResource("/").getPath();
		String message = FilesUtil.readPropertiesData(rootPath+licenseName, "message");
		String signStr = FilesUtil.readPropertiesData(rootPath+licenseName, "signature");
		
		//校验签名
		String datPath = rootPath+pk_dat;
		LicenseManage rsa = new LicenseManage();
		try {
			PublicKey key = (PublicKey)rsa.readFromFile(datPath);
			message = new String(rsa.decrypt(message, key));
			if(!rsa.verifySign(message, signStr, key)){
				System.out.println("is false");
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		//判断MAC地址
		if (!mac.equals(message)){
			return false;
		}
		
		//判断IP地址
		if (!mac.equals(message)){
			return false;
		}
		
		return true;
	}

	/**
	 * 保存对象到文件
	 * 
	 * @param obj
	 * @param fileName
	 * @throws Exception
	 */
	private void saveFile(Object obj,String dirPath ,String fileName) throws Exception {
	        // 源文件
			FilesUtil.createDir(dirPath, true);
	        File desc = new File(dirPath+fileName);
	        
	        // 写入器
	        FileOutputStream fos = null;
	        ObjectOutputStream oos = null;
	         
	        try {
	            fos = new FileOutputStream(desc);
	            oos = new ObjectOutputStream(fos);
	             
	            // 将自身序列化
	            oos.writeObject(obj);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                oos.close();
	                fos.close();
	            } catch (Exception e) {
	            }
	        }
	    }
	/**
	 * 从文件读取object
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public Object readFromFile(String filePath) throws Exception {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream( new File(filePath)));
		Object obj = input.readObject();
		input.close();
		return obj;
	}

	

	/**
	 * 加密,key可以是公钥，也可以是私钥
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(message.getBytes());
	}

	/**
	 * 解密，key可以是公钥，也可以是私钥，如果是公钥加密就用私钥解密，反之亦然
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(toBytes(message));
	}

	/**
	 * 用私钥签名
	 * 
	 * @param message
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] sign(String message, PrivateKey key) throws Exception {
		Signature signetcheck = Signature.getInstance("MD5withRSA");
		signetcheck.initSign(key);
		signetcheck.update(message.getBytes("ISO-8859-1"));
		return signetcheck.sign();
	}

	/**
	 * 用公钥验证签名的正确性
	 * 
	 * @param message
	 * @param signStr
	 * @return
	 * @throws Exception
	 */
	public boolean verifySign(String message, String signStr, PublicKey key)
			throws Exception {
		if (message == null || signStr == null || key == null) {
			return false;
		}
		Signature signetcheck = Signature.getInstance("MD5withRSA");
		signetcheck.initVerify(key);
		signetcheck.update(message.getBytes("ISO-8859-1"));
		return signetcheck.verify(toBytes(signStr));
	}


	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
			sb.append(HEXCHAR[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static final byte[] toBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}