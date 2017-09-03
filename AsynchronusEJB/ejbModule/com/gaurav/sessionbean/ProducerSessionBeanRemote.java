package com.gaurav.sessionbean;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface ProducerSessionBeanRemote {
	public void sendJMSMessage(String msg) throws JMSException;
}
