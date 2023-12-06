package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Comment02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment02,Integer> {
}
