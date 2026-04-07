package com.example.demo.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ItemImage {

    @Id
    @GeneratedValue
    @Column(name ="item_image_id")
    private Long id;

    private String originalFileName;
    private String storedFileName;
    private String imageUrl;

    private boolean thumbnail;
    private int sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
