package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.dto.CustomUserDetails;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    @Value("${pagination.size}")
    private int paginationSize;

    private final BoardService boardService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }
    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }

    @PostMapping("/insert")
    public String insertProcess(@ModelAttribute BoardDto boardDto,
                                @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Board02 dbInsertBoard = Board02.builder()
                .writer(customUserDetails.getLoggedMember())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
        boardService.insertBoard(dbInsertBoard);
        return "redirect:/board/list02";
    }

    /*@GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> boardList =boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/board/list";
    }*/


//    @GetMapping("/list")
//    public String list02(Model model, @RequestParam(value = "page", required = true, defaultValue = "0") int page) {
//        Page<Board02> boardList = boardService.getAllPageBoard(page);
//        model.addAttribute("boardList",boardList);
//        return "/board/list";
//    }

    /*@GetMapping("/list02")
    public String list02(Model model) {
        List<Board02> boardList = boardService.getAllBoardDsl();
        model.addAttribute("boardList",boardList);
        return "/board/listdsl";
    }*/

    @GetMapping("/list02")
    public String pageList(Model model,
                           @RequestParam(value="page", required = true, defaultValue = "0") int page) {
        Page<Board02> pagination = boardService.getAllPageBoardDsl(page);
        log.info("pageBoardList.getTotalPages()==={}",pagination.getTotalPages());
        log.info(pagination.toString());

        List<Board02> boardList = pagination.getContent();
        int start = (int)(Math.floor((double) pagination.getNumber() / paginationSize)*paginationSize);
        int end =  start + paginationSize;

        log.info("start==={},end==={}",start,end);
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        model.addAttribute("boardList",boardList);
        model.addAttribute("pagination",pagination);

        return "/board/list";
    }


    /*@GetMapping("/list02")
    public String pageList(Model model,
                           @RequestParam(value="page", required = true, defaultValue = "0") int page) {
        Page<Board02> pagination = boardService.getAllPageBoard(page);
        log.info("pageBoardList.getTotalPages()==={}",pagination.getTotalPages());
        log.info(pagination.toString());



        List<Board02> boardList = pagination.getContent();
        int start = (int)(Math.floor((double) pagination.getNumber() / paginationSize)*paginationSize);
        int end =  start + paginationSize;

        log.info("start==={},end==={}",start,end);
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        model.addAttribute("boardList",boardList);
        model.addAttribute("pagination",pagination);

        return "/board/list";
    }*/

    @GetMapping("/search")
    public String pageSearchList(Model model,
                           @RequestParam String category,
                           @RequestParam String keyword,
                           @RequestParam(value="page", required = true, defaultValue = "0") int page) {
        Page<Board02> pagination = boardService.getSearchBoardDsl(category,keyword,page);

        List<Board02> boardList = pagination.getContent();
        int start = (int)(Math.floor((double) pagination.getNumber() / paginationSize)*paginationSize);
        int end =  start + paginationSize;

        log.info("start==={},end==={}",start,end);
        model.addAttribute("start",start);
        model.addAttribute("end",end);
        model.addAttribute("boardList",boardList);
        model.addAttribute("pagination",pagination);

        return "/board/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        log.info("id==={}",id);
        //Board02 board = boardService.getBoard(id);
        Board02 board = boardService.getBoardDsl(id);
        log.info("commentList==={}",board.getCommentList().size());
        model.addAttribute("board",board);
        return "/board/view";
    }
}
