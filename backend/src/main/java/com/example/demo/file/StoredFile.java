package com.example.demo.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoredFile {
    private String originalFileName;
    private String storedFileName;
}
