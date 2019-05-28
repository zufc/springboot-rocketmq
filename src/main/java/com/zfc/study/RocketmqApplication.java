package com.zfc.study;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RocketmqApplication {

    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        ApplicationContext context = SpringApplication.run(RocketmqApplication.class,args);
        DefaultMQProducer producer = context.getBean(DefaultMQProducer.class);
        for(int i = 0; i < 10; i ++) {
            String body = "hello rocketMQ" + i;
            //注意，第一个参数是topic，第二个tag，要和配置文件保持一致，第三个是body，也就是我们要发的消息，字节类型。
            Message message = new Message("topic2019", "test", body.getBytes());
            SendResult result = producer.send(message);
            System.out.println("send:"+ result);
            Thread.sleep(1000);
        }
        //关闭资源
        producer.shutdown();
        System.out.println("producer shutdown!");


    }

}
