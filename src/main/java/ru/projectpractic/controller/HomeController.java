package ru.projectpractic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/welcome")
    public String welcomePage() {
        return "home";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
