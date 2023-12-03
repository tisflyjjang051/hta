package com.jjang051.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
    private int currentPage;
    private int pageSize; // 세로....
    private String category;
    private String searchTxt;

    public Criteria() {
        this.currentPage = 1;  //  pagination 에 클릭한 번호
        this.pageSize = 7;    // 세로로 보여지는 글갯수
    }
    public int getStartPage() {
        return (currentPage - 1) * pageSize+1;   // 1,11,21,31,41~~~~
    }
    public int getEndPage() {
        return currentPage*pageSize;            // 10,20,30,40,50
    }
}
