package com.suwon.festival.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suwon.festival.dto.MemberSignupRequest;
import com.suwon.festival.entity.Member;
import com.suwon.festival.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequest request) {
        Member member = memberService.signup(request.nickname());

        return ResponseEntity.ok("회원가입 완료 : " + member.getNickname());
    }
}
