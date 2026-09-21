package com.suwon.festival.dto.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverUserResponse {
    public NaverAccount response;
}