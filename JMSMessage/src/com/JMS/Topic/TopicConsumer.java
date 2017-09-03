package com.JMS.Topic;

import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class TopicConsumer implements MessageListener {

	TopicConnection topicConnection = null;

	boolean subscriptionOn;

	public static void main(String[] args) {

		TopicConsumer wqts = new TopicConsumer();

		try {
			while (wqts.subscriptionOn) {
				Thread.sleep(500);
			}
			// onMessage() waits for message here
		} catch (java.lang.InterruptedException intExc) {
		}

		try {
			wqts.topicConnection.close();
		} catch (javax.jms.JMSException jmsEx) {
			System.out.println("JMS Exception: " + jmsEx.toString());
		}
		System.out.println("  Weasley quidditch subscriber closed");
	}

	public TopicConsumer() {
		TopicConnectionFactory topicConnectionFactory = null;
		Topic quidditchTopic = null;
		this.subscriptionOn = true;

		try {
			Properties prop = new Properties();
			prop.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");
			prop.setProperty(Context.PROVIDER_URL, "t3://localhost:7001");
			Context jndiContext = new InitialContext(prop);
			topicConnectionFactory = (TopicConnectionFactory) jndiContext
					.lookup("/Connection");
			quidditchTopic = (Topic) jndiContext.lookup("/Topic");

		} catch (NamingException nameEx) {
			System.out.println("Naming Exception: " + nameEx.toString());
		}

		try {
			topicConnection = topicConnectionFactory.createTopicConnection();
			TopicSession topicSession = topicConnection.createTopicSession(
					false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber topicSubscriber = topicSession
					.createSubscriber(quidditchTopic);
			topicSubscriber.setMessageListener(this);
			// will use the onMessage() method below
			topicConnection.start();
		} catch (javax.jms.JMSException jmsEx) {
			System.out.println("JMS Exception: " + jmsEx.toString());
		}
	}

	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			System.out.println(" receiving line " + " : "
					+ textMessage.getText());
			if (textMessage.getText().equals("end of message")) {
				this.subscriptionOn = false;
			}
		} catch (javax.jms.JMSException jmsEx) {
			System.out.println("JMS Exception in onMessage: "
					+ jmsEx.toString());
		}
	}
}