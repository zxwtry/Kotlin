package exam;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ControlSendReceiveAmountAndFrequency {
	private static int producerSleepMil = 1000;
	private static int consumerSleepMil = 1000;
	private static int producerAmount = 10;
	private static int producerCount = 0;
	public static void main(String[] args) throws JMSException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Thread producerThread = new Thread(new ProducerOrConsumerRunnable(context, false));
		Thread consumerThread = new Thread(new ProducerOrConsumerRunnable(context, true));
		
//		producerSleepMil = Integer.parseInt(args[0]);
//		consumerSleepMil = Integer.parseInt(args[1]);
//		producerAmount = Integer.parseInt(args[2]);
		producerCount = 0;
		
		producerThread.start();
		consumerThread.start();
	}
	static class ProducerOrConsumerRunnable implements Runnable {
		final ApplicationContext context;
		final JmsTemplate template;
		final ActiveMQQueue queue;
		final boolean isConsumer;
		public ProducerOrConsumerRunnable(ApplicationContext context, boolean isConsumer) {
			this.context = context;
			this.template = (JmsTemplate)context.getBean("jmsTemplate");
			this.queue = (ActiveMQQueue)context.getBean("queueDestination");
			this.isConsumer = isConsumer;
		}
		public void run() {
			if (isConsumer) {
				consumerThreadRun();
			} else {
				producerThreadRun();
			}
		}
		private void consumerThreadRun() {
			while (true) {
				try {
					Thread.sleep(consumerSleepMil);
					TextMessage msg = (TextMessage)template.receive(queue);
					System.out.println(msg.getText());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		private void producerThreadRun() {
			while (true) {
				try {
					Thread.sleep(producerSleepMil);
					template.send(queue, new MessageCreator(){
						public Message createMessage(Session session) throws JMSException {
							Message msg = session.createTextMessage("I am zxwtry " + String.format("%02d", producerCount));
							session.close();
							return msg;
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					producerCount ++;
				}
				if (producerAmount == producerCount) {
					break;
				}
			}
		}
	}
}
