package com.suwon.festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suwon.festival.entity.LoginMethod;

public interface LoginMethodRepository extends JpaRepository<LoginMethod, Long>{
    
}
