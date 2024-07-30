package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberImage;
import com.example.demo.repository.MemberImageRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberImageService {

    private final MemberRepository memberRepository;
    private final MemberImageRepository memberImageRepository;

    public void uploadImage(Member member, MultipartFile image) {
        try {
            // 이미지 파일 저장을 위한 경로 설정
            String uploadsDir = "src/main/resources/static/uploads/images/";

            // 이미지 파일 경로를 저장
            String dbFilePath = saveImage(image, uploadsDir);

            // MemberImage 엔티티 생성 및 저장
            MemberImage memberImage = new MemberImage(member, dbFilePath);
            memberImageRepository.saveImage(memberImage);

        } catch (IOException e) {
            // 파일 저장 중 오류가 발생한 경우 처리
            e.printStackTrace();
        }

    }

    // 이미지 파일을 저장하는 메서드
    public String saveImage(MultipartFile image, String uploadsDir) throws IOException{

        //파일 이름 생성
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + image.getOriginalFilename();

        //실제 파일이 저장될 경로
        String filePath = uploadsDir + fileName;

        // DB에 저장할 경로 문자열
        String dbFilePath = "/uploads/images/" + fileName;

        Path path = Paths.get(filePath); // Path 객체 생성
        Files.createDirectories(path.getParent()); // 디렉토리 생성
        Files.write(path, image.getBytes()); // 디렉토리에 파일 저장

        return dbFilePath;
    }
}
