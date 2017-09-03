package com.gaurav.sessionBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import com.gaurav.InterceptorDAO.SimpleInterceptor;

/**
 * Session Bean implementation class SessionBean
 * *
 */
@Stateful(mappedName = "/BeanDemo")
@Interceptors(SimpleInterceptor.class)
public class SessionBean implements SessionBeanRemote, SessionBeanLocal {

	/**
	 * Default constructor.
	 */
	public SessionBean() {

	}

	@PreDestroy
	public void PreDestroy() {
		System.out.println("Executing method : @PreDestroy");
	}

	@PostConstruct
	public void PostConstruct() {
		System.out.println("Invoking method: @PostConstruct ()");
	}

	@Override
	public String LocalBeanCall(String param) {
		System.out.println("Executing method : LocalBeanCall");
		return "Local Bean " + param;
	}

	@Override
	public String RemoteBeanCall(String param) {
		System.out.println("Executing method : RemoteBeanCall");
		return "Remote Bean " + param;
	}

	@Remove
	public void RemoveSesssion() {
		System.out.println("Invoking method: Remove ()");
	}

	@PostActivate
	public void postActivate() {
		System.out.println("Invoking method: @PostActivate ()");
	}
	
	@PrePassivate
	public void prePassivate() {
		System.out.println("Invoking method: @PrePassivate ()");

	}
}