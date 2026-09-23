package com.suwon.festival.dto.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoTokenResponse {
  public String access_token;
  public String token_type;
  public String refresh_token;
}