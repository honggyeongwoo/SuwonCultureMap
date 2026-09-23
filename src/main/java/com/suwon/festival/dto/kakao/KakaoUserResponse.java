package com.suwon.festival.dto.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserResponse {
  public Long id;
  public KakaoAccount kakao_account;
}