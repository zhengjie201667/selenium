package com.java.function.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.java.utils.ServerUtil;
import com.jcraft.jsch.ChannelSftp;

import io.restassured.RestAssured;

public class ApiFunc {
	private static final Logger logger = LogManager.getLogger();
	
	public void getResponse(String url) {
		RestAssured.given().contentType("j");
		
	}
	
	public ChannelSftp getServerConnect() {
		Properties properties = new Properties();
		InputStream inStream;
		ChannelSftp channelSftp = null;
		try {
			inStream = new FileInputStream(new File("src\\test\\resources\\config.property"));
			properties.load(inStream);
			channelSftp = ServerUtil.getServerconnect(properties.getProperty("host"),
					properties.getProperty("serverUsername"), properties.getProperty("serverPassword"),
					Integer.valueOf(properties.getProperty("port")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("File not find");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("IO proceession exeception");
		}
		return channelSftp;
	}
	
	public SqlSession getMybatisSession() throws IOException {
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession session = sqlSessionFactory.openSession();
        
        return session;
	}
	
}
