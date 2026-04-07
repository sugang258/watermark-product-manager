package com.example.demo.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStore {
    
    @Value("${file.dir}")
    private String fileDir;
    
    public String getFullPath(String filename) {
        return fileDir + filename;
    }
    
    public StoredFile storedFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }
        
        String originalFilename = multipartFile.getOriginalFilename();
        String ext = extractExt(originalFilename);
        String storedFileName = UUID.randomUUID() + "." + ext;

        Path path = Paths.get(getFullPath(storedFileName));
        Files.createDirectories(path.getParent());
        multipartFile.transferTo(path);

        return new StoredFile(originalFilename, storedFileName);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
