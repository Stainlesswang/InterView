package allen.interview.tool.activemq.producer;

import allen.interview.tool.activemq.ActiveMQNameUtil;
import allen.interview.tool.activemq.JMSTemplate;

import javax.jms.*;

/**
 * 消息生产者
 */
public class ActiveMQProducerTest extends JMSTemplate {

    public ActiveMQProducerTest(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * 实现具体的doTask方法
     * @param session
     * @throws JMSException
     */
    @Override
    public void doTask(Session session) throws JMSException {
        Destination destination = session.createQueue(ActiveMQNameUtil.TEST_QUEUE);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 20; i++) {
            TextMessage message = session.createTextMessage("Hello,JMS and ActiveMQ----->"+i);
            producer.send(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isConsumer() {
        return false;
    }
}
