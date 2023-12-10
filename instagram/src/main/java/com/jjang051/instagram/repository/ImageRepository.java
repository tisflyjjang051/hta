package com.jjang051.instagram.repository;


import com.jjang051.instagram.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {}
