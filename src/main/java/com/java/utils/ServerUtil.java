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
			properties.put("userauth.gssapi-with-mic", "no");
			session.setConfig(properties);
			session.connect();
			System.out.println("session connect succsfully");
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("chanel connect succsfully");
		} catch (JSchException e) {
			e.printStackTrace();
			logger.info("server connect fail");
		}
		return (ChannelSftp) channel;
	}
	
	public static void uploadFileToServer(String src, String dst,ChannelSftp channelSftp) throws Exception {
			channelSftp.put(src, dst, ChannelSftp.OVERWRITE);

	}

	
	public static void uploadFileToServer(ChannelSftp channelSftp,InputStream inputStream, String dst) throws SftpException {
		channelSftp.put(inputStream, dst, ChannelSftp.OVERWRITE);
}

	public static void closeConnect() {
		channel.disconnect();
		session.disconnect();
	}


	public static void main(String[] args) {

		getServerconnect("119.29.12.21", "root", "Zh123456", 22);
		
	}
}
