package android;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PorduceMessageForAndroid {
	private static final String IP = "127.0.0.1";
	private static final String ACTIVE_MQ_TCP_URI = "tcp://"+IP+":61616";
	public static void main(String[] args) throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_MQ_TCP_URI);
		Connection con = factory.createConnection();
		con.start();
	}
}