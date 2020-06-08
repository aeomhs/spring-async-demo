package com.example.demo.service;


import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SubmitPool {

    private int nClient;

    private Deque<String> submitted;

    private boolean isDone;

    public SubmitPool() {
        this(3);
    }

    public SubmitPool(int nClient) {
        this.nClient = nClient;
        this.submitted = new ConcurrentLinkedDeque<>();
        isDone = false;
    }

    public void add(String msg) {
        submitted.add(msg);
        if (submitted.size() == nClient)
            isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }
}
