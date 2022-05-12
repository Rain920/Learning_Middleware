import javax.jms.DeliveryMode;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


/**Publisher
 * @author wanghuadong
 * @createDate 2013-6-19 下午04:34:36
 * @file TopicSender.java
 * @project ActiveMQ-5.8
 * @version 1.0
 */
public class TopicSender {

    // 发送次数
    public static final int SEND_NUM = 8;

    public static final String BROKER_URL = "tcp://localhost:61616";

    public static final String DESTINATION = "葛雨凡";


    public static void sendMessage(TopicSession session, TopicPublisher publisher) throws Exception {
        for (int i = 1; i <= SEND_NUM; i++) {

            TextMessage message = session.createTextMessage("使用ActiveMq发布消息" +i);

            System.out.println("使用ActiveMq发布消息" +i);

            publisher.send(message);
        }
    }

    public static void run() throws Exception {

        TopicConnection connection = null;
        TopicSession session = null;
        try {
            // 创建链接工厂
            TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            // 通过工厂创建一个连接
            connection = factory.createTopicConnection();

            // 启动连接
            connection.start();
            // 创建一个session会话

            session = connection.createTopicSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Topic topic = session.createTopic(DESTINATION);
            // 创建消息发送者
            TopicPublisher publisher = session.createPublisher(topic);
            // 设置持久化模式
            publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
            sendMessage(session, publisher);
            // 提交会话
            //session.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭释放资源
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TopicSender.run();
    }
}