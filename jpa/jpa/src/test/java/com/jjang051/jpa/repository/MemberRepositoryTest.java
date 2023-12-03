package com.jjang051.jpa.repository;

import com.jjang051.jpa.entity.Member02;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// db에 넣고 나서 다시 롤백시킨다.
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("1+2는 3이다")
    @Test
    public void test() {
        int a = 1;
        int b = 2;
        int sum = 3;
        //Assertions.assertEquals(sum,a+b);
        //Assertions.assertThat(a+b).isNotEqualTo(5);
        Assertions.assertThat("abcd").contains("b");
        Assertions.assertThat("abcd").startsWith("a");


    }

    public void joinMember() {
        for(int i=0;i<10;i++) {
            Member02 dbInsertMember = Member02.builder()
                    .userId("jjang"+i)
                    .email("jjang"+i+"@hanmail.net")
                    .nickName("길동"+i)
                    .age(10+i)
                    .build();
            memberRepository.save(dbInsertMember);
        }
    }

    @Test
    @DisplayName("이름으로 조회")
    public void findByNickNameTest() {
        joinMember();
        List<Member02> memberList = memberRepository.findByNickName("길동2");
        Assertions.assertThat(memberList.size()).isGreaterThan(0);
        //Assertions.assertEquals(1,memberList.size());
//        for(Member02 item: memberList) {
//            System.out.println(item.toString());
//        }
    }

    @Test
    @DisplayName("별명과 아이디 조회")
    public void findByNickNameOrUserIdTest() {
        joinMember();
        List<Member02> memberList = memberRepository.findByNickNameOrUserId("길동2","jjang5");
        //Assertions.assertEquals(2,memberList.size());
    }

    @Test
    @DisplayName("15살보다 많은 사람 찾기")
    public void findByAgeGreaterThanTest() {
        joinMember();
        List<Member02> memberList = memberRepository.findByAgeGreaterThanOrderByAgeDesc(15);
        //Assertions.assertEquals(4,memberList.size());
        for(Member02 member:memberList) {
            System.out.println(member.toString());
        }
    }
}





