package com.jjang051.photogram02.repository;

import com.jjang051.photogram02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	// JPA query method
	User findByUserId(String userId);
	//User findById(int id);

}
