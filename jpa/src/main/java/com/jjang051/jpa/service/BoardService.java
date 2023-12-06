package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.QBoard02;
import com.jjang051.jpa.exception.DataNotFoundException;
import com.jjang051.jpa.repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    // 얘가 db에 왔다 갔다 하는 애....
    private final BoardRepository boardRepository;
    private final JPAQueryFactory queryFactory;

    public BoardDto insertBoard(Board02 board02){
        Board02 board = boardRepository.save(board02);
        return BoardDto.fromEntity(board);
    }


//    public Page<Board02> getAllPageBoard(int page) {
//        return  boardRepository.findAll(pageable);
//    }

    public List<Board02> getAllBoard() {
        List<Board02> boardList = boardRepository.findAll();
        return boardList;
    }

    public Page<Board02> getAllPageBoard(int page) {
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));
        Page<Board02> boardList = boardRepository.findAll(pageable);
        return boardList;
    }


    // Optional
    public Board02 getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id);
        if(board.isPresent()) {
            return board.get();
        }
        throw new DataNotFoundException("찾는 거 없음");
        //return null;
    }


    public Page<Board02> getSearchBoard(String category,String keyword, int page) {
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));
        if(category.equals("subject")) {
            Page<Board02> boardList = boardRepository.findBySubject(keyword, pageable);
            return boardList;
        } else if(category.equals("content")) {
            Page<Board02> boardList = boardRepository.findByContent(keyword, pageable);
            return boardList;
        }else if(category.equals("writer")) {
            Page<Board02> boardList = boardRepository.findByWriter(keyword, pageable);
            return boardList;
        } else {
            log.info("전체검색");
            Page<Board02> boardList = boardRepository.fingByAllCategory(keyword, pageable);
            return boardList;
        }
        //throw new RuntimeException("검색 결과가 없습니다.");
    }

    public Board02 getBoardDsl(int id) {
        QBoard02 qBoard = QBoard02.board02;
        Board02 selectedBoard02 =
                queryFactory.select(qBoard).from(qBoard).where(qBoard.id.eq(id)).fetchOne();
        return selectedBoard02;
    }

    public List<Board02> getAllBoardDsl() {
        QBoard02 qBoard = QBoard02.board02;
        List<Board02> boardList = queryFactory.select(qBoard).from(qBoard).fetch();
        return boardList;
    }

    public Page<Board02> getAllPageBoardDsl(int page) {
        QBoard02 qBoard = QBoard02.board02;
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));
        List<Board02> boardList =
                queryFactory
                .select(qBoard)
                .from(qBoard)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(qBoard.count()).from(qBoard).fetchOne();

        return new PageImpl<>(boardList,pageable,total);
    }




    public Page<Board02> getSearchBoardDsl(String category,String keyword, int page) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();


        QBoard02 qBoard = QBoard02.board02;
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));

        //BooleanBuilder 참 거짓 만들어 주는 builder역할을 함....

        if(StringUtils.equals(category,"subject")) {
            log.info("subject 검색");
            booleanBuilder.or(qBoard.subject.contains(keyword));
        }
        if(StringUtils.equals(category,"writer")) {
            log.info("writer 검색");
            booleanBuilder.or(qBoard.writer.nickName.contains(keyword));
        }
        if(StringUtils.equals(category,"content")) {
            log.info("content 검색");
            booleanBuilder.or(qBoard.content.contains(keyword));
        }
        if(StringUtils.equals(category,"all")) {
            log.info("all 검색");
            booleanBuilder.or(qBoard.content.contains(keyword))
                    .or(qBoard.writer.nickName.contains(keyword))
                    .or(qBoard.subject.contains(keyword));
        }

        List<Board02> boardList =
                queryFactory.selectFrom(qBoard)
                //.where(qBoard.subject.contains(keyword))
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = queryFactory.select(qBoard.count()).from(qBoard).fetchOne();
        return new PageImpl<>(boardList,pageable,total);
        //throw new RuntimeException("검색 결과가 없습니다.");
    }
}
