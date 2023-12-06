package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.Comment02;
import com.jjang051.jpa.entity.Member02;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {
    private Integer id;

    private String subject;

    private String content;

    private LocalDateTime createDate;
    private List<Comment02> commentList;

    public static BoardDto fromEntity(Board02 board) {
        return BoardDto.builder()
                .id(board.getId())
                .subject(board.getSubject())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .commentList(board.getCommentList())
                .build();
    }
}
