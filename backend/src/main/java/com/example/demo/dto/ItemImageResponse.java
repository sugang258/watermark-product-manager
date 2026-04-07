package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemImageResponse {
    private Long id;
    private String imageUrl;
    private boolean thumbnail;
    private int sortOrder;
}
