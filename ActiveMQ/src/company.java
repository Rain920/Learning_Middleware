import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class company {
    public static void main(String[] args) {

        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");

        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue("葛雨凡_物流");

            producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            sendMessage(session, producer);
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    if (null != connection) {
                        connection.close();
                    }
                } catch (Throwable ignore) {

                }
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        TextMessage message = session.createTextMessage("【已下单】商品已下单" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【仓库已接单】您的订单已入库" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【已出库】包裹已出库" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【已发货】包裹正在等待揽收" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【已揽件】|金华市| 您的包裹已由物流公司揽收" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【运输中】|金华市| 从浙江义乌公司发出，正发往湖北武昌转运中心" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【运输中】|鄂州市| 快件已到达 湖北武昌转运中心" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【运输中】|鄂州市| 快件已从湖北武昌转运中心发出，正发往湖北武汉南湖新城公司" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【运输中】|武汉市| 快件已到达 湖北武汉南湖新城公司" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);


        message = session.createTextMessage("【派送中】|武汉市| 湖北武汉南湖新城公司 派件员：湖工大校内 电话15478964876 当前正在为您派件" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【待取件】您的包裹已放入湖北工业大学中区食堂旁红房子代收点，请您尽快前往领取" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);

        message = session.createTextMessage("【已签收】" + simpleDateFormat.format(new Date()));
        producer.send(message);
        System.out.println("发送消息：" + message.getText());
        Thread.sleep(2000);


    }
}
