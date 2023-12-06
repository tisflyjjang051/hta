package com.jjang051.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
//@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Member02 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 100,unique = true)
    private String userId;

    @Column(length = 100,nullable = true)
    private String password;

    @Column(length = 20,nullable = true)
    private String role;

    @Column(length = 100)
    private String nickName;

    @Column(length = 100)
    private  String email;

    private Integer age;


    public void updateMemberInfo(String nickName,String email, int age) {
        this.nickName= nickName;
        this.email = email;
        this.age= age;
    }
    // 생성자에 @Builder 적용
}
