package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.Comment02;
import com.jjang051.jpa.entity.Member02;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    private Integer id;

    private String content;

    private LocalDateTime createDate;

    private String strCreateDate;

    private Member02 writer;

    private Board02 board02;
    public static CommentDto fromEntity(Comment02 comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .writer(comment.getWriter())
                .strCreateDate(comment.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
