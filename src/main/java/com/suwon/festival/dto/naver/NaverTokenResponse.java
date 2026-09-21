package com.suwon.festival.dto.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverTokenResponse {
    public String access_token;
}