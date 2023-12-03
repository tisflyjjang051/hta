package com.jjang051.sbb;

import com.jjang051.sbb.entity.Question;
import com.jjang051.sbb.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
//@Rollback(false)
class SbbApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		for(int i=0;i<100;i++) {
			Question q1 = new Question();
			q1.setSubject("sbb가 무엇인가요?");
			q1.setContent("sbb에 대해서 알고 싶습니다.");
			q1.setCreateDate(LocalDateTime.now());
			this.questionRepository.save(q1);  // 첫번째 질문 저장
		}

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	@DisplayName("값을 출력해본다")
	void testJpa02() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
}
