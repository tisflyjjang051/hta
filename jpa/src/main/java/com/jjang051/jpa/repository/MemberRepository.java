package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Member02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member02,Integer> {

    List<Member02> findByNickName(String nickName);
    List<Member02> findByNickNameOrUserId(String nickName, String userId);
    List<Member02> findByAgeGreaterThanOrderByAgeDesc(int age);

    Optional<Member02> findByUserId(String userId);
}
