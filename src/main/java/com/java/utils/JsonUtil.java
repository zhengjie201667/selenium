package com.java.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	private static Map<String, Object> jsonKeyValueMap = new HashMap<String, Object>();
	
	
	public static JSONObject jsonConstructor(String jsonPath,String parameterJsonName,String defaultValueJsonName,Map<String, String>parameters) {
		//keyVariableJson 代表带值得json, keyValueJson代表参数化的json
		JSONObject keyVariableJson = getJsonData(jsonPath,parameterJsonName);
		JSONObject keyValueJson = getJsonData(jsonPath,defaultValueJsonName);
		
		String jsonString = keyValueJson.toString();
		for (String parameterKey : parameters.keySet()) {
			String variable = "${"+parameterKey+"}";
			if (jsonString.contains(variable)) {
				String newValue = String.valueOf(parameters.get(parameterKey));
				jsonString = jsonString.replace(variable, newValue);
			}
		}
		
		keyValueJson = JSONObject.fromObject(jsonString);
		
		getRequestJsonValue(keyVariableJson);
		updateJsonValue(keyValueJson);
		return keyValueJson;
	}

	private static void updateJsonValue(JSONObject obj) {
		Iterator iterator = obj.keys();
		String key = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			String value = String.valueOf(obj.get(key));
			if ((obj.optJSONArray(key)==null)&&(obj.optJSONObject(key))==null) {
				if (value.contains("${")) {
					Object newValue = jsonKeyValueMap.get(value.substring(2).substring(0,value.length()-3));
					obj.put(key, newValue);
				}
			}
			if (obj.optJSONObject(key)!=null) {
				updateJsonValue(obj.getJSONObject(key));
			}
			if (obj.optJSONArray(key)!=null) {
				JSONArray jArray = obj.getJSONArray(key);
				for (int i = 0; i < jArray.size(); i++) {
					updateJsonValue(jArray.getJSONObject(i));
					
				}
			}
			
		}
		
	}

	private static void getRequestJsonValue(JSONObject obj) {
		Iterator iterator = obj.keys();
		String key = null;
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			if ((obj.optJSONArray(key)==null)&&(obj.optJSONObject(key))==null) {
				Object value = obj.get(key);
				jsonKeyValueMap.put(key, value);
			}
			if (obj.optJSONObject(key)!=null) {
				getRequestJsonValue(obj.getJSONObject(key));
			}
			if (obj.optJSONArray(key)!=null) {
				JSONArray jArray = obj.getJSONArray(key);
				for (int i = 0; i < jArray.size(); i++) {
					getRequestJsonValue(jArray.getJSONObject(i));
					
				}
			}
			
		}
		
	}

	private static JSONObject getJsonData(String folder, String fileName) {
		JSONObject jsonObject = new JSONObject();
		try {
			File filePath = new File(folder+File.separator+fileName);
			String input = FileUtils.readFileToString(filePath, "UTF-8");
			jsonObject = JSONObject.fromObject(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("id", "asfsf");
		String jsonPath = "src//test//resources//Jsons";
		String parameterJsonName = "test.json";
		String defaultValueJsonName = "test2.json";
		JSONObject jsonObject= jsonConstructor(jsonPath, parameterJsonName, defaultValueJsonName, map);
		System.out.println(jsonObject);
		
	}
}
