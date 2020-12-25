package com.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import inteface.Ant;
import inteface.Flyanimal;
import net.bytebuddy.asm.Advice.This;

public class Test extends DbUtils{
	// 静态属性
	private static String staticField = getStaticField();
	// 静态代码块ࣘ
	static {
	System.out.println(staticField);
	System.out.println("静态方法");
	}
	// 普通属性
	private String field = getField();
	// 普通方法快
	{
	System.out.println(field);
	}
	// 构造法方法
	public Test() {
	super();
	System.out.println("构造函数初始化");
	}
	public static String getStaticField() {
	String statiFiled = "Static Field Initial";
	return statiFiled;
	}
	public static String getField() {
	String filed = "Field Initial";
	
	return filed;
	
	}
	
	// 主方法
	
	
	public static int add(int... numbers){
		int sum = 0;
		for(int num : numbers){
		sum += num;
		}
		return sum;
		}
	
	public static void main(String[] argc) {

		Flyanimal flyanimal = new Ant();
		flyanimal.fly();
	
	
	}

}
