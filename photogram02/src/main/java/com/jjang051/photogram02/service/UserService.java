package com.jjang051.photogram02.service;



import com.jjang051.photogram02.entity.User;
import com.jjang051.photogram02.handler.exception.CustomException;
import com.jjang051.photogram02.handler.exception.CustomValidationApiException;
import com.jjang051.photogram02.repository.SubscribeRepository;
import com.jjang051.photogram02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    /*
    @Transactional
    public User updateUserInfo(int id, User user) {
        Optional<User> userEntity = userRepository.findById(id);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);


        if (userEntity.isPresent()) {
            User updateUser = User.builder()
                    .id(userEntity.get().getId())
                    .userId(user.getUserId())
                    .name(user.getName())
                    .password(encPassword)
                    .bio(user.getBio())
                    .website(user.getWebsite())
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .email(user.getEmail())
                    .build();
            userRepository.save(updateUser);
            return updateUser;
        } else {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put("internal server error","알 수 없는 서버 에러가 발생하였습니다.");
            throw new CustomValidationApiException("회원정보 수정에 실패하였습니다",errorMap);
        }
    }
    */
    @Transactional
    public User updateUserInfo(int id, User user) {
        //User userEntity = userRepository.findById(id).orElseThrow(()->new CustomValidationApiException("찾을 수 없는 id입니다."));
        User userEntity = userRepository.findById(id).orElseThrow(new Supplier<CustomValidationApiException>() {
            @Override
            public CustomValidationApiException get() {
                return new CustomValidationApiException("찾을 수 없는 id입니다.");
            }
        });
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setName(user.getName());
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

//    public void userProfile(int userId) {
//        User userEntity = userRepository.findById(userId).orElseThrow(()-> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));
//    }

    public User userProfile(int userId) {
        User userEntity = userRepository.findById(userId).orElseThrow(()-> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));
        return userEntity;
    }
}
