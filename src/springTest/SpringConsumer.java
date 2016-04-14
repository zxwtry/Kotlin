package springTest;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class SpringConsumer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws JMSException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate tmplate = (JmsTemplate)context.getBean("jmsTemplate");
		ActiveMQQueue queue = (ActiveMQQueue)context.getBean("queueDestination");
		TextMessage msg = (TextMessage)tmplate.receive(queue);
		System.out.println(msg.getText());
	}
}
