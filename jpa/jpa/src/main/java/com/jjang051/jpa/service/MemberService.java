package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member02;
import com.jjang051.jpa.exception.NotFoundMember;
import com.jjang051.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    public Member02 join(MemberDto memberDto) {
                Member02 dbJoinMember = Member02.builder()
                        .userId(memberDto.getUserId())
                        .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                        .role("ROLE_USER")
                        .email(memberDto.getEmail())
                        .nickName(memberDto.getNickName())
                        .age(memberDto.getAge())
                        .build();
        Member02 responseMember = memberRepository.save(dbJoinMember);
        return responseMember;
    }

    public List<Member02> getAllMember() {
        List<Member02> memberList = memberRepository.findAll();
        return memberList;
    }

    public Member02 getMemberInfo(String id) {
        Optional<Member02> member = memberRepository.findByUserId(id);
        if(member.isPresent()) {
            return member.get();
        }
        throw new NotFoundMember("찾을 수 없는 아이디입니다.");
    }

    @Transactional
    public void modifyMember(MemberDto memberDto) {
        log.info("memberDto.getUserId()==="+memberDto.getUserId());
        //Optional<Member02> member = memberRepository.findByUserId(memberDto.getUserId());
        Member02 member = memberRepository.findByUserId(memberDto.getUserId()).orElseThrow(()->new RuntimeException("없음"));
        log.info("member.getUserId()==="+member.getUserId());
        member.update(memberDto.getRole(),memberDto.getNickName(),memberDto.getEmail(),memberDto.getAge());
        //memberRepository.save(updateMember);
            //return memberRepository.save(updateMember);

    }

    public void deleteMember(int id) {
        Optional<Member02> member = memberRepository.findById(id);
        if(member.isPresent()) {
            memberRepository.delete(member.get());
        }
        throw new NotFoundMember("찾을 수 없는 아이디입니다.");
    }
}
