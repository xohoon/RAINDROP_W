package dev.drop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
	public static String MD5(String pw) throws Exception{
		String MD5 = "";
		
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(pw.getBytes());
	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        MD5 = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        MD5 = null;
	    }
	    return MD5;
	}
	
	public static String SHA256(String pw) throws Exception {
		StringBuffer hexString = new StringBuffer();
		 
	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(pw.getBytes("UTF-8"));
	 
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	            hexString.append(hex);
	        }
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	    return hexString.toString();
	}
}
