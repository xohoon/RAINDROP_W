package dev.drop.utils;

import java.util.Random;

public class RandomNum {
	private static final char[] chars;
	
	static {
		StringBuffer buffer = new StringBuffer();
		
		for(char ch = '0'; ch <= '9'; ++ch)
			buffer.append(ch);
		for(char ch = 'A'; ch <= 'Z'; ++ch)
			buffer.append(ch);
		chars = buffer.toString().toCharArray();
	}
	
	public static String random(int length) {
		
//		if(length < 1)
//			throw new IllegalAccessException("length < 1 : " + length);
		
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();
		
		for(int i = 0; i < length; i++) {
			randomString.append(chars[random.nextInt(chars.length)]);
		}
		return randomString.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(random(10));
	}
}
