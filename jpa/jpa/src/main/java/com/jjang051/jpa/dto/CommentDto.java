package com.jjang051.jpa.dto;

import com.jjang051.jpa.entity.Board02;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

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

    private Board02 board02;
}
