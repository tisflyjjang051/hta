package com.jjang051.todo.dao;

import com.jjang051.todo.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoDao {
    int insertTodo(TodoDto todoDto);
//    List<TodoDto> getPickedDateTodo(TodoDto todoDto);
//    int deleteTodo(TodoDto todoDto);
//    List<TodoDto> getAllList();
}
