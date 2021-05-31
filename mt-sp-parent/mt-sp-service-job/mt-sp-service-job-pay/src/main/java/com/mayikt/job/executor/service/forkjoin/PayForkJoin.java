package com.mayikt.job.executor.service.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveAction;

@Slf4j
public class PayForkJoin extends RecursiveAction {

    private int start;
    private int end;
    private int max = 2;

    public PayForkJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= max) {
            log.info(">>>db:start:{},end:{}<<<", start, end);
            // db limit(start,end)
        } else {
            int i = (end + start) / 2;
            PayForkJoin left = new PayForkJoin(start, i);
            PayForkJoin rigt = new PayForkJoin(i + 1, end);
            left.fork();
            rigt.fork();
        }
    }
}
