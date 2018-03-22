package com.mxh1995.pro.test;

public class 类的实例化顺序 extends Base{
	
	public static void main(String[] args) {
		new Child();
		System.out.println();
		new Child();
	}

}

class Child extends Base{
	static {
		System.out.println("孩子静态代码块");
	}
	
	{
		System.out.println("孩子普通代码块");
	}
	
	Child(){
		System.out.println("子类构造器");
	}
}

class Base {

	Base(){
		System.out.println("构造器");
	}
	
	static {
		System.out.println("父亲静态代码块");
	}
	
	{
		System.out.println("父亲普通代码块");
	}

}
