package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ConsumerTest {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		Connection con = factory.createConnection();
		con.start();
		
		// Admin lay conduct producer consumer
		// fisrt arg is th ����  (��֤ԭ����)
		// false ���� is independent 
		// session.commit()
		//Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Session session = con.createSession(true, Session.CLIENT_ACKNOWLEDGE);
		// Destination
		// 
		Queue queue = session.createQueue("zxwtry01");
//		MessageProducer producer = session.createProducer(queue);
//		TextMessage msg = session.createTextMessage("I am zxwtry");
//		producer.send(msg);
//		
		MessageConsumer consumer = session.createConsumer(queue);
		
		// receive() ��������߳�����
		TextMessage msg = (TextMessage)consumer.receive();
		System.out.println(msg.getText());
		
		// һ������ʹ�����Լ����Խ����жϵ�ʱ��,�ж�
//		session.commit();
		msg.acknowledge();
		
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
	
	
	
	@Test
	public void topic() {
		
	}
	
}
