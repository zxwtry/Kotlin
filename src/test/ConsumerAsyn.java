package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerAsyn {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		Connection con = factory.createConnection();
		con.start();
		
		// Admin lay conduct producer consumer
		// fisrt arg is th 事务  (保证原子性)
		// false 事务 is independent 
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
		final MessageConsumer consumer = session.createConsumer(queue);
		
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message arg0) {
				// TODO Auto-generated method stub
				try {
					TextMessage msg = (TextMessage) consumer.receive();
					System.out.println(msg.getText());
					msg.acknowledge();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		// receive() 会把在主线程阻塞
		TextMessage msg = (TextMessage)consumer.receive();
		System.out.println(msg.getText());
		
		// 一般是在使用者自己可以进行判断的时候,判断
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
		
		
		//con.stop();
		// In an application con close at last
		
		//con.close();
	
		
	}
}
