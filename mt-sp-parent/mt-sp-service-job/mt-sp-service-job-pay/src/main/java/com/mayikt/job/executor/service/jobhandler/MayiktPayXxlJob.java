package com.mayikt.job.executor.service.jobhandler;

import com.mayikt.job.executor.service.forkjoin.PayForkJoin;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

/**
 * @author 余胜军
 * @ClassName MayiktPayXxlJob
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月18日 21:18:00
 */
@Component
@Slf4j
public class MayiktPayXxlJob {

    /**
     * 演示效果 单台服务器做多只会发送10 生产环境可以配置为1万
     */
    private Integer size = 10;

    @XxlJob("shardingPayJobHandler")
    public void shardingPayJobHandler() throws Exception {
        log.info(">shardingPayJobHandler<");
//
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        // 0*10 start=
        /**
         * shardIndex=0  start=0*size=0 end=0+10;
         * shardIndex=1  start=(1*size),end 10+10=20
         *
         */
        int start = shardIndex * size;
        int end = start + size;
        PayForkJoin payForkJoin = new PayForkJoin(start, end);
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        forkJoinPool.invoke(payForkJoin);


    }
}
