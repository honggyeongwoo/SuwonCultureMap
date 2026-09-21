package com.suwon.festival.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwon.festival.dto.google.GoogleUserResponse;
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
  private final GoogleAuthService googleAuthService;
  private final NaverAuthService naverAuthService;

  // 카카오
  @Transactional
  public Member kakaoLogin(String code) {
    String accessToken = kakaoAuthService.getAccessToken(code);
    KakaoUserResponse kakaoUser = kakaoAuthService.getUserInfo(accessToken);

    String provider = "kakao";
    String providerId = String.valueOf(kakaoUser.id);

    return loginMethodRepository.findByProviderAndProviderId(provider, providerId)
        .map(LoginMethod::getMember)
        .orElseGet(() -> createNewMember(provider, providerId, kakaoUser.kakao_account.email));
  }

  // 구글
  @Transactional
  public Member googleLogin(String code) {
    String accessToken = googleAuthService.getAccessToken(code);
    GoogleUserResponse googleUser = googleAuthService.getUserInfo(accessToken);

    String provider = "google";
    String providerId = googleUser.id;

    return loginMethodRepository.findByProviderAndProviderId(provider, providerId)
        .map(LoginMethod::getMember)
        .orElseGet(() -> createNewMember(provider, providerId, googleUser.email));
  }

  // 네이버
  @Transactional
  public Member naverLogin(String code, String state) {
    String accessToken = naverAuthService.getAccessToken(code, state);
    var naverUser = naverAuthService.getUserInfo(accessToken);

    String provider = "naver";
    String providerId = naverUser.id;

    return loginMethodRepository.findByProviderAndProviderId(provider, providerId)
        .map(LoginMethod::getMember)
        .orElseGet(() -> createNewMember(provider, providerId, naverUser.email));
  }

  private Member createNewMember(String provider, String providerId, String email) {
    Member member = Member.builder()
        .nickname(provider + "_" + providerId)
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