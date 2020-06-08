package com.example.demo.web;

import com.example.demo.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class IndexController {

    private AtomicLong client = new AtomicLong(1);

    private SubmitService submitService;

    @Autowired
    public IndexController(SubmitService submitService) {
        this.submitService = submitService;
    }

    @GetMapping("/")
    public String index(@RequestParam(name = "roomCode", required = false, defaultValue = "defaultCode") String roomCode, Model model) {
        if (!submitService.contains(roomCode))
            roomCode = submitService.generateSubmitPool();

        model.addAttribute("roomCode", roomCode);
        model.addAttribute("clientId", client.getAndIncrement());
        return "index";
    }
}
