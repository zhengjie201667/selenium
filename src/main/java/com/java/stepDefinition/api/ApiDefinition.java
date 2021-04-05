package com.java.stepDefinition.api;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.java.dao.UserMapper;
import com.java.function.api.ApiFunc;
import com.java.utils.FileUtil;
import com.java.utils.JsonUtil;
import com.java.utils.ServerUtil;
import com.java.utils.User;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.lu.a;
import net.sf.json.JSONObject;

public class ApiDefinition {
	private static final Logger logger = LogManager.getLogger();
	public static JSONObject jsonObject ;
	
	
	@Given ("^Construct json file with (.*) (.*) (.*) (.*)$")
	public void constructJson(String arg1,String arg2,String arg3,String arg4,DataTable dataTable) {

		String jsonPath = "src\\test\\resources\\Jsons";
		String parameterJsonName = "test.json";
		String defaultValueJsonName = "test2.json";
//		Map<String, String> parameters = new HashMap<String, String>();
		DataTable dataTable2 = dataTable;
		
		List<Map<String, String>> listMap = dataTable2.asMaps(String.class, String.class);
		Map<String, String> parameters= listMap.get(0);
//		parameters.put("id", id);
//		parameters.put("oid", oid);
//		parameters.put("format", format);
//		parameters.put("PageIndex", PageIndex);
		jsonObject = JsonUtil.jsonConstructor(jsonPath, parameterJsonName, defaultValueJsonName, parameters);
		logger.info("The json content is : "+jsonObject);
		
	}
	
	@When ("^I put the json into the server$")
	public void uploadJsonIntoServer() {
		ApiFunc apiFunc = new ApiFunc();
		ChannelSftp channelSftp = apiFunc.getServerConnect();
		String dst ="/root/newjson2.json";
		InputStream inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes());
		try {
			ServerUtil.uploadFileToServer(channelSftp,inputStream, dst);
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}
	
	@Then("^I check the data$")
	public void checkData() {
		SqlSession session = null;
		ApiFunc apiFunc = new ApiFunc();
		try {
			session = apiFunc.getMybatisSession();
			UserMapper userMapper = session.getMapper(UserMapper.class);
			List<User> users = userMapper.selectUsers();
			for (User user : users) {
				logger.info(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		String url = "dsafj";
		
	}
}
