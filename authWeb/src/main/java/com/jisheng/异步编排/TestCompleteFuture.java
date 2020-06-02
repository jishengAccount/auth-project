package com.jisheng.异步编排;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class TestCompleteFuture {
    @RequestMapping("testCompleteFuture")
    public void testComleteFuture() {
        //supplyAsync有返回值，runAsync没有返回值
        //thenApplyAsync有返回值，thenAcceptAsync没有返回值
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "supplyAsync";
        }, new MyThreadPool().threadPoolExecutor()).thenApplyAsync(e -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(e);
            return "supplyAsync";
        });
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("runAsync");
        });
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(stringCompletableFuture, runAsync);
        //阻塞
        try {
            voidCompletableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
