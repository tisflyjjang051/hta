package com.jjang051.board.dao;

import com.jjang051.board.dto.FileRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    void saveAll(List<FileRequestDto> files);

}
