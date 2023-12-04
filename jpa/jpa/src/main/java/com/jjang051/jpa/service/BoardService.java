package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.BoardDto;
import com.jjang051.jpa.entity.Board02;
import com.jjang051.jpa.entity.QBoard02;
import com.jjang051.jpa.exception.DataNotFoundException;
import com.jjang051.jpa.repository.BoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    // 얘가 db에 왔다 갔다 하는 애....
    private final BoardRepository boardRepository;


    @Autowired
    private JPAQueryFactory queryFactory;

//    @Autowired
//    private EntityManager em;

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
        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<Board02> boardList = boardRepository.findAll(pageable);
        return boardList;
    }


    // Optional
    public Board02 getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id);
        if(board.isPresent()) {
            //return BoardDto.fromEntity(board.get());
            return board.get();
        }
        throw new DataNotFoundException("찾는 거 없음");
        //return null;
    }

//    public List<Board02> getAllSearchBoard(String search) {
//        List<Board02> boardList = boardRepository.findBySubject(search);
//        return boardList;
//    }

    public Page<Board02> getAllSearchPageBoard(int page,String search) {
        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<Board02> boardList = boardRepository.findBySubject(search,pageable);
        return boardList;
    }


    public Page<Board02> getAllSearchPageBoardQueryDsl(int page,String search) {
        //JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QBoard02 qBoard = QBoard02.board02;


        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "createDate"));
        List<Board02> boardList = queryFactory
                .select(qBoard)
                .from(qBoard)
                .where(
                    //qBoard.subject.like("%"+search+"%")
                        qBoard.writer.nickName.contains(search)
                                .or(qBoard.writer.nickName.contains(search))
                                .or(qBoard.content.contains(search))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
        .orderBy(qBoard.createDate.desc())
        .fetch();

        Long count = queryFactory.select(qBoard.count())
                .from(qBoard)
                .where(qBoard.subject.like("%"+search+"%"))
                .fetchOne();

        return new PageImpl<>(boardList,pageable,count);
    }
}
