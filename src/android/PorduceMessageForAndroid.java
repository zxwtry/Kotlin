package android;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PorduceMessageForAndroid {
	private static final String IP = "127.0.0.1";
	private static final String ACTIVE_MQ_TCP_URI = "tcp://"+IP+":61616";
	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_MQ_TCP_URI);
		Connection con = factory.createConnection();
		con.start();
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("zxwtry_android_message_distributor");
		MessageProducer producer = session.createProducer(queue);
		TextMessage msg = session.createTextMessage("This is a TextMessage for android");
		producer.send(msg);
		con.stop();
		con.close();
	}
}