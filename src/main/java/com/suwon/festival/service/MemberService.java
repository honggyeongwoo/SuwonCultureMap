package com.suwon.festival.service;

import org.springframework.stereotype.Service;

import com.suwon.festival.entity.Member;
import com.suwon.festival.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member signup(String nickname) {

        // 닉네임 중ㅇ복 확인
        if (memberRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
        }

        // 새 member 만들어서 저장
        Member member = Member.builder()
                .nickname(nickname)
                .build();

        return memberRepository.save(member);
    }
}
