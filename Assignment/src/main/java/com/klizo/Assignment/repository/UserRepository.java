package com.klizo.Assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klizo.Assignment.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{
	  @Query(value="SELECT * FROM User_Entity where username=:username and password=:password  ", nativeQuery = true)
			  List<UserEntity> authenticateUserNamePassword(@Param("username")String
					  username,@Param("password") String password);
	  @Query(value="SELECT * FROM User_Entity where username like 'D%'  ", nativeQuery = true)
	  List<UserEntity> authenticateUserName(@Param("username")String username);
	  
}
