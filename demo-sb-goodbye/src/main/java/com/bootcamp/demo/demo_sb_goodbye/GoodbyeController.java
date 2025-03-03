package com.bootcamp.demo.demo_sb_goodbye;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class GoodbyeController {
    @GetMapping(value = "/iphone/goodbye")
    public String hello() {
        return "Goodbye!";
    }
}
