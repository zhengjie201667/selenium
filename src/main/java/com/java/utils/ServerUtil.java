package com.java.utils;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ServerUtil {
	private static final Logger logger = LogManager.getLogger();
	public static Session session = null;
	public static Channel channel = null;

     
     public static ChannelSftp getServerconnect(String host,String username,String password,int port) {
		JSch jSch = new JSch();
		
		try {
			session = jSch.getSession(username, host, port);
			session.setPassword(password);
			Properties properties = new Properties();
			properties.put("StrictHostKeyChecking", "no");
			session.setConfig(properties);
			session.connect();
			logger.info("session connect succsfully");
			channel = session.openChannel("sftp");
			channel.connect();
			logger.info("chanel connect succsfully");
		} catch (JSchException e) {
			e.printStackTrace();
			logger.info("server connect fail");
		}
		return (ChannelSftp) channel;
	 }
     
     public static void closeConnect() {
		channel.disconnect();
		session.disconnect();
	 }
     
     public void uploadFile() {
		
	}
     
     public static void main(String[] args) {
		Properties properties = new Properties();
		InputStream inStream;
		try {
			inStream = new FileInputStream("config.property");
			properties.load(inStream);
			ChannelSftp channelSftp = getServerconnect(properties.getProperty("host"), properties.getProperty("username"), properties.getProperty("password"), Integer.valueOf(properties.getProperty("port")));
//			InputStream inputStream = new 
//			channelSftp.put(inputStream, "/root/test-copy.txt");
//			closeConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
