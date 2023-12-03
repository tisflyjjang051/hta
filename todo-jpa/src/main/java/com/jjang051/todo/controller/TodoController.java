package com.jjang051.todo.controller;

import com.jjang051.todo.dto.TodoDto;
import com.jjang051.todo.entity.TodoEntity;
import com.jjang051.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    @GetMapping({"/","/index"})
    public String index() {
        return "/todo/index";
    }



    @PostMapping("/insert")
    @ResponseBody
    public void insertTodo(@ModelAttribute TodoEntity todoEntity) {
        todoService.insertTodo(todoEntity);
    }

    /*@PostMapping("/list")
    @ResponseBody
    public List<TodoDto> getPickedDateTodo(@ModelAttribute TodoDto todoDto) {
        List<TodoDto> todolist = todoService.getPickedDateTodo(todoDto);
        return todolist;
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public List<TodoDto>  deleteTodo(@ModelAttribute TodoDto todoDto) {
        log.info(todoDto.toString());
        List<TodoDto> todolist = todoService.deleteTodo(todoDto);
        return todolist;
    }*/
}
