package com.example.demo.web;

import com.example.demo.domain.UserSubmitHolder;
import com.example.demo.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@Controller
public class SubmitController {

    private SubmitService submitService;

    @Autowired
    public SubmitController(SubmitService submitService) {
        this.submitService = submitService;
    }

    @PostMapping("/{roomCode}/submit/{clientId}")
    @ResponseBody
    public Callable<String> submit(@PathVariable String roomCode, @PathVariable Long clientId, @RequestBody String msg) {
        return () -> {
            submitService.submit(roomCode, new UserSubmitHolder(clientId, msg));

            // Blocked
            return submitService.getResult(roomCode);
        };
    }
}
