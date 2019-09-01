package com.example.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @ResponseBody
    @RequestMapping("/wait")
    public String wait(Model model) throws InterruptedException {

        Thread.sleep(1000);

        return "response";
    }

}
