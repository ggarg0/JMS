package com.gaurav.accountInfo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.gaurav.Interceptor.SimpleInterceptor;

/**
 * Session Bean implementation class AccountStatelessBean
 * @Interceptors(SimpleInterceptor.class) ---- TEST
 */

@Stateful(mappedName="Account")
@Interceptors(SimpleInterceptor.class)
public class AccountStatefulBean implements AccountStatefulBeanRemote, AccountStatefulBeanLocal {

	public int total=1000;

	@PostActivate
	public void PostActivate()
	{
		total= total + 5000;
		System.out.println("@PostActivate : " + total);
	}

	@PreDestroy
	public void PreDestroy() {
		System.out.println("@@PreDestroy : " + total);
	}

	@PostConstruct
	public void PostConstruct() {
		System.out.println("@@PostConstruct : " + total);
	}

	@PrePassivate
	public void prePassivate() {
		System.out.println("@@PrePassivate : " + total);
	}


    public AccountStatefulBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void deposit(int amt) {
		total=total+amt;
		System.out.println("Total balance after deposit : " + total);

	}

	@Override
	public int withdraw(int amt) {
		total=total-amt;
		System.out.println("Total balance after withdrawal : " + total);
		return total;
	}

	@Override
	public int getBalance() {
		System.out.println("Total available balance : " + total);
		return total;
	}

}
