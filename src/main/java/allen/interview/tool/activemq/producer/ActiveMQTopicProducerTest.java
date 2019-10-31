package allen.interview.tool.activemq.producer;

import allen.interview.tool.activemq.ActiveMqNameUtil;
import allen.interview.tool.activemq.JMSTemplate;

import javax.jms.*;

public class ActiveMQTopicProducerTest extends JMSTemplate {
    public ActiveMQTopicProducerTest(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void doTask(Session session) throws JMSException {
        Destination sessionTopic = session.createTopic(ActiveMqNameUtil.TEST_TOPIC);
        MessageProducer topicProducer = session.createProducer(sessionTopic);
        topicProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 10; i++) {
            TextMessage messageTopic = session.createTextMessage("Every one Should hear me____"+i);
            topicProducer.send(messageTopic);
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
