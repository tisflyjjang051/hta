package com.jjang051.jpa.repository;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("select b from Board02 b where b.subject like %:keyword%")
    Page<Board02> findBySubject(@Param("keyword") String keyword,Pageable pageable);

    @Query("select b from Board02 b where b.content like %:keyword%")
    Page<Board02> findByContent(@Param("keyword") String keyword,Pageable pageable);

    @Query("select b from Board02 b where b.writer.nickName like %:keyword%")
    Page<Board02> findByWriter(@Param("keyword") String keyword,Pageable pageable);




    @Query(value = "select b from Board02 b where " +
            "b.writer.nickName like %:keyword% or " +
            "b.subject like %:keyword%  or " +
            "b.content like %:keyword%")
    Page<Board02> fingByAllCategory(@Param("keyword") String keyword,Pageable pageable);





    // select를 제외한 나머지는 반드시(dml) @Modifying을 붙여야 한다.
    /*@Modifying
    @Query(value = "insert into  Board02", nativeQuery = true)
    Page<Board02> insertBoard(@Param("keyword") String keyword,Pageable pageable);*/

    @Query(value = "select * from Board02 b where b.subject like %:keyword%", nativeQuery = true)
    Page<Board02> findBySubjectNative(@Param("keyword") String keyword,Pageable pageable);

    @Query(value = "select * from Board02 b where b.writer like %:keyword%", nativeQuery = true)
    Page<Board02> findByWriterNative(@Param("keyword") String keyword,Pageable pageable);

    @Query(value = "select * from Board02 b where b.content like %:keyword%", nativeQuery = true)
    Page<Board02> findByContentNative(@Param("keyword") String keyword,Pageable pageable);




}






