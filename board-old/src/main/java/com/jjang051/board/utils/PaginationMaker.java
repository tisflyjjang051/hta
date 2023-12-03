package com.jjang051.board.utils;

import com.jjang051.board.dto.Criteria;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Component
@Slf4j
public class PaginationMaker {
    // 이 클래스는 아래쪽 pagination에 관련된 것
    private Criteria criteria;  // 기준 체조 대형으로 벌려 하나 둘 셋
    private int total;          // 전체 갯수
    private int startPage;      // 시작 페이지
    private int endPage;        // 마지막 페이지
    private int pageBlock = 5; //    1/2/3/4/5
    private boolean isPrev;     // 이전 페이지
    private boolean isNext;     //다음 페이지
    private int count;

    //  10   123  13
    // 전체 페이지 갯수를 정하는 순간 pagination 만들기....
    public void setTotal(int total) {
        this.total = total;
        log.info("total===={}",total);
        makePagination();
    }
    public int getLastPage() {
        return (int) Math.ceil( total / (double) criteria.getPageSize());
    }

    private void makePagination() {

        //  123 / 10
        //  10, 20, 30, 40
        //  13
        endPage = (int) Math.ceil( (criteria.getCurrentPage() / (double) pageBlock)  ) * pageBlock;
        startPage = (endPage - pageBlock) + 1;
        count = total-(criteria.getCurrentPage() - 1)*criteria.getPageSize();
        int lastPage = getLastPage();
        log.info("lastPage==={}",lastPage);
        if(endPage > lastPage) endPage = lastPage;
        isPrev = startPage==1 ? false:true;
        isNext = lastPage > endPage? true: false;
        // 90 - (1-1)*10   90 - 1*10
    }
}
