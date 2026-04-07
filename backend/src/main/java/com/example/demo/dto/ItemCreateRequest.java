package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemCreateRequest {
    private String name;
    private int price;
    private int stockQuantity;
}
