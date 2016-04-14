package springTest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringProducer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate template = (JmsTemplate)context.getBean("jmsTemplate");
		ActiveMQQueue queue = (ActiveMQQueue)context.getBean("queueDestination");
		template.send(queue, new MessageCreator(){
			public Message createMessage(Session session) throws JMSException {
				Message msg = session.createTextMessage("I am zxwtry");
				session.close();
				return msg;
			}
		});
	}
}
