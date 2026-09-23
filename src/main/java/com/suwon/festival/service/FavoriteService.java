package com.suwon.festival.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.suwon.festival.dto.EventResponse;
import com.suwon.festival.entity.Event;
import com.suwon.festival.entity.Favorite;
import com.suwon.festival.entity.Member;
import com.suwon.festival.repository.EventRepository;
import com.suwon.festival.repository.FavoriteRepository;
import com.suwon.festival.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

  private final FavoriteRepository favoriteRepository;
  private final MemberRepository memberRepository;
  private final EventRepository eventRepository;

  public void addFavorite(Long memberId, Long eventId) {
    if (favoriteRepository.existsByMemberIdAndEventId(memberId, eventId)) {
      throw new IllegalArgumentException("이미 즐겨찾기한 행사입니다.");
    }

    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행사입니다."));

    Favorite favorite = Favorite.builder()
        .member(member)
        .event(event)
        .createdAt(LocalDateTime.now())
        .build();

    favoriteRepository.save(favorite);
  }

  public void removeFavorite(Long memberId, Long eventId) {
    Favorite favorite = favoriteRepository.findByMemberIdAndEventId(memberId, eventId)
        .orElseThrow(() -> new IllegalArgumentException("즐겨찾기하지 않은 행사입니다."));

    favoriteRepository.delete(favorite);
  }

  public List<EventResponse> getFavoriteEvents(Long memberId) {
    return favoriteRepository.findByMemberId(memberId).stream()
        .map(Favorite::getEvent)
        .map(event -> new EventResponse(
            event.getId(),
            event.getTitle(),
            event.getCategory(),
            event.getLatitude(),
            event.getLongitude(),
            event.getImageUrl(),
            event.getInfo(),
            event.getPubDate(),
            event.getStartDate(),
            event.getEndDate()))
        .collect(Collectors.toList());
  }
}