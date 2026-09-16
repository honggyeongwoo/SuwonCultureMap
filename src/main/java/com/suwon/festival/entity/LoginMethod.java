package com.suwon.festival.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

// id
// provider
// providerId
// email
// password
// member_id
public class LoginMethod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 유니크, 자동 증가는 안하나?
  private Long id;

  @ManyToAny(fetch = FetchType.LAZY) // ????
  @JoinColumn(name = "member_id") // FK - DB에 생성되는 컬럼 이름 부여 (Table = name 같이)
  private Member member; // Member 엔티티의 객체 참조
}
