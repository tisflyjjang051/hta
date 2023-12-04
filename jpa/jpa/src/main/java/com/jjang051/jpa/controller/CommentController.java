package com.jjang051.jpa.controller;


import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.dto.CommentDto;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.Comment02;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.service.BoardService;
import com.jjang051.jpa.service.CommentService;
import com.jjang051.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/insert/{id}")
    public String insert(@PathVariable("id") int id, @RequestParam String content, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Board02 board02 = boardService.getBoard(id);
        Member02 member02 = memberService.getMemberInfo(customUserDetails.getLoggedMember().getUserId());
        log.info("customUserDetails.getLoggedMember().getUserId()==={}",customUserDetails.getLoggedMember().getUserId());
        log.info("member02==={}",member02.getUserId());


        Comment02 dbInsertComment = Comment02.builder()
                .writer(member02)
                .content(content)
                .board02(board02)
                .build();

        commentService.insertComment(dbInsertComment);
        return "redirect:/board/view/"+id;
    }


    @PostMapping("/ajaxinsert/{id}")
    @ResponseBody
    public Map<String, Object> ajaxinsert(@PathVariable("id") int id, @RequestParam String content) {
        log.info("id===={},content===={}",id,content);
        Board02 boardDto = boardService.getBoard(id);
        Board02 board02 = Board02.builder()
                .id(boardDto.getId())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate())
                .commentList(boardDto.getCommentList())
                .subject(boardDto.getSubject())
                .build();
        // save된 entity를 리턴받을 수 있다.
        Comment02 comment02 = commentService.insertAjaxComment(board02,content);
        // 데이터를 전달하기 위해 dto로 변환해서
        CommentDto responseComment = CommentDto.fromEntity(comment02);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isInsert","ok");
        // map에 실어서 보낸다.
        resultMap.put("responseComment",responseComment);

//        resultMap.put("content",responseComment.getContent());
//        resultMap.put("id",responseComment.getId().toString());
//        resultMap.put("date",responseComment.getCreateDate().toString());
        return resultMap;
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
