package com.suwon.festival.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
 회원정보
    닉네임
    가입일
*/
@Entity // Table 기초
@Table (name ="member")
@Getter // private 꺼내쓸거니까 달아주기 ?
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 자동 생성자 틀, 외부에서 함부로 빈 객체를 못 만들게 설정
public class Member {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // 자동생성
    private Long id; // 회원번호

    // 중복 x
    @Column (unique = true, nullable = false) // 중복 x, null x
    private String nickname; // 닉네임
    
    private LocalDateTime createdAt; // 가입일
}
