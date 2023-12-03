package com.jjang051.photogram02.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SequenceGenerator(
		name = "IMAGE_SEQ_GENERATOR"
		, sequenceName = "IMAGE_SEQ"
		, initialValue = 1
		, allocationSize = 1
)
public class Image { // N,   1
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String caption; // 오늘 나 너무 피곤해!!
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	//@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴
	private User user; // 1,  1
	
	// 이미지 좋아요
//	@JsonIgnoreProperties({"image"})
//	@OneToMany(mappedBy = "image")
//	private List<Likes> likes;
	
	// 댓글
//	@OrderBy("id DESC")
//	@JsonIgnoreProperties({"image"})
//	@OneToMany(mappedBy = "image")
//	private List<Comment> comments;
	
//	@Transient // DB에 칼럼이 만들어지지 않는다.
//	private boolean likeState;
	
//	@Transient
//	private int likeCount;
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
