package com.jjang051.board.service;

import com.jjang051.board.dao.FileMapper;
import com.jjang051.board.dto.FileRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;

    @Transactional
    public void saveFiles(final Long boardId, final List<FileRequestDto> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequestDto file : files) {
            file.setBoardId(boardId);
        }
        fileMapper.saveAll(files);
    }
}
