package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.exception.DataNotFoundException;
import com.jjang051.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    // 얘가 db에 왔다 갔다 하는 애....
    private final BoardRepository boardRepository;

    public void insertBoard(Board02 board02){
        Board02 board = boardRepository.save(board02);
        BoardDto.fromEntity(board);
    }


/*
    public Page<BoardDto> getAllBoard(int page) {
//        Pageable pageable = PageRequest.of(page,10);
//        Page<Board02> pages = boardRepository.findAll(pageable);
//        Page<BoardDto> boardList = pages.map(BoardDto::fromEntity);
//        log.info("pageable==={}",pageable.getPageSize());
//        return  boardList;
        Pageable pageable = PageRequest.of(page,10);
        //Page<BoardDto> boardList = boardRepository.findAll(pageable).map(BoardDto::fromEntity);
        Page<BoardDto> boardList = boardRepository.findAll(pageable).map(item-> BoardDto.fromEntity(item));
        log.info("pageable==={}",pageable.getPageSize());
        return  boardList;
    }
*/

    public Page<BoardDto> getAllBoard(Pageable pageable) {
        //Page<BoardDto> boardList = boardRepository.findAll(pageable).map(BoardDto::fromEntity);
        Page<BoardDto> boardList = boardRepository.findAll(pageable).map(item-> BoardDto.fromEntity(item));
        return  boardList;
    }

/*    public List<BoardDto> getAllBoard() {
        List<BoardDto> boardList = boardRepository.findAll().stream().map(BoardDto::fromEntity).collect(Collectors.toList());
        return boardList;
    }*/

    // Optional
    public BoardDto getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id);
        if(board.isPresent()) {
            return BoardDto.fromEntity(board.get());
        }
        throw new DataNotFoundException("찾는 거 없음");
        //return null;
    }
}
