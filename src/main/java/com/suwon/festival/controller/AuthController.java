package com.suwon.festival.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suwon.festival.entity.Member;
import com.suwon.festival.service.AuthService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @GetMapping("/kakao/callback")
  public String kakaoCallback(@RequestParam String code, HttpSession session) {
    Member member = authService.kakaoLogin(code);
    session.setAttribute("memberId", member.getId());
    return "카카오 로그인 성공: " + member.getNickname();
  }

  @GetMapping("/me")
  public String me(HttpSession session) {
    Long memberId = (Long) session.getAttribute("memberId");
    if (memberId == null) {
      return "로그인 상태가 아닙니다.";
    }
    return "현재 로그인한 회원 번호: " + memberId;
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "로그아웃 완료";
  }
}