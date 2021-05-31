package com.mayikt.elk.log;/*
* 作    者 ：蚂蚁课堂-余胜军
* 版 本 号 ：v1.0.0.0
*******************************************************************
* 版权由每特教育-蚂蚁课堂-余胜军所有 微信yushengjun644 QQ644064779
* 官方网址:www.mayikt.com 
*******************************************************************
//----------------------------------------------------------------*/

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;

@Component
public class LogContainer {
    private LogThread logThread;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public LogContainer() {
        logThread = new LogThread();
        logThread.start();
    }

    private static LinkedBlockingDeque<String> logs = new LinkedBlockingDeque<>();

    /**
     * 存入一条日志消息到并发队列中
     *
     * @param log
     */
    public void putLog(String log) {
        logs.offer(log);
    }

    /**
     * 异步日志线程 实时从队列中获取内容
     */
    class LogThread extends Thread {
        @Override
        public void run() {
            while (true) {

                /**
                 * 代码的优化
                 * 当前线程批量获取多条日志消息 投递kafka   批量
                 *
                 */
                String log = logs.poll();
                if (!StringUtils.isEmpty(log)) {
                    /// 将该消息投递到kafka中 批量形式投递kafka
                    kafkaTemplate.send("mayikt-log", log);
                }
            }
        }
    }


}
