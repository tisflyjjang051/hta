package com.jjang051.jpa.service;

import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.Comment02;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void insertComment(Board02 board02, String content) {
        Comment02 comment = Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .board02(board02)
                .build();
        commentRepository.save(comment);
    }

    public Comment02 insertAjaxComment(Board02 board02, String content, Member02 member02) {
        Comment02 comment = Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .writer(member02)
                .board02(board02)
                .build();
        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
