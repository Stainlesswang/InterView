package allen.interview.tool.activemq;

import allen.interview.tool.activemq.consumer.ActiveMQConsumerTest;
import allen.interview.tool.activemq.consumer.ActiveMQConsumerTest2;
import allen.interview.tool.activemq.consumer.ActiveMQTopicConsumerTest;
import allen.interview.tool.activemq.consumer.ActiveMQTopicConsumerTest2;
import allen.interview.tool.activemq.producer.ActiveMQProducerTest;
import allen.interview.tool.activemq.producer.ActiveMQTopicProducerTest;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.io.IOException;

/**
 * @author wangjianqiang
 */
public class ActiveMqTest {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMqTest.class);

    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = ActiveMQUtil.getActiveMQConnectionFactory();

            JMSTemplate consumer = new ActiveMQConsumerTest(activeMQConnectionFactory);
            consumer.startTask();
            JMSTemplate consumer2 = new ActiveMQConsumerTest2(activeMQConnectionFactory);
            consumer2.startTask();

            JMSTemplate producer = new ActiveMQProducerTest(activeMQConnectionFactory);
            producer.startTask();

//            /* topic/sub 主题订阅模式,开启多可consumer都可以收到producer生产的消息  start*/
//            JMSTemplate topicConsumer = new ActiveMQTopicConsumerTest(activeMQConnectionFactory);
//            JMSTemplate topicConsumer2 = new ActiveMQTopicConsumerTest2(activeMQConnectionFactory);
//            topicConsumer.startTask();
//            topicConsumer2.startTask();
//            //一个topic生产者创建
//            JMSTemplate topicProducer = new ActiveMQTopicProducerTest(activeMQConnectionFactory);
//            topicProducer.startTask();
//            /* topic/sub 主题订阅模式,开启多可consumer都可以收到producer生产的消息  start*/
        } catch (IOException | JMSException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
