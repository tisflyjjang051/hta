package com.jjang051.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Builder
@AllArgsConstructor
//@ToString(exclude = {"nickName,password"})
public class Member02 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 30,unique = true)
    private String userId;

    @Column(length = 300)
    private String password;

    @Column(length = 300)
    private String role;

    @Column(length = 100)
    private String nickName;

    @Column(length = 100)
    private  String email;

    private Integer age;


    public Member02 update(String role, String nickName,String email,Integer age) {
        this.role = role;
        this.nickName = nickName;
        this.email = email;
        this.age = age;
        return this;
    }
}
