package allen.interview.tool.activemq;

import allen.interview.tool.activemq.consumer.ActiveMQConsumerTest;
import allen.interview.tool.activemq.producer.ActiveMQProducerTest;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.io.IOException;

public class ActiveMQTest {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQTest.class);

    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = ActiveMQUtil.getActiveMQConnectionFactory();

            JMSTemplate consumer = new ActiveMQConsumerTest(activeMQConnectionFactory);
            consumer.startTask();

            JMSTemplate producer = new ActiveMQProducerTest(activeMQConnectionFactory);
            producer.startTask();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
