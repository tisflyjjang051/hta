package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.Comment02;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void insertComment(Comment02 comment02) {
        commentRepository.save(comment02);
    }

    public Comment02 insertAjaxComment(Board02 board02, String content) {
        Comment02 comment = Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .board02(board02)
                .build();
        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
