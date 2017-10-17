package com.mxh1995.pro.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    //目标对象  
    private Object target;  
      
    /** 
     * 构造方法 
     * @param target 目标对象 
     */  
    public MyInvocationHandler(Object target) {  
        super();  
        this.target=target;  
    }  
    
    
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在目标方法执行前简单打印一下  
        System.out.println("----------before----------");  
          
        //执行目标方法对象  
        Object result=method.invoke(target, args);  
          
        //在目标方法执行之后简单打印一下  
        System.out.println("----------after----------");  
         
        return result;  
	}
	
    public Object getProxy(){  
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),   
                this.target.getClass().getInterfaces(),this);  
    }
    
    
    public static void main(String[] args) {
    	UserService userService = new UserServiceImpl();
    	
    	MyInvocationHandler handler = new MyInvocationHandler(userService);
    	
    	UserService proxy = (UserService) handler.getProxy();
    	
    	proxy.add();
	}

}
