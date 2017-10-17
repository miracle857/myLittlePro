package com.mxh1995.pro.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Encode {
	public static void main(String[] args) {
		String decode;
		try {
			decode = URLDecoder.decode("123456","1kdix");
			System.out.println(decode);
			String encode = URLEncoder.encode(decode,"utf-8");
			System.out.println(encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
