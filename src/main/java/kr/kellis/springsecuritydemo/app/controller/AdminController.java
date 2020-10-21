package kr.kellis.springsecuritydemo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/main")
    public String adminHome(){
        return "admin/home";
    }
}
