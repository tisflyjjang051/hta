package com.jjang051.todo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private int no;
    private String todo;
    private String pickedDate;
    private String isDone;
    private int count;
}
