package com.jjang051.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Member02 {
    @Id
    @Column(length = 30,unique = true)
    private String userId;
    @Column(length = 100)
    private String nickName;
    @Column(length = 100)
    private  String email;
    private Integer age;
}
