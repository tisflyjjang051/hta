package com.jjang051.jpa.service;

import com.jjang051.jpa.dto.MemberDto;
import com.jjang051.jpa.entity.Member02;
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

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
        //MemberDto responseMemberDto = MemberDto.fromEntity(responseMember);
        return responseMember;
    }

    public List<MemberDto> getAllMember() {
        // stream과 lamda를 쓰면 이걸 간단히 줄일수 있다....
        List<Member02> member02List = memberRepository.findAll();
        List<MemberDto> memberList = new ArrayList<>();
        for(int i=0;i<member02List.size();i++) {
            memberList.add(MemberDto.fromEntity(member02List.get(i)));
        }
        return memberList;
        /*return memberRepository.findAll()
                .stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());*/
        // 반목문 돌려서 dto memberList에 담기...
        //return  memberList;
    }

    public Member02 getMemberInfo(String id) {
        Optional<Member02> memberInfo = memberRepository.findByUserId(id);
        if(memberInfo.isPresent()) {
            return memberInfo.get();
        }
        throw new RuntimeException("찾는 사람이 옶습니다.");
    }

    @Transactional
    public void modifyMember(MemberDto memberDto) {
        log.info(memberDto.toString());
        Optional<Member02> member = memberRepository.findByUserId(memberDto.getUserId());
        // jpa 에 id로 잡힌 컬럼의 이름이 같으면 update를 한다. 아니면 insert
        if(member.isPresent()) {
            member.get().updateMemberInfo(memberDto.getNickName(), memberDto.getEmail(), memberDto.getAge());
            //memberRepository.save(member.get());
            //member.get();
//            Member02 dbInsertMember = Member02.builder()
//                     .id(member.get().getId())
//                     .userId(member.get().getUserId())
//                     .email(memberDto.getEmail())
//                     .role(member.get().getRole())
//                     .age(memberDto.getAge())
//                     .nickName(memberDto.getNickName())
//                     .build();
            //memberRepository.save(dbInsertMember);

//            dbInsertMember.setAge(memberDto.getAge());
//            dbInsertMember.setEmail(memberDto.getEmail());
//            dbInsertMember.setNickName(memberDto.getNickName());
            //memberRepository.save(dbInsertMember);
//            dbInsertMember.builder()
//                    .age(memberDto.getAge())
//                    .email(memberDto.getEmail())
//                    .nickName(memberDto.getNickName())
//                    .build();
        }
        //throw  new RuntimeException("없음");
    }

    public boolean deleteMember(String id) {
//        Optional<Member02> member = memberRepository.findById(id);
//        if(member.isPresent()) {
//            memberRepository.delete(member.get());
//            return true;
//        }
        return false;
    }
}
