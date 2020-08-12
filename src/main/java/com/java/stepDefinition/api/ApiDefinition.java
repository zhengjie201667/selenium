package com.java.stepDefinition.api;

import java.io.File;
import com.java.utils.FileUtil;

import net.sf.json.JSONObject;

public class ApiDefinition {
	public static void name() {
		File file = new File("src\\test\\resources\\Jsons\\api_1.json");
		JSONObject json = FileUtil.getJsonObject(file);
		json.replace("name", "mark");
		System.out.println(json);
	}
	
	
	public static void main(String[] args) {
		name();
	}
}
