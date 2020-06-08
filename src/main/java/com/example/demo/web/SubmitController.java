package com.example.demo.web;

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

    @PostMapping("/{id}/submit")
    @ResponseBody
    public Callable<String> submit(@PathVariable Long id, @RequestBody String msg) {

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                submitService.submit(id, msg);

                //TODO Time Out Logic 필요!
                while (!submitService.isDone(id)) {
                    Thread.sleep(1000);
                }
                return submitService.getResult(id);
            }
        };
    }
}
