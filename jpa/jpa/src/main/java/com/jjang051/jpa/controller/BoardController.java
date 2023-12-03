package com.jjang051.jpa.controller;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String insertProcess(@ModelAttribute BoardDto boardDto) {
        Board02 dbInsertBoard = Board02.builder()
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(LocalDateTime.now())
                .build();
        boardService.insertBoard(dbInsertBoard);
        return "redirect:/board/list";
    }

/*
    @GetMapping("/list")
    public String list(Model model,) {
        List<BoardDto> boardList =boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/board/list";
    }
*/


    /*@GetMapping("/list")
    //@ResponseBody
    public String list(Model model, @RequestParam(value = "page", required = true, defaultValue = "0") int page) {
        Page<BoardDto> pagingBoardList = boardService.getAllBoard(page);
        model.addAttribute("pagingBoardList",pagingBoardList);
        return "/board/list";
    }
*/
    @GetMapping("/list")
    //@ResponseBody
    public String list(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        int paginationSize = 10;
        Page<BoardDto> page = boardService.getAllBoard(pageable);

        long totalCount = page.getTotalElements();
        long totalPage = page.getTotalPages();
        List<BoardDto> pagingBoardList = page.getContent();

        int currentPage = page.getPageable().getPageNumber();
        int startPage = (int)(Math.floor((double)currentPage/paginationSize)*paginationSize+1);
        int endPage = (int)Math.max(startPage+paginationSize-1,totalPage);
        log.info("currentPage==={},startPage====={},endPage===={},totalPage==={},totalCount==={}", currentPage,startPage,endPage,totalPage,totalCount);

        model.addAttribute("pagingBoardList",pagingBoardList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        return "/board/list";
    }



    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        log.info("id==={}",id);
        BoardDto board = boardService.getBoard(id);
        log.info("commentList==={}",board.getCommentList().size());
        model.addAttribute("board",board);
        return "/board/view";
    }
}
