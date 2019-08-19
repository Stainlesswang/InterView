package allen.interview.tool.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * JMS操作模板类代码,
 */
public abstract class JMSTemplate {
    private ConnectionFactory connectionFactory;

    public JMSTemplate(ConnectionFactory connectionFactory){
        this.connectionFactory=connectionFactory;
    }

    public ConnectionFactory getConnectionFactory(){
        return this.connectionFactory;
    }

    /**
     * To personalize your task based on JMS, Such as producing a message to the
     * broker or consuming a message from the broker.
     *
     * @param session
     *            javax.jms.Session object
     */
    public abstract void doTask(Session session) throws JMSException;

    /**
     * Indicates whether the client is a message consumer.
     *
     * @return true if the client is a message consumer, false otherwise.
     */
    public abstract boolean isConsumer();

    /**
     * 将一些固有的操作也就是平常所说的模板代码提取出来
     * 其中的doTask();
     * isConsumer();
     * 是继承该类的具体实现
     */
    final void startTask() throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            doTask(session);
        } finally {
            if (!isConsumer()) {
                releaseConnection(connection, session);
            }
        }
    }

    private void releaseConnection(Connection connection, Session session) throws JMSException {
        if (null != session) {
            session.close();
        }
        if (null != connection) {
            connection.close();
        }
    }
}
