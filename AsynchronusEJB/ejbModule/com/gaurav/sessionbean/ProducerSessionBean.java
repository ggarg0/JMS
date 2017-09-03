package com.gaurav.sessionbean;

import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.jms.JMSException;
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

/**
 * Session Bean implementation class ProducerSessionBean
 */
@Stateless(mappedName = "Producer")
public class ProducerSessionBean implements ProducerSessionBeanRemote {

	/**
	 * Default constructor.
	 */
	public ProducerSessionBean() {
		// TODO Auto-generated constructor stub
	}

	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public final static String JMS_FACTORY = "/Connection";
	public final static String QUEUE = "/Queue";

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;

	public void init(Context ctx, String queueName) throws NamingException,
			JMSException {
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
		qcon = qconFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
		msg = qsession.createTextMessage();
		qcon.start();
	}

	public void send(String message) throws JMSException {
		msg.setText(message);
		qsender.send(msg);
	}

	public void close() throws JMSException {
		qsender.close();
		qsession.close();
		qcon.close();
	}

	public void sendJMSMessage(String msg) throws JMSException {
		String url = "t3://localhost:7001";
		InitialContext ic;
		try {
			ic = getInitialContext(url);

			ProducerSessionBean qs = new ProducerSessionBean();
			qs.init(ic, QUEUE);
			String line = "GauravEJB";

			qs.send(line);

			qs.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static InitialContext getInitialContext(String url)
			throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, url);
		return new InitialContext(env);
	}

}
