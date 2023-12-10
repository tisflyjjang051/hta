package com.jjang051.instagram.service;

import com.jjang051.instagram.code.ErrorCode;
import com.jjang051.instagram.constant.Role;
import com.jjang051.instagram.dto.JoinDto;
import com.jjang051.instagram.entity.Member;
import com.jjang051.instagram.exception.CustomValidationApiException;
import com.jjang051.instagram.exception.MemberException;
import com.jjang051.instagram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    public Member join(JoinDto joinDto) {
        Member dbJoinMember = Member.builder()
                .userId(joinDto.getUserId())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .role(Role.ROLE_USER)
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .build();
        Member responseMember = memberRepository.save(dbJoinMember);
        //joinDto responsejoinDto = joinDto.fromEntity(responseMember);
        return responseMember;
    }
    public Member login(JoinDto joinDto) {
       return null;
    }


    public Member getProfile(int pageUserId) {
        Member memberEntity = memberRepository.findById(pageUserId).orElseThrow(()-> new MemberException(ErrorCode.NOT_FOUND_USER));
        log.info("memberEntity.getProfileImageUrl()==={}",memberEntity.getProfileImageUrl());
        return memberEntity;
    }


    public Member updateMemberInfo(Member member) {
        //User userEntity = userRepository.findById(id).orElseThrow(()->new CustomValidationApiException("찾을 수 없는 id입니다."));
        log.info("member.getId()==={}",member.getId());
        Optional<Member> memberEntity = memberRepository.findById(member.getId());
        if(memberEntity.isPresent()){
            log.info("memberEntity.getId()==={}",memberEntity.get().getId());
            String rawPassword = member.getPassword();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);

            memberEntity.get().setName(member.getName());
            memberEntity.get().setEmail(member.getEmail());
            memberEntity.get().setPhone(member.getPhone());
            memberEntity.get().setMbti(member.getMbti());
            memberEntity.get().setDescription(member.getDescription());
            return memberEntity.get();
        }
        throw new CustomValidationApiException(ErrorCode.NOT_FOUND_USER.toString());
    }

    public Member changeProfile(int principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename(); // 1.jpg

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member memberEntity = memberRepository.findById(principalId).orElseThrow(()->{
            // throw -> return 으로 변경
            return new CustomValidationApiException(ErrorCode.NOT_FOUND_USER.toString());
        });
        memberEntity.setProfileImageUrl(imageFileName);

        return memberEntity;
    }
}
