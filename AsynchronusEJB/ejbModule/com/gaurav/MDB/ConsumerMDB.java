package com.gaurav.MDB;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "/Connection"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "/Queue") }, mappedName = "/Queue")
public class ConsumerMDB implements MessageListener {

	public ConsumerMDB() {
		// TODO Auto-generated constructor stub
	}

	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		for (int i = 0; i > 10; i++) {
			try {
				System.out.println("Message is : " + msg.getText());

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
