package com.openapi.restdoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gyuhwan
 */
@RequestMapping("/items")
@RestController
public class ItemController {

    @GetMapping
    public ItemResponse getItem() {
        return new ItemResponse(1, "쌍쌍바", 1000,50);
    }
}
