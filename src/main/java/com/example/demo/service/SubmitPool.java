package com.example.demo.service;


import com.example.demo.domain.UserSubmitHolder;

import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SubmitPool {

    private final BlockingQueue<String> resultHolder;

    private static final int nClient = 3;

    private final Deque<UserSubmitHolder> submitted;

    public SubmitPool() {
        this.resultHolder = new ArrayBlockingQueue<>(nClient);
        this.submitted = new ConcurrentLinkedDeque<>();
    }

    public String getResult() throws InterruptedException {
        return resultHolder.take();
    }

    public void add(UserSubmitHolder submitHolder) throws InterruptedException {
        submitted.add(submitHolder);
        if (nClient == submitted.size())
            generateResult();
    }

    private void generateResult() throws InterruptedException {
        for (int i = 0; i < nClient; i++) {
            resultHolder.put("Done!");
        }
    }
}
