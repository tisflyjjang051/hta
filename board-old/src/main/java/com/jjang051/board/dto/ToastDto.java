package com.jjang051.board.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToastDto {
    private String isState;
    private String title;
    private String msg;
}
