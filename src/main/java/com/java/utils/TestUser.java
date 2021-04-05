package com.java.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java.dao.UserMapper;

public class TestUser {
	private SqlSession session;
	
	@Before
	public void before() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testSelectUsers() {
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			List<User> users = mapper.selectUsers();
			for (User user : users) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectUsersById() {
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			User user = mapper.selectUserById(4);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveUser() {
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			int user = mapper.saveUser(new User(11,"数据","aa"));
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateUser() {
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			int user = mapper.updateUser(new User(6,"新数据库","ccc"));
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteUser() {
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			int user = mapper.deleteUser(new User(6,"laosun","ccc"));
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
		session.commit();
		session.close();
	}
	
}
