package zxwtry.simpleOnlineStore;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestSeller {
	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
	}
}
