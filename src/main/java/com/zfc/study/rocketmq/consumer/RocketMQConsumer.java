package com.zfc.study.rocketmq.consumer;

import com.zfc.study.rocketmq.message.MessageListen;
import com.zfc.study.rocketmq.message.MessageProcessor;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author zufeichao
 * @ProjectName springboot-rocketmq
 * @Description TODO
 * @Date 2019-05-27 9:38
 **/
@Component
public class RocketMQConsumer  {
    private static final Logger log = LoggerFactory.getLogger(RocketMQConsumer.class);
    @Autowired
    private MessageProcessor messageProcessor;

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer()
    {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setVipChannelEnabled(false);
        //我们自己实现的监听类
        MessageListen messageListen = new MessageListen();
        messageListen.setMessageProcessor(messageProcessor);
        consumer.registerMessageListener(messageListen);
        try {
            consumer.subscribe(topic,tag);
            consumer.start();
            log.info("consume is start ,groupName:{},topic:{}",groupName,topic);
        } catch (MQClientException e) {
            log.error("consume start error");
            e.printStackTrace();
        }
        return consumer;
    }


}

