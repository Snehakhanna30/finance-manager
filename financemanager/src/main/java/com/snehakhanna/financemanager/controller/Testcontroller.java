package com.snehakhanna.financemanager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class Testcontroller {

    @GetMapping
    public String hello() {
        return "🎉 Backend is working!";
    }
}
