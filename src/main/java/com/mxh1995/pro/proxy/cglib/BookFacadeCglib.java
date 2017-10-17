package com.mxh1995.pro.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



/**  
 * 使用cglib动态代理  
 *   
 * @author student  
 *   
 */    
public class BookFacadeCglib implements MethodInterceptor {    
    private Object target;    
    
    /**  
     * 创建代理对象  
     *   
     * @param target  
     * @return  
     */    
    public Object getInstance(Object target) {    
        this.target = target;    
        Enhancer enhancer = new Enhancer();    
        enhancer.setSuperclass(this.target.getClass());    
        // 回调方法    
        enhancer.setCallback(this);    
        // 创建代理对象    
        return enhancer.create();    
    }    
    
	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy methodProxy) throws Throwable {
		System.out.println("--前--");
		Object invokeSuper = methodProxy.invokeSuper(obj, arg2);
		System.out.println("--后--");
		return invokeSuper;
	}    
	
	public static void main(String[] args) {
		BookService book = new BookService();
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookService proxy = (BookService) cglib.getInstance(book);
		proxy.addBook();
		
	}
    
}    