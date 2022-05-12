import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**Subscriber
 * @author wanghuadong
 * @createDate 2015-6-19 下午01:34:27
 *
 * @file TopicReceiver.java
 * @project ActiveMQ-5.8
 */
public class TopicReceiver {


    public static final String BROKER_URL = "tcp://localhost:61616";

    public static final String TARGET = "葛雨凡";
    public static final String CLIENTID = "receiver2";

    public static void run() throws Exception {

        TopicConnection connection = null;
        TopicSession session = null;
        try {
            // 创建链接工厂
            TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            // 通过工厂创建一个连接
            connection = factory.createTopicConnection();

            connection.setClientID(CLIENTID);
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createTopicSession(Boolean.FALSE , Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Topic topic = session.createTopic(TARGET);
            // 创建消息制作者
            // TopicSubscriber subscriber = session.createSubscriber(topic);

            TopicSubscriber subscriber = session.createDurableSubscriber(topic, "ss");
            subscriber.setMessageListener(new MessageListener() {
                public void onMessage(Message msg) {
                    if (msg != null) {
                        TextMessage text = (TextMessage) msg;
                        try {
                            System.out.println(CLIENTID + "接收消息：" + text.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Thread.sleep(10000);

            // 提交会话
            // session.commit();

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
        TopicReceiver.run();
    }
}