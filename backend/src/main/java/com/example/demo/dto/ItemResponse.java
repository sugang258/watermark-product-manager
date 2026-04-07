package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemResponse {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private List<ItemImageResponse> images;
}
