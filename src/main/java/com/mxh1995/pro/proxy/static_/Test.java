package com.mxh1995.pro.proxy.static_;

public class Test {
	public static void main(String[] args) {
		CountImpl count = new CountImpl();
		CountProxy proxy = new CountProxy(count);
		proxy.queryCount();
	}
}
