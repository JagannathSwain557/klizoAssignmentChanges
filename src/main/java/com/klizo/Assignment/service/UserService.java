package com.klizo.Assignment.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klizo.Assignment.dto.UserDto;
import com.klizo.Assignment.entity.UserEntity;
import com.klizo.Assignment.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRep;
	public UserEntity create(UserDto userDto) {
		UserEntity userEntity=userRep.save(new UserEntity(userDto));
		return userEntity;
	}

	public List<UserEntity> getAllUsers() {
		Iterable<UserEntity> owners = userRep.findAll();
		ArrayList ownerList = new ArrayList();
		owners.forEach(b -> ownerList.add(b));
		return ownerList;
	}
	public UserEntity updateUser(UserDto userDetails, String fastName) {
		UserEntity user = userRep.findById(fastName).get();
		if (userDetails.getFastName() != null && !userDetails.getFastName().isEmpty()) {
			user.setFastName(userDetails.getFastName().trim().toUpperCase());
		}
		if (userDetails.getLastName() != null && !userDetails.getLastName().isEmpty()) {
			user.setLastName(userDetails.getLastName().trim().toUpperCase());
		}
		if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()) {
			user.setUsername(userDetails.getUsername().trim().toUpperCase());
		}
		if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
			user.setPassword(userDetails.getPassword().trim().toUpperCase());
		}
	
		UserEntity updatedUser = userRep.save(user);
		return updatedUser;

	}
	public List<UserEntity> authenticateUserNamePassword(String username, String password) {
		Iterable<UserEntity> users = userRep.authenticateUserNamePassword(username.trim().toUpperCase(),
				password.trim().toUpperCase());
		ArrayList userList = new ArrayList();
		users.forEach(b -> userList.add(b));
		return userList;
	}
	public UserEntity delete(String fastName) {
		UserEntity userEntity=userRep.findById(fastName).get();
		userRep.delete(userEntity);
		return userEntity;
	}
}