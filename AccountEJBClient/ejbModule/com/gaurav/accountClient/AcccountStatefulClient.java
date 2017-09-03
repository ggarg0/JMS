package com.gaurav.accountClient;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.gaurav.accountInfo.AccountStatefulBeanLocal;
import com.gaurav.accountInfo.AccountStatefulBeanRemote;



public class AcccountStatefulClient {

	public static void main(String[] args) {
		try {
			Properties props= new Properties();
			props.put(Context.PROVIDER_URL, "t3://localhost:7001");
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			
			System.out.println("Hi A");
			Context ctx= new InitialContext(props);
		
			AccountStatefulBeanRemote bean = (AccountStatefulBeanRemote)ctx.lookup("Account#com.gaurav.accountInfo.AccountStatefulBeanRemote");
			System.out.println("Remote Bean Starts");
			System.out.println("Balance  " + bean.getBalance());
			bean.deposit(500);
			bean.withdraw(200);
			bean.withdraw(200);
			System.out.println("Balance after Deposit : " + bean.getBalance());
			System.out.println("WithDrawal Amount " + bean.withdraw(200));
			System.out.println("Balance  " + bean.getBalance());
			System.out.println("Remote Bean end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Hi  e" +  e);
		} 
	}

}
