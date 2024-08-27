package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberImageService memberImageService;

    public Long join(Member member, List<MultipartFile> images) {

        memberRepository.saveMember(member);
        memberImageService.uploadImage(member,images);

        return member.getId();
    }

    public Member fineOne(Long userId) {
        return memberRepository.findMember(userId);
    }

}
