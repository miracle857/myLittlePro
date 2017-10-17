package com.mxh1995.pro.test;

import java.util.UUID;

public class Test {
	public static void main(String[] args) {
		String string = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(string);
	}
}
