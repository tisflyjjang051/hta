package com.jjang051.photogram.domain.likes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjang051.photogram.domain.image.Image;
import com.jjang051.photogram.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes_uk",
						columnNames = {"imageId", "userId"}
				)
		}
)
public class Likes { // N
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "imageId")
	@ManyToOne
	private Image image; // 1
	
	@JsonIgnoreProperties({"images"})
	// 오류가 터지고 나서 잡아봅시다.
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user; // 1
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}


}
