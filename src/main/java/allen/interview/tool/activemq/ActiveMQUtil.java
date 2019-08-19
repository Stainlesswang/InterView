package allen.interview.tool.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ActiveMQUtil {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQUtil.class);

    public static ActiveMQConnectionFactory getActiveMQConnectionFactory() throws IOException {
        // load the ActiveMQ configerations
        Properties props = new Properties();
        try (InputStream inStream = ActiveMQUtil.class.getClassLoader()
                .getResourceAsStream("activemqcofig.properties");) {
            logger.debug("load the ActiveMQ config file:activemqcofig.properties");
            props.load(inStream);

            String brokerURL = props.getProperty("activemq.url");
            if (null == brokerURL || 0 == brokerURL.length()) {
                brokerURL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
            }
            String username = props.getProperty("activemq.username");
            if (null == username || 0 == username.length()) {
                username = ActiveMQConnectionFactory.DEFAULT_USER;
            }
            String password = props.getProperty("activemq.password");
            if (null == password || 0 == password.length()) {
                password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
            }
            return new ActiveMQConnectionFactory(username, password, brokerURL);
        }
    }
}
