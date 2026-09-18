package com.suwon.festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suwon.festival.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    
}
