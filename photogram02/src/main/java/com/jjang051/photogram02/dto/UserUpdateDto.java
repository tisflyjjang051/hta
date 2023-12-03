package com.jjang051.photogram02.dto;


import com.jjang051.photogram02.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class UserUpdateDto {

	@NotBlank
	private String userId;
	@NotBlank
	private String password; // 필수
	@NotBlank
	private String name; // 필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	private String email;


	// 조금 위함함. 코드 수정이 필요할 예정
	public User toEntity() {
		return User.builder()
				.userId(userId)
				.name(name) // 이름을 기재 안했으면 문제!! Validation 체크
				.password(password) // 패스워드를 기재 안했으면 문제!! Validation 체크
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.email(email)
				.build();
	}
}
