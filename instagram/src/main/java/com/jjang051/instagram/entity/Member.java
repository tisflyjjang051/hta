package com.jjang051.instagram.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjang051.instagram.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@ToString
@Table(name="table_member")
@EntityListeners(AuditingEntityListener.class) // 추가
public class Member {
    // join되게 해보기....
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="member_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    private String profileImageUrl; // 사진

    private String mbti;

    private String description;

    private String phone;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"member"})
    private List<Image> imageList; // 양방향 매핑

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyeDate;



}
