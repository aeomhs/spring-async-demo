package com.example.demo.service;

import com.example.demo.domain.UserSubmitHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SubmitService {

    private static long generateId = 0;

    private Map<String, SubmitPool> submitPools;

    public SubmitService() {
        submitPools = new ConcurrentHashMap<>();
    }

    // <------- Generate Submit Pool -------->
    public String generateSubmitPool() {
        String roomCode = generateId().toString();
        submitPools.put(roomCode, new SubmitPool());
        return roomCode;
    }

    synchronized public static Long generateId() {
        return generateId++;
    }

    // <------- Exist Submit Pool -------->
    public boolean contains(String id) {
        return submitPools.containsKey(id);
    }

    // <-------  Submit  -------->
    public void submit(String roomCode, UserSubmitHolder submitHolder) throws InterruptedException {
        if (!submitPools.containsKey(roomCode))
            throw new RuntimeException("invalid Pool ID" + roomCode);

        submitPools.get(roomCode).add(submitHolder);
    }

    // <------- Request Result, Blocking -------->
    public String getResult(String poolId) throws InterruptedException {

        return submitPools.get(poolId).getResult();
    }
}
