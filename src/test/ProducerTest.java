package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ProducerTest {
	public static void main(String[] args) throws JMSException {
		// JNDI or Spring IoC
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		Connection con = factory.createConnection();
		con.start();
		
		// Admin lay conduct producer consumer
		// fisrt arg is th 事务  (保证原子性)
		// false 事务 is independent 
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Destination
		// 
		Queue queue = session.createQueue("zxwtry01");
		MessageProducer producer = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("I am zxwtry");
		producer.send(msg);
		
	
		/*
		 * Session session = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
		 * for (int i = 0; i < 10000; i ++) {
		 * 		TextMessage msg = con
		 * 		producer.send(msg);
		 * }
		 * session.commit();
		 */
		
		
		con.stop();
		// In an application con close at last
		
		con.close();
		
	}
	
	
	
	//消息事先没有进行订阅,那么不可能接受到
	@Test
	public void testTopic() {
		
	}
}
