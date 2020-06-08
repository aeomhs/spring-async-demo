package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SubmitService {

    private Map<Long, SubmitPool> submitPools;

    public SubmitService() {
        submitPools = new ConcurrentHashMap<>();
    }

    public String getResult(Long id) {
        if (!submitPools.containsKey(id))
            return null;

        return "Done!";
    }

    public boolean isDone(Long id) {
        return submitPools.containsKey(id) && submitPools.get(id).isDone();
    }

    public void submit(Long id, String msg) {
        SubmitPool submitPool = getSubmitPool(id);
        submitPool.add(msg);
    }

    private SubmitPool getSubmitPool(Long id) {
         submitPools.putIfAbsent(id, new SubmitPool());
         return submitPools.get(id);
    }
}
