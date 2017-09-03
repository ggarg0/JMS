package com.JMS.Topic;

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.*;
import javax.naming.*;

public class TopicPublisher {

	public static void main(String[] args) {
		
		String message[] = { "Hufflepuff 80, Slytherin 300",
		"Gryffindor 320, Ravenclaw 155" };

		TopicConnectionFactory topicConnectionFactory = null;
		Topic publisherTopic = null;

		try {
			Properties prop = new Properties();
			prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			prop.setProperty(Context.PROVIDER_URL, "t3://localhost:7001");
			Context jndiContext = new InitialContext(prop);
			topicConnectionFactory = (TopicConnectionFactory) jndiContext
					.lookup("/Connection");
			publisherTopic = (Topic) jndiContext.lookup("/Topic");
		} catch (NamingException nameEx) {
			System.out.println("Naming Exception: " + nameEx.toString());
		}

		TopicConnection topicConnection = null;

		try {
			topicConnection = topicConnectionFactory.createTopicConnection();
			TopicSession topicSession = topicConnection.createTopicSession(
					false, Session.AUTO_ACKNOWLEDGE);
			javax.jms.TopicPublisher topicPublisher = topicSession.createPublisher(publisherTopic);
			TextMessage textMessage = topicSession.createTextMessage();
			for (int msgCount = 0; msgCount < message.length; msgCount++) {
				textMessage.setText(message[msgCount]);
				((javax.jms.TopicPublisher) topicPublisher).publish(textMessage);
				System.out.println(" publishing line " + msgCount + " : "
						+ message[msgCount]);
			}

			textMessage.setText("end of message");
			((javax.jms.TopicPublisher) topicPublisher).publish(textMessage);
			System.out.println(" publishing last line " + " : "
					+ textMessage.getText());

			topicConnection.close();
			System.out.println("  quidditch publisher closed");

		} catch (javax.jms.JMSException jmsEx) {
			System.out.println("JMS Exception: " + jmsEx.toString());
		} finally {
			if (topicConnection != null) {
				try {
					topicConnection.close();
				} catch (javax.jms.JMSException jmse) {
				}
			}
		}
	}
	
}
