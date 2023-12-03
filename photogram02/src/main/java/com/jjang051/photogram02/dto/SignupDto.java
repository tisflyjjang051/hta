package com.jjang051.photogram02.dto;


import com.jjang051.photogram02.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	@Size(min = 2, max = 20)
	@NotBlank
	private String userId;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		return User.builder()
				.userId(userId)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
