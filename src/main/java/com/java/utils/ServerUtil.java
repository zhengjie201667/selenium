package com.java.utils;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ServerUtil {
	private static final Logger logger = LogManager.getLogger();
	public static Session session = null;
	public static Channel channel = null;
	public static ChannelSftp channelSftp = null;

	public static ChannelSftp getServerconnect(String host, String username, String password, int port) {
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
	
	public static void uploadFileToServer(ChannelSftp channelSftp) {
		String src = "D://test.txt";
		String dst = "//root//test2.txt";
		try {
			channelSftp.put(src, dst, ChannelSftp.OVERWRITE);
		} catch (SftpException e) {
			e.printStackTrace();
		}
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
//		File file = new File("Configs\\log4j2.xml");
//		try {
//			inStream = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		try {
			inStream = new FileInputStream(new File("Configs\\config.property"));
			properties.load(inStream);
			ChannelSftp channelSftp = getServerconnect(properties.getProperty("host"),
					properties.getProperty("serverUsername"), properties.getProperty("serverPassword"),
					Integer.valueOf(properties.getProperty("port")));
//			File file = new File("D:/test2.txt");
//			InputStream inputStream = new FileInputStream(file);
//			channelSftp.put(inputStream, "/root/test2.txt");
//			closeConnect();
			uploadFileToServer(channelSftp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnect();
		}
	}
}
