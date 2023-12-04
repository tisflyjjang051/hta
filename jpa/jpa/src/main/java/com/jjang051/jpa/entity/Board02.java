package com.jjang051.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="board02")
public class Board02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="boardId")
    private Integer id;

    //@Column(name = "mySubject")
    private String subject;

    @Column(columnDefinition = "varchar2(1500)")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board02", cascade = CascadeType.REMOVE)
    private List<Comment02> commentList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member02 writer;
}



