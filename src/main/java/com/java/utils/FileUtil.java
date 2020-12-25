package com.java.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.StringUtil;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.jayway.jsonpath.JsonPath;
import com.jcraft.jsch.ChannelSftp;
import com.mysql.jdbc.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class FileUtil {
	private static final Logger logger = LogManager.getLogger();
	
	public static void getExcelFile(String filePath) {
		Fillo fillo = new Fillo();
		Connection con = null;
		Recordset recordset = null;
		try {
			con = fillo.getConnection(filePath);
			String strSql = "select * from tortoise where id = 'env'";
			recordset = con.executeQuery(strSql);
			while (recordset.next()) {
				System.out.println(recordset.getField("sit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		recordset.close();
		con.close();
	}
	
	/**
	 * @author Justin
	 * transfer file to jsonObject
	 */
	public static JSONObject getJsonObject(File jsonFile) {
		logger.info("get json object");
		JSONObject jsonObject = null;
		try {
			String jString = FileUtils.readFileToString(jsonFile);
			jsonObject = JSONObject.fromObject(jString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * @author Justin
	 * transfer file to json string
	 */
	public static String getJsonObjectString(File jsonFile) {
		String jString = null;
		try {
			jString = FileUtils.readFileToString(jsonFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jString;
	}
	
	/**
	 * 
	 * @param new excel
	 */
	public void getExcel() {
//		HSSFWorkbook wb = new HSSFWorkbook();
//		HSSFSheet sheet = wb.createSheet("");
//		HSSFRow firstRow = sheet.createRow(0);
//		HSSFCell firstCell = firstRow.createCell(0);
//		String [] excelCellName = {"objectname","cit","sit"};
//		for (int i = 0; i < 3; i++) {
//			
//		}

	}
	
	public static void readFile() {
		InputStream iStream = null;
		OutputStream oStream = null;
		try {
			iStream = new FileInputStream("D://connectServer111.txt");
			oStream = new FileOutputStream("D://test.txt");
			byte [] buffer = new byte [1024];
			int len;
			while ((len = iStream.read(buffer))!=-1) {
				System.out.println(len);
				oStream.write(buffer, 0, len);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				iStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<HashMap<String, Object>> getJsonList(String json) {
        List<HashMap<String, Object>> dataList;
        dataList = new ArrayList<>();
        try {
            JSONObject rootObject = JSONObject.fromObject(json);
            JSONObject paramzObject = rootObject.getJSONObject("paramz");
            JSONArray feedsArray = paramzObject.getJSONArray("feeds");
            for (int i = 0; i < feedsArray.size(); i++) {
                JSONObject sonObject = feedsArray.getJSONObject(i);
                JSONObject dataObject = sonObject.getJSONObject("data");
                String subjectStr = dataObject.getString("subject");
                String summaryStr = dataObject.getString("summary");
                String coverStr = dataObject.getString("cover");
                HashMap<String, Object> map = new HashMap<>();
                map.put("subject", subjectStr);
                map.put("summary", summaryStr);
                map.put("cover", coverStr);
                dataList.add(map);
            }
            System.out.println(dataList);
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static void constructJson(JSONObject defaultJson,Map<String, String> cdsData) {
		
		for (Map.Entry<String, String> entry:cdsData.entrySet()) {
			JSONObject json = defaultJson;
			String paths[] = entry.getKey().split("\\.");
			for (int i = 0; i < paths.length; i++) {
				String path = paths[i];
				boolean isLast = (i ==paths.length-1);
				if (path.contains("[")&&path.contains("]")) {
					int start = path.indexOf("[");
					int end = path.indexOf("]");
					int index = Integer.parseInt(path.substring(start + 1,end));
					path = path.substring(0, start);
					JSONArray array = json.getJSONArray(path);
					if (isLast) {
						array.set(index, entry.getValue());
					} else {
						json = array.getJSONObject(index);
					}
				} else {
					if (isLast) {
						json.put(path, entry.getValue());
					} else {
						json = json.getJSONObject(path);
					}
				}
			}
			System.out.println(json);
		}
		
	}
	
	public static void constructJson(String jsonString) {
//		for (String string : strings) {
//			
//		}
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(jsonString);
		System.out.println(jsonObject.getJSONArray("paramz"));
		
	}
	
	public static String constructJson2(String jsonString,Map<String, String> cdsData) {
		for(Map.Entry<String, String>entry:cdsData.entrySet()) {
			String key = entry.getKey();
			if (jsonString.contains(key)) {
				
				jsonString = jsonString.replace("{"+key+"}", entry.getValue());
				
			}
//			System.out.println(jsonString);
		}
		return jsonString;
	}
	
	 public static JSONObject toMap(String str,Map<String,String> replaceMap){
	        Map<String, String> map=new HashMap<>();
	        JSONObject jsonObject=JSONObject.fromObject(str);
	        //遍历
	        Set<String> set=jsonObject.keySet();
	        for (String string : set) {
	            Set<String> mapSet = replaceMap.keySet();
	            for (String s : mapSet) {
	                if (s.equals(string)) {
	                    jsonObject.put(s, replaceMap.get(s));
	                }
	                if (jsonObject.getString(string).contains(":")) {//冒号可以判断该字段值为一个json
	                    if (jsonObject.getString(string).contains("]")){// ]可以判断该字段值为一个数组，数组就需要转成JsonArray才行
	                       // JSONArray array=JSONObject.parseArray(jsonObject.getString(string));
	                      //  for (Object o : array) {
	                         //   JSONObject jsonObject2= toMap(o.toString(),replaceMap);//递归，回调自己的函数，以达到一层一层往下挖，看看还有多少的json字符串
	                        //    jsonObject.put(string, jsonObject2);
	                       // }
	                       //以上的array遍历有bug  改成以下遍历：
	                    	com.alibaba.fastjson.JSONArray array=com.alibaba.fastjson.JSONObject.parseArray(jsonObject.getString(string));
	                        for (int i = 0; i < array.size() ;i++) {
	                            Object o = array.get(i);
	                            array.set(i,toMap(o.toString(),replaceMap));
	                        }
	                        jsonObject.put(string, array);
	                    }else{
	                        JSONObject jsonObject2=toMap(jsonObject.getString(string),replaceMap);
	                        jsonObject.put(string, jsonObject2);
	                    }
	                }

	            }
	        }
//	        System.out.println(jsonObject);
	        return jsonObject;
	    }
	
	public static void constructDefaultJson(String jsonString) {
		Map<Object,Object> maps = (Map) com.alibaba.fastjson.JSONObject.parse(jsonString);
		for(Map.Entry<Object, Object>entry:maps.entrySet()) {
			System.out.println("这是map"+entry.getKey()+entry.getValue());
		}
	}
	
	
	
	public static void main(String[] args) {
		File file = new File("src/test/resources/Jsons/test2.json");
		String json;
		Map<String, String> jsonMap = new HashMap<>();
		jsonMap.put("ar", "test");
		jsonMap.put("br","test2");
		Properties properties = new Properties();
		InputStream inStream;
 		try {
			json = FileUtils.readFileToString(file,"UTF-8");
//			constructJson(json);
//			constructJson2(json,jsonMap);
 			System.out.println(json);
 			InputStream inputStream = new ByteArrayInputStream(json.getBytes());
 			inStream = new FileInputStream(new File("Configs\\config.property"));
			properties.load(inStream);
 			ChannelSftp channelSftp = ServerUtil.getServerconnect(properties.getProperty("host"),
					properties.getProperty("serverUsername"), properties.getProperty("serverPassword"),
					Integer.valueOf(properties.getProperty("port")));
 			channelSftp.put(inputStream, "//root//testJson.txt", ChannelSftp.OVERWRITE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnect();
		}
		
	}
}
