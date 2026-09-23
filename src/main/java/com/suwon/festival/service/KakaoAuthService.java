package com.suwon.festival.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.suwon.festival.dto.kakao.KakaoTokenResponse;
import com.suwon.festival.dto.kakao.KakaoUserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

  private final RestTemplate restTemplate;

  @Value("${kakao.rest-api-key}")
  private String restApiKey;

  @Value("${kakao.redirect-uri}")
  private String redirectUri;

  @Value("${kakao.client-secret}")
  private String clientSecret;

  public String getAccessToken(String code) {
    String url = "https://kauth.kakao.com/oauth/token";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", restApiKey);
    params.add("redirect_uri", redirectUri);
    params.add("code", code);
    params.add("client_secret", clientSecret);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

    KakaoTokenResponse response = restTemplate.postForObject(url, request, KakaoTokenResponse.class);
    return response.access_token;
  }

  public KakaoUserResponse getUserInfo(String accessToken) {
    String url = "https://kapi.kakao.com/v2/user/me";

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + accessToken);

    HttpEntity<Void> request = new HttpEntity<>(headers);

    return restTemplate.exchange(url, HttpMethod.GET, request, KakaoUserResponse.class).getBody();
  }
}