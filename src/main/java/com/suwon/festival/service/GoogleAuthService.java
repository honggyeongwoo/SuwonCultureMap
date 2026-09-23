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

import com.suwon.festival.dto.google.GoogleTokenResponse;
import com.suwon.festival.dto.google.GoogleUserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final RestTemplate restTemplate;

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    public String getAccessToken(String code) {
        String url = "https://oauth2.googleapis.com/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        GoogleTokenResponse response = restTemplate.postForObject(url, request, GoogleTokenResponse.class);
        return response.access_token;
    }

    public GoogleUserResponse getUserInfo(String accessToken) {
        String url = "https://www.googleapis.com/oauth2/v2/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, request, GoogleUserResponse.class).getBody();
    }
}