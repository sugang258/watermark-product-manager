package com.example.demo.service;

import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.ItemImage;
import com.example.demo.file.FileStore;
import com.example.demo.file.StoredFile;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    @Value("${watermark.path}")
    private String watermarkPath;

    private final ItemRepository itemRepository;
    private final FileStore fileStore;
    private final ImageWatermarkService imageWatermarkService;

    @Transactional
    public Long saveItem(Item item, List<MultipartFile> imageFiles) throws IOException {
        for (int i = 0; i < imageFiles.size(); i++) {
            MultipartFile imageFile = imageFiles.get(i);

            if (imageFile.isEmpty()) {
                continue;
            }

            StoredFile storedFile = fileStore.storedFile(imageFile);

            imageWatermarkService.addImageWatermark(storedFile.getStoredFileName(),watermarkPath);

            ItemImage itemImage = new ItemImage();
            itemImage.setOriginalFileName(storedFile.getOriginalFileName());
            itemImage.setStoredFileName(storedFile.getStoredFileName());
            itemImage.setImageUrl("/images/" + storedFile.getStoredFileName());
            itemImage.setThumbnail(i == 0);
            itemImage.setSortOrder(i);

            item.addImage(itemImage);
        }

        itemRepository.save(item);
        return item.getId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
