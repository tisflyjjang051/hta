package com.jjang051.photogram02.service;

import com.jjang051.photogram02.entity.Subscribe;
import com.jjang051.photogram02.handler.exception.CustomApiException;
import com.jjang051.photogram02.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeService {


    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void following(int fromUerId, int toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUerId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독하였습니다.");
        }

    }

    @Transactional
    public void unFollowing(int fromUerId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUerId,toUserId);
    }
}
