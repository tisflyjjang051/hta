package com.jjang051.instagram.repository;

import com.jjang051.instagram.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findByUserId(String userId);

}
