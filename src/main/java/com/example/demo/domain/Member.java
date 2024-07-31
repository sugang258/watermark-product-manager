package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty
    @Column(name = "user_pw")
    private String userPw;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberImage> memberImage = new ArrayList<>();


    public Member(Long userId, String userPw, String name, MemberImage memberImage) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
    }
}
