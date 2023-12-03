package com.jjang051.photogram02.api;


import com.jjang051.photogram02.dto.CommonResponseDto;
import com.jjang051.photogram02.dto.CustomUserDetailsDto;
import com.jjang051.photogram02.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {
	
	private final SubscribeService subscribeService;
	
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal CustomUserDetailsDto customUserDetailsDto, @PathVariable int toUserId){
		subscribeService.following(customUserDetailsDto.getLoggedUser().getId(), toUserId);
		return new ResponseEntity<>(new CommonResponseDto<>(1, "구독하기 성공", null), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal CustomUserDetailsDto customUserDetailsDto, @PathVariable int toUserId){
		subscribeService.unFollowing(customUserDetailsDto.getLoggedUser().getId(), toUserId);
		return new ResponseEntity<>(new CommonResponseDto<>(1, "구독취소하기 성공", null), HttpStatus.OK);
	}
}
