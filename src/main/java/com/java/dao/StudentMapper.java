package com.java.dao;

import java.util.List;
import com.java.utils.Student;


public interface StudentMapper {

	List<Student> listStudents();
	
	List<Student> listStudentResult();
}
