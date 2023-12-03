package com.employees.manage.staff.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gyuhwan
 */
@RequestMapping("/api/v1")
@RestController
public class TestController {

    @GetMapping("/only-test")
    public String test() {
        return "perfect test";
    }

}
