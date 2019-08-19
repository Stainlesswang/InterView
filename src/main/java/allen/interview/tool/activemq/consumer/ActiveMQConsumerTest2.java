package allen.interview.tool.activemq.consumer;

import allen.interview.tool.activemq.ActiveMQNameUtil;
import allen.interview.tool.activemq.JMSTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 消费者
 */
public class ActiveMQConsumerTest2 extends JMSTemplate implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ActiveMQConsumerTest2.class);

    public ActiveMQConsumerTest2(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * 实现消费者构造的代码
     * @param session
     * @throws JMSException
     */
    @Override
    public void doTask(Session session) throws JMSException {
        Destination destination = session.createQueue(ActiveMQNameUtil.TEST_QUEUE);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);
    }

    @Override
    public boolean isConsumer() {
        return true;
    }

    /**
     * MessageListener 需要实现的方法,处理取到的消息
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("One 2 One Consumer1###received msg:" + textMessage.getText());
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
