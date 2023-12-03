package com.jjang051.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileRequestDto {
    private Long id;                // 파일 번호 (PK)
    private Long boardId;            // 게시글 번호 (FK)
    private String original;    // 원본 파일명
    private String renamed;        // 저장 파일명
    private long fileSize;              // 파일 크기

    @Builder
    public FileRequestDto(String original, String renamed, long fileSize) {
        this.original = original;
        this.renamed = renamed;
        this.fileSize = fileSize;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
