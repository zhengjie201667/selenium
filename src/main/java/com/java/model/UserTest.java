package com.java.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class UserTest {

	@Value("11")
	private int id;
	
	@Value("13")
	private int age;
	
	@Value("lisi")
	private String name;

	

	
}
