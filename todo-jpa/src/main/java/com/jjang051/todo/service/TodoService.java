package com.jjang051.todo.service;

import com.jjang051.todo.dao.TodoDao;
import com.jjang051.todo.dto.TodoDto;
import com.jjang051.todo.entity.TodoEntity;
import com.jjang051.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {
    //private final TodoDao todoDao;
    private final TodoRepository todoRepository;


    @Transactional
    public void insertTodo(TodoEntity todoEntity) {
        todoRepository.save(todoEntity);
    }

    /*@Transactional
    public List<TodoDto> getAllList() {
        List<TodoDto> todoList = todoDao.getAllList();
        return todoList;
    }
    @Transactional
    public List<TodoDto> getPickedDateTodo(TodoDto todoDto) {
          = todoRepository.findByDate(todoDto.getPickedDate());
        return todoList;
    }
    @Transactional
    public List<TodoDto> deleteTodo(TodoDto todoDto) {
        int result  = todoDao.deleteTodo(todoDto);
        List<TodoDto> todoList = getPickedDateTodo(todoDto);
        log.info("todoList==={}",todoList.size());
        return todoList;
    }*/




}
