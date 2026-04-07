package com.example.demo.controller;

import com.example.demo.domain.item.Item;
import com.example.demo.dto.ItemCreateRequest;
import com.example.demo.dto.ItemImageResponse;
import com.example.demo.dto.ItemResponse;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Long createItem(
            @RequestPart("item") ItemCreateRequest request,
            @RequestPart("images") List<MultipartFile> images
    ) throws IOException {

        Item item = new Item();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setStockQuantity(request.getStockQuantity());

        return itemService.saveItem(item, images);
    }

    @GetMapping
    public List<ItemResponse> findItems() {
        return itemService.findItems().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{itemId}")
    public ItemResponse findItem(@PathVariable Long itemId) {
        return toResponse(itemService.findOne(itemId));
    }

    private ItemResponse toResponse(Item item) {
        List<ItemImageResponse> images = item.getImages().stream()
                .map(img -> new ItemImageResponse(
                        img.getId(),
                        img.getImageUrl(),
                        img.isThumbnail(),
                        img.getSortOrder()
                ))
                .toList();

        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getStockQuantity(),
                images
        );
    }
}
