package com.suwon.festival.dto;

import java.time.LocalDateTime;

public record EventResponse(
    Long id,
    String title,
    String category,
    Double latitude,
    Double longitude,
    String imageUrl,
    String info,
    LocalDateTime pubDate) {
}