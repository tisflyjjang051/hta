package com.jjang051.instagram.dto;



import com.jjang051.instagram.entity.Image;
import com.jjang051.instagram.entity.Member;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl, Member member) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.member(member)
				.build();
	}
}
