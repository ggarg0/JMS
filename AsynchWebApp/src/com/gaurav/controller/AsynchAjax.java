package com.gaurav.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaurav.sessionbean.ProducerSessionBeanRemote;

/**
 * Servlet implementation class helloWorld
 */

public class AsynchAjax extends HttpServlet {
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AsynchAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		res.setContentType("text/html");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		System.out.println("Date : " + sdf.format(cal.getTime()));
		Properties props = new Properties();
		props.put(Context.PROVIDER_URL, "t3://localhost:7001");
		props.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");

		System.out.println("Hi A");
		Context ctx;
		try {

			ctx = new InitialContext(props);

			ProducerSessionBeanRemote bean = (ProducerSessionBeanRemote) ctx
					.lookup("Producer#com.gaurav.sessionbean.ProducerSessionBeanRemote");
			bean.sendJMSMessage("Synchronus Message");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

}
