package com.gaurav.InterceptorDAO;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SimpleInterceptor {
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		System.out
				.println("SimpleInterceptor - Logging BEFORE calling method :"
						+ context.getMethod().getName());

		Object result = context.proceed();

		System.out.println("SimpleInterceptor - Logging AFTER calling method :"
				+ context.getMethod().getName());

		return result;

	}
	  @PostActivate
	  public void postActivate(InvocationContext ic) {
	    System.out.println("Invoking method: "+ic.getMethod());
	  }
	   @PrePassivate
	  public void prePassivate(InvocationContext ic) {
	    System.out.println("Invoking method: "+ic.getMethod());

}
}