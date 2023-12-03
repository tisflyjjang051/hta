package com.jjang051.photogram02.repository;

import com.jjang051.photogram02.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

	@Modifying // INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!!
	@Query(value = "INSERT INTO subscribe(id,fromUserId, toUserId, createDate) VALUES(SUBSCRIBE_SEQ.nextval,:fromUserId, :toUserId, sysdate)", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId);

	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(@Param("principalId") int principalId, @Param("pageUserId") int pageUserId);

	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(@Param("pageUserId") int pageUserId);

}
