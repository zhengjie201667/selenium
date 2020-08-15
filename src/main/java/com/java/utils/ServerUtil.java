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
	
	public SFTPChannel getSFTPChannel() {
		    return new SFTPChannel();
		  }
   /**
     * @param args
     * @throws Exception
     */
     public static void connect() throws Exception {
        ServerUtil test = new ServerUtil();
        Map<String, String> sftpDetails = new HashMap<String, String>();
        // 设置主机ip，端口，用户名，密码
          sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "192.168.211.128");
          sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "root");
          sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "123456");
          sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");
         
          String src = "D:\\connectServer111.txt"; // 本地文件名
          String dst = "/root/connectServer111-copy.txt"; // 目标文件名
                
          SFTPChannel channel = test.getSFTPChannel();
          ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
         
       
          OutputStream out = chSftp.put(dst, ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
          byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
         int read;
//         InputStream is=null;
//        if (out != null) {
//             System.out.println("Start to read input stream");
//             is = new FileInputStream(src);
//             do {
//                read = is.read(buff, 0, buff.length);
//                  if (read > 0) {
//                 out.write(buff, 0, read);
//              }
//                 out.flush();
//            } while (read >= 0);
//           System.out.println("input stream read done.");
//        }
        
//	             chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2
         InputStream isInputStream = chSftp.get(dst, 0);
        
          chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // 代码段3
         byte[] b = new byte[1024];
         isInputStream.read(b);
         isInputStream.close();
         System.out.println(new String(b));
         chSftp.quit();
      
     }
     
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
     
     public static void main(String[] args) {
		Properties properties = new Properties();
		InputStream inStream;
		try {
			inStream = new FileInputStream("config.property");
			properties.load(inStream);
			ChannelSftp channelSftp = getServerconnect(properties.getProperty("host"), properties.getProperty("username"), properties.getProperty("password"), Integer.valueOf(properties.getProperty("port")));
			InputStream inputStream = new FileInputStream("D:\\test.txt");
			channelSftp.put(inputStream, "/root/test-copy.txt");
			closeConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
