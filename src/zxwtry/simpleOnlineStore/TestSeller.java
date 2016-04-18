package zxwtry.simpleOnlineStore;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import model.Seller;

public class TestSeller {
	public static void main(String[] args) {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		Connection con = null;
		try {
			con = factory.createConnection();
			con.start();
			Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("sellerToBeAudited");
			MessageConsumer consumer = session.createConsumer(queue);
			ObjectMessage msg = (ObjectMessage) consumer.receive();
			Seller newSeller = (Seller)msg.getObject();
			System.out.println(newSeller.getPasswd());
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				try {
					con.stop();
					con.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
