package com.zfc.study.rocketmq.message;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Author zufeichao
 * @ProjectName springboot-rocketmq
 * @Description TODO
 * @Date 2019-05-27 9:34
 **/
public interface MessageProcessor {
    boolean handle(MessageExt messageExt);
}


