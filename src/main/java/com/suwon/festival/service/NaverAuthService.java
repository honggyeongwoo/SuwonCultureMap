package com.suwon.festival.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.suwon.festival.dto.naver.NaverAccount;
import com.suwon.festival.dto.naver.NaverTokenResponse;
import com.suwon.festival.dto.naver.NaverUserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NaverAuthService {

    private final RestTemplate restTemplate;

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    public String getAccessToken(String code, String state) {
        String url = "https://nid.naver.com/oauth2.0/token"
                + "?grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code
                + "&state=" + state;

        NaverTokenResponse response = restTemplate.getForObject(url, NaverTokenResponse.class);
        return response.access_token;
    }

    public NaverAccount getUserInfo(String accessToken) {
        String url = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        NaverUserResponse response = restTemplate.exchange(url, HttpMethod.GET, request, NaverUserResponse.class)
                .getBody();
        return response.response;
    }
}