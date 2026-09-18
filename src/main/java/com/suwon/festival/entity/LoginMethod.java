package com.suwon.festival.entity;

// TODO: import 잘못됨. org.hibernate.annotations.ManyToAny 는 @ManyToOne이랑 다른 어노테이션임
// jakarta.persistence.ManyToOne 으로 교체하고, 아래 23번 줄도 @ManyToOne(fetch = FetchType.LAZY)로 수정
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

// TODO: 클래스 위에 어노테이션이 하나도 없음. 이대로면 JPA가 엔티티로 인식 못함
// @Entity, @Table(name = "login_method") 추가 필요
// (provider, providerId) 조합 unique 제약도 Table 안에 uniqueConstraints로 걸어야 함 (가이드 문서 참고)
// @Getter, @NoArgsConstructor(access = AccessLevel.PROTECTED) 도 Member처럼 빠짐 - 추가 필요
public class LoginMethod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 유니크, 자동 증가는 안하나?
  private Long id;

  // TODO: 필드 4개가 아직 없음 - 아래 다 추가해야 함
  // provider (String or Enum - 이번 프로젝트엔 java.security.Provider 말고 직접 만든 Provider enum 써야 함. entity 패키지에 enum Provider { LOCAL, KAKAO, GOOGLE, NAVER } 새로 만들 것)
  // providerId (String, nullable = false)
  // email (String)
  // password (String, local 로그인만 값 있음 - nullable 허용)

  @ManyToAny(fetch = FetchType.LAZY) // ????
  @JoinColumn(name = "member_id") // FK - DB에 생성되는 컬럼 이름 부여 (Table = name 같이)
  private Member member; // Member 엔티티의 객체 참조

  // TODO: @Builder 생성자 없음 - provider, providerId, email, password, member 받는 생성자 추가 필요 (Event/Favorite 예시 참고)
}
