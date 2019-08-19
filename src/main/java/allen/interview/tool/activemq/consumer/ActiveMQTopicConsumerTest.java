package allen.interview.tool.activemq.consumer;

import allen.interview.tool.activemq.ActiveMQNameUtil;
import allen.interview.tool.activemq.JMSTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 消费者
 */
public class ActiveMQTopicConsumerTest extends JMSTemplate implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ActiveMQTopicConsumerTest.class);

    public ActiveMQTopicConsumerTest(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * 实现消费者构造的代码
     * @param session
     * @throws JMSException
     */
    @Override
    public void doTask(Session session) throws JMSException {
        Destination destination = session.createTopic(ActiveMQNameUtil.TEST_TOPIC);
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
            System.out.println("Consumer1####Topic msg Is:" + textMessage.getText());
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
