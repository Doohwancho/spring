package com.cho.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cho.security1.model.User;

//JpaRepository 를 상속하면 자동 컴포넌트 스캔됨. 따라서 bean으로 @Repository 안해도 됨. 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// Jpa Naming 전략
	// SELECT * FROM user WHERE username = 1? 
	//1?은 첫번째 파라미터. 
	User findByUsername(String username); 
	
	
	// SELECT * FROM user WHERE username = 1? AND password = 2?
	// User findByUsernameAndPassword(String username, String password);
	
	// @Query(value = "select * from user", nativeQuery = true)
	// User find마음대로();
}
