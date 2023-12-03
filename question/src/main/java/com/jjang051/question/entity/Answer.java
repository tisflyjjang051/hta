package com.jjang051.question.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(columnDefinition = "varchar2(2000)")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question;
}
