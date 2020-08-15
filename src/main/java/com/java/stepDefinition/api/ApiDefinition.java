package com.java.stepDefinition.api;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.java.utils.FileUtil;
import net.sf.json.JSONObject;

public class ApiDefinition {
	private static final Logger logger = LogManager.getLogger();
	
	public static void name() {
		File file = new File("src\\test\\resources\\Jsons\\api_1.json");
		JSONObject json = FileUtil.getJsonObject(file);
		json.replace("name", "mark");
		logger.info(json);
	}
	
	public void constructJson(JSONObject json,String... jsonArry) {
		
	}
	
	
	public static void main(String[] args) {
		name();
	}
}
