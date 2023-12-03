package com.jjang051.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private String receiver;
    private String title;
    private String content;
}
