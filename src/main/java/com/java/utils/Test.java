package com.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test {
	public static void readFile(File file) {
		InputStream iStream = null;
		byte b[] = new byte[1024];
		int temp = 0;
		int len = 0;
		int by=0;
		try {
			iStream = new FileInputStream(file);
			OutputStream oStream = new FileOutputStream("D:\\test.txt");
			while ((by = iStream.read())!=-1) {
				
				oStream.write(by);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		System.out.println(new String(b));
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\connectServer111.txt");
		readFile(file);
	}
}
