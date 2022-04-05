package com.example.learn.recipes_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getView() {
        return "index";
    }

}
