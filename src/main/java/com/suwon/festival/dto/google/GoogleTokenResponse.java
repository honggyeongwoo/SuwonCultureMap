package com.suwon.festival.dto.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleTokenResponse {
    public String access_token;
}