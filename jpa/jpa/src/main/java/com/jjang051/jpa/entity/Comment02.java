package com.jjang051.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 2000)
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board02 board02;

}





