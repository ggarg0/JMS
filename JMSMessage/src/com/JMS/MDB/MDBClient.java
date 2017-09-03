package com.JMS.MDB;

import java.io.IOException;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.JMS.Queue.QueueSend;
import com.JMS.employeeDAO.Employee;

public class MDBClient {

	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public final static String JMS_FACTORY = "/Connection";
	public final static String QUEUE = "/MDBQueue";

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;

	private InitialContext ic;

	private String url = "t3://localhost:7001";

	public void init() throws NamingException, JMSException {

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, url);
		ic = new InitialContext(env);
	
		qconFactory = (QueueConnectionFactory) ic.lookup(JMS_FACTORY);
		qcon = qconFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ic.lookup(QUEUE);
		qsender = qsession.createSender(queue);

		qcon.start();
	}

	public void close() throws JMSException {
		qsender.close();
		qsession.close();
		qcon.close();
	}

	private void CreateMessage() throws IOException, JMSException {
		// 1. Sending TextMessage to the Queue
		TextMessage message = qsession.createTextMessage();
		message.setText("user");
		qsender.send(message);
		System.out.println("1. Sent TextMessage to the Queue");

		// 2. Sending ObjectMessage to the Queue
		ObjectMessage objMsg = qsession.createObjectMessage();
		Employee employee = new Employee();
		employee.setId(122182);
		employee.setName("Gaurav Garg");
		employee.setDesignation("TA");
		employee.setSalary(66000);
		objMsg.setObject(employee);
		qsender.send(objMsg);
		System.out.println("2. Sent ObjectMessage to the Queue");

	}
	
	public static void main(String[] args) throws Exception {
		MDBClient obj = new MDBClient();

		obj.init();
		obj.CreateMessage();
		obj.close();
	}

}
