package com.gaurav.EJBClient;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.gaurav.sessionBean.SessionBeanLocal;
import com.gaurav.sessionBean.SessionBeanRemote;

public class EJBRemoteCall {
	
	public static void main(String[] args) {
		try {
			Properties props= new Properties();
			props.put(Context.PROVIDER_URL, "t3://localhost:7001");
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			
			System.out.println("Hi A");
			Context ctx= new InitialContext(props);
			SessionBeanRemote bean = (SessionBeanRemote)ctx.lookup("/BeanDemo#com.gaurav.sessionBean.SessionBeanRemote");
			System.out.println("Hi " + bean.RemoteBeanCall("gaurav"));

			SessionBeanLocal bean1 = (SessionBeanLocal)ctx.lookup("/BeanDemo#com.gaurav.sessionBean.SessionBeanLocal");
			
			System.out.println("Hi " + bean1.LocalBeanCall("gaurav Local"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Hi  e" +  e);
		} 

	}

}
