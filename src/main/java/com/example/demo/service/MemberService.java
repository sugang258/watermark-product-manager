package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberImageService memberImageService;

    public Long join(Member member, MultipartFile image) {

        memberRepository.saveMember(member);
        memberImageService.uploadImage(member,image);

        return member.getUserId();
    }

    public Member fineOne(Long userId) {
        return memberRepository.findMember(userId);
    }

}
