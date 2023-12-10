package com.jjang051.instagram.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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
@EntityListeners(AuditingEntityListener.class) // 추가
public class Image { // N,   1
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String caption; // 오늘 나 너무 피곤해!!
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	//@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "member_id")
	@ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴
	private Member member; // 1,  1
	

	@CreatedDate
	private LocalDateTime createDate;

	@LastModifiedDate
	private LocalDateTime modifyDate;



}
