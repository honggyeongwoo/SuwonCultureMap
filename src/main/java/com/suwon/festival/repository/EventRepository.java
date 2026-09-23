package com.suwon.festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suwon.festival.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
  boolean existsByIdx(Long idx);
}
