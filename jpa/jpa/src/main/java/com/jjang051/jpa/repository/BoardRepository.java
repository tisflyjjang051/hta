package com.jjang051.jpa.repository;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository  extends JpaRepository<Board02,Integer> {
    // crud
    // insert == save
    // select == find
    // update == save
    // delete == delete

    // save , findBy
    // findByEmail
    //Page<Board02> findAll(Pageable pageable);

    Page<Board02> findAll(Pageable pageable);

    @Query("select b from Board02 b where b.subject like %:search% order by b.createDate desc")
    Page<Board02> findBySubject(@Param("search") String search,Pageable pageable); // :itemDetail 변수로 전달

    @Query(value="select * from board02 b where b.subject like %:search% order by b.createDate desc", nativeQuery = true)
    Page<Board02> findBySubjectNative(@Param("search") String search,Pageable pageable);




}






