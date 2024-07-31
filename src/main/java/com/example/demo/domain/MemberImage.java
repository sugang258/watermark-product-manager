package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "member_image")
public class MemberImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_num")
    private Long fileNum;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @NotEmpty
    @Column(name = "file_name")
    private String fileName;

    public MemberImage(Member member, String fileName) {
        this.member = member;
        this.fileName = fileName;
    }
}
