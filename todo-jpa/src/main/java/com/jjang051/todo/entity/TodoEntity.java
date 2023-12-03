package com.jjang051.todo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int no;
    private String todo;
    private String pickedDate;
    private String isDone;
    private int count;
}
