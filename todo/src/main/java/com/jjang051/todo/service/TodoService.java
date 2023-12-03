package com.jjang051.todo.service;

import com.jjang051.todo.dao.TodoDao;
import com.jjang051.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {
    private final TodoDao todoDao;


    @Transactional
    public List<TodoDto> insertTodo(TodoDto todoDto) {
        int result  = todoDao.insertTodo(todoDto);
        List<TodoDto> todoList = getPickedDateTodo(todoDto);
        return todoList;
    }

    @Transactional
    public List<TodoDto> getAllList() {
        List<TodoDto> todoList = todoDao.getAllList();
        return todoList;
    }
    @Transactional
    public List<TodoDto> getPickedDateTodo(TodoDto todoDto) {
        List<TodoDto> todoList  = todoDao.getPickedDateTodo(todoDto);
        return todoList;
    }
    @Transactional
    public List<TodoDto> deleteTodo(TodoDto todoDto) {
        int result  = todoDao.deleteTodo(todoDto);
        List<TodoDto> todoList = getPickedDateTodo(todoDto);
        log.info("todoList==={}",todoList.size());
        return todoList;
    }




}
