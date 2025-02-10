package com.example.movies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping({"/login", "/req/signup"})
    public String forwardToReact() {
        return "forward:/index.html";
    }
}
