package com.jjang051.jpa.controller;


import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.service.BoardService;
import com.jjang051.jpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/insert/{id}")
    public String insert(@PathVariable("id") int id, @RequestParam String content) {
        BoardDto boardDto = boardService.getBoard(id);
        Board02 board02 = Board02.builder()
                .id(boardDto.getId())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate())
                .commentList(boardDto.getCommentList())
                .subject(boardDto.getSubject())
                .build();
        commentService.insertComment(board02,content);
        return "redirect:/board/view/"+id;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String,String> delete(@PathVariable("id") int id) {
        commentService.deleteComment(id);
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("isDelete","ok");
        return resultMap;
    }
}
