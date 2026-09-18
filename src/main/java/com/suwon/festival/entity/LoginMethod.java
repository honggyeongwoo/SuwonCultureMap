package com.suwon.festival.entity;

// [수정] import java.security.Provider; → 삭제
// java.security.Provider는 JDK 보안 API 클래스(추상 클래스)이고 enum이 아니라서
// 아래 @Enumerated(EnumType.STRING) 자체가 컴파일 에러가 남
// 같은 패키지(com.suwon.festival.entity)에 직접 만든 Provider enum을 사용해야 함
// (같은 패키지라 import 문 자체가 필요 없음 — 파일만 추가하면 됨)

// [삭제] import org.hibernate.annotations.ManyToAny;
// 사용하지 않는 import. @ManyToOne과 이름이 비슷해서 자동완성으로 잘못 추가된 것으로 보임

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// id
// provider
// providerId
// email
// password
// member_id

@Entity 
@Table(name = "login_method", 
        uniqueConstraints = @UniqueConstraint(columnNames = { "provider", "provider_id" }))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginMethod {

  @Id 
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  @Column (nullable = false)
  private String provider; 

  @Column (name = "provider_id", nullable = false)
  private String providerId;

  @Column 
  private String email;

  @Column 
  private String password;

  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn (name = "member_id")
  private Member member;

  @Builder 
  public LoginMethod(String provider, String providerId, String email, String password, Member member) {
    this.provider = provider;
    this.providerId = providerId;
    this.email = email;
    this.password = password;
    this.member = member;
  }}
