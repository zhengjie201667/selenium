package com.java.dao;

import java.util.List;

import com.java.utils.User;

public interface UserMapper {
	
	List<User> selectUsers();
	
	User selectUserById(int id);
	
	int saveUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(User user);
}
