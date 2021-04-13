package com.java.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class User implements Serializable{
	private int id;
	private String username;
	private String password;
	@Override
	public String toString(){
        return "id "+id+",username "+username+",password "+password;
    }
	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	
		
	
	
}
