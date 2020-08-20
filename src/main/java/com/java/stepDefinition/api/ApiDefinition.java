package com.java.stepDefinition.api;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.utils.FileUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.lu.a;
import net.sf.json.JSONObject;

public class ApiDefinition {
	private static final Logger logger = LogManager.getLogger();
	
	public static void name() {
		File file = new File("src\\test\\resources\\Jsons\\api_1.json");
		JSONObject json = FileUtil.getJsonObject(file);
		json.replace("name", "mark");
		logger.info(json);
	}
	
	@Given ("^I want to construct a json (.*)$")
	public void constructJson(String name) {
		File file = new File("src\\test\\resources\\Jsons\\api_1.json");
		JSONObject json = FileUtil.getJsonObject(file);
//		Set jsonMapKeySet = jsonMap.keySet();
//		Iterator<String> mapIterator = jsonMapKeySet.iterator();
//		while (mapIterator.hasNext()) {
//			String mapKeyString = (String) mapIterator.next();
//			json.replace(mapKeyString, jsonMap.get(mapKeyString));
//		}
		json.replace("name", name);
		
	}
	
	
	public static void main(String[] args) {
		name();
	}
}
