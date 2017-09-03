package com.gaurav.mdb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.JMS.employeeDAO.Employee;
import com.gaurav.ws.HelloWorld;

/**
 * Message-Driven Bean implementation class for: QueueMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "/Connection"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "/MDBQueue") }, mappedName = "/MDBQueue")
public class QueueMDB implements MessageListener {

	URL url;
	public QueueMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {

		try {
			
			url = new URL("http://localhost:7001/HelloWorldWS/HelloWorldImplService?WSDL");
			
			if (message instanceof TextMessage) {
				System.out.println("Queue: I received a TextMessage at "
						+ new Date());
				TextMessage msg = (TextMessage) message;
				System.out.println("Message is : " + msg.getText());
				
				String msg1=msg.getText();

				//1st argument service URI, refer to wsdl document above
				//2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://ws.gaurav.com/", "HelloWorldImplService");

				Service service = Service.create(url, qname);

				HelloWorld hello = service.getPort(HelloWorld.class);

				Map<String, Object> req_ctx = ((BindingProvider)hello).getRequestContext();
			    req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:7001/HelloWorldWS/HelloWorldImplService?WSDL");
  
				Map<String, List<String>> headers = new HashMap<String, List<String>>();
				    headers.put("Username", Collections.singletonList(msg1));
				    headers.put("Password", Collections.singletonList("pass"));
				    req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);		
					System.out.println(" hello : "+hello.getHelloWorldAsString(msg1));
				
			}
				else if(message instanceof ObjectMessage) {
						System.out.println("Queue: I received an ObjectMessage "
								+ " at " + new Date());
						ObjectMessage msg11 = (ObjectMessage) message;
						Employee employee = (Employee) msg11.getObject();
						System.out.println("Employee Details: ");
						System.out.println("Employee ID : " + employee.getId());
						System.out.println("Employee Name : " + employee.getName());
						System.out.println("Employee Desgination : " + employee.getDesignation());
						System.out.println("Employee Salaray : " + employee.getSalary());
						System.out.println("Employee All : " + employee.toString());
					} 
				
				
			else {
				System.out.println("Not valid message for this Queue MDB");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
