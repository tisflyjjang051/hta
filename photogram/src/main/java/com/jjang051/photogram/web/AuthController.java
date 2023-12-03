package com.jjang051.photogram.web;


import com.jjang051.photogram.domain.user.User;
import com.jjang051.photogram.service.AuthService;
import com.jjang051.photogram.web.dto.auth.SignupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor // final 필드를 DI 할때 사용
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@GetMapping("/signin")
	public String signinForm() {

		return "/auth/signin";
	}

	@GetMapping("/signup")
	public String signupForm() {
		return "/auth/signup";
	}

	// 회원가입버튼 -> /auth/signup -> /auth/signin
	// 회원가입버튼 X
	@PostMapping("/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)

		// User < - SignupDto
		User user = signupDto.toEntity();
		authService.join(user);
		// System.out.println(userEntity);

		// 로그를 남기는 후처리!!
		return "/auth/signin";

	}
}
