package com.jisheng.异步编排;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool  {
    public ThreadPoolExecutor threadPoolExecutor(){
        return  new ThreadPoolExecutor(
                10, 100, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue(1000),  Executors.defaultThreadFactory());
    }

}
