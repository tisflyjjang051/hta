package com.jjang051.board.dao;

import com.jjang051.board.dto.JoinDto;
import com.jjang051.board.dto.LoginDto;
import com.jjang051.board.dto.UpdateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int insertMember(JoinDto joinDto);
    JoinDto loginMember(String username);
    int deleteMember(LoginDto loginDto);

    int updateMember(JoinDto joinDto);
    int insertDeleteMember(JoinDto joinDto);
    int updatePassword(UpdateDto updateDto);
    int duplicateEmail(String email);

}
