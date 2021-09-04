package com.example.parksproject.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;
}
