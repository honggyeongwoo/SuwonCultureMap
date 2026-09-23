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

import java.util.HashMap;
import java.util.Map;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @GetMapping("/kakao/callback")
  public ResponseEntity<Void> kakaoCallback(@RequestParam String code, HttpSession session) {
    Member member = authService.kakaoLogin(code);
    session.setAttribute("memberId", member.getId());
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
  }

  // 구글 콜백
  @GetMapping("/google/callback")
  public ResponseEntity<Void> googleCallback(@RequestParam String code, HttpSession session) {
    Member member = authService.googleLogin(code);
    session.setAttribute("memberId", member.getId());
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
  }

  // 네이버 콜백
  @GetMapping("/naver/callback")
  public ResponseEntity<Void> naverCallback(@RequestParam String code, @RequestParam String state,
      HttpSession session) {
    Member member = authService.naverLogin(code, state);
    session.setAttribute("memberId", member.getId());
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
  }

  @GetMapping(value = "/me", produces = "application/json")
  public Map<String, Object> me(HttpSession session) {
    Long memberId = (Long) session.getAttribute("memberId");
    Map<String, Object> result = new HashMap<>();
    result.put("loggedIn", memberId != null);
    result.put("memberId", memberId);
    return result;
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "로그아웃 완료";
  }

}