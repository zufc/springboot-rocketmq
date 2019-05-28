package com.zfc.study.rocketmq.message.impl;

import com.zfc.study.rocketmq.message.MessageProcessor;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @Author zufeichao
 * @ProjectName springboot-rocketmq
 * @Description TODO
 * @Date 2019-05-27 9:35
 **/
@Service
public class MessageProcessorImpl implements MessageProcessor {
    @Override
    public boolean handle(MessageExt messageExt) {
        //这里收到的body（也就是消息体）是字节类型，转为String
        String result = new String(messageExt.getBody());
        System.out.println("收到了消息："+ result);
        return true;
    }
}


