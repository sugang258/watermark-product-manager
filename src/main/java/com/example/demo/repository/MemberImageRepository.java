package com.example.demo.repository;

import com.example.demo.domain.MemberImage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberImageRepository {

    private final EntityManager em;

    public void saveImage(MemberImage memberImage) {
        em.persist(memberImage);
    }
}
