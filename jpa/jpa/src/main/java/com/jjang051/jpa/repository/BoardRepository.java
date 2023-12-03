package com.jjang051.jpa.repository;


import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface BoardRepository  extends JpaRepository<Board02,Integer> {
    // crud
    // insert == save
    // select == find
    // update == save
    // delete == delete

    // save , findBy
    // findByEmail
    Page<Board02> findAll(Pageable pageable);
}
