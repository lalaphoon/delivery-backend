package rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class P {
    private final static String QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
//		设置RabbitMQ地址
        factory.setHost("localhost");
//		创建一个新的连接
        Connection connection = factory.newConnection();
//		创建一个频道
        Channel channel = connection.createChannel();
//		声明一个队列 -- 在RabbitMQ中，队列声明是幂等性的（一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同），也就是说，如果不存在，就创建，如果存在，不会对已经存在的队列产生任何影响。
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

//		发送消息到队列中
        //channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

        for(int i = 0 ; i < 5; i++){
            String message = "Hello World! " + i;
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }


//		关闭频道和连接
        channel.close();
        connection.close();
    }
}
