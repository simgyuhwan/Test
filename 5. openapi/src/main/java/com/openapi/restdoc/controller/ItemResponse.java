package com.openapi.restdoc.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Gyuhwan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
    private int id;
    private String name;
    private int price;
    private int quantity;
}
