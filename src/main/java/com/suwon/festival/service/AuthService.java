package com.suwon.festival.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwon.festival.dto.kakao.KakaoUserResponse;
import com.suwon.festival.entity.LoginMethod;
import com.suwon.festival.entity.Member;
import com.suwon.festival.repository.LoginMethodRepository;
import com.suwon.festival.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final LoginMethodRepository loginMethodRepository;
  private final MemberRepository memberRepository;
  private final KakaoAuthService kakaoAuthService;

  @Transactional
  public Member kakaoLogin(String code) {
    String accessToken = kakaoAuthService.getAccessToken(code);
    KakaoUserResponse kakaoUser = kakaoAuthService.getUserInfo(accessToken);

    String provider = "kakao";
    String providerId = String.valueOf(kakaoUser.id);

    return loginMethodRepository.findByProviderAndProviderId(provider, providerId)
        .map(LoginMethod::getMember)
        .orElseGet(() -> createNewKakaoMember(provider, providerId, kakaoUser.kakao_account.email));
  }

  private Member createNewKakaoMember(String provider, String providerId, String email) {
    Member member = Member.builder()
        .nickname("kakao_" + providerId)
        .build();
    memberRepository.save(member);

    LoginMethod loginMethod = LoginMethod.builder()
        .provider(provider)
        .providerId(providerId)
        .email(email)
        .password(null)
        .member(member)
        .build();
    loginMethodRepository.save(loginMethod);

    return member;
  }
}