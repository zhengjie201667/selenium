package com.java.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.java.dao.StudentMapper;


public class TestStudent {
	
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
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			List<Student> students = mapper.listStudents();
			for (Student student : students) {
				System.out.println(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectUsersResult() {
		
		try {
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			List<Student> students = mapper.listStudentResult();
			for (Student student : students) {
				System.out.println(student);
			}

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
