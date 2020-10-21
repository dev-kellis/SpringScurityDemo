package kr.kellis.springsecuritydemo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "/home/home";
    }

    @GetMapping("/main")
    public String main(){
        return "/home/main";
    }
}
