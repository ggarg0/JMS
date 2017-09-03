package com.gaurav.Interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SimpleInterceptor {
	@AroundInvoke
    public Object intercept(InvocationContext context) throws Exception{
		 System.out.println("Before method invocation");
         //invoking start() method
         Object returnedValue = context.proceed();
         System.out.println("After method invocation");
		return returnedValue;
    }
}
