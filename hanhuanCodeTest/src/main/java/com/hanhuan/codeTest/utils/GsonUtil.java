package com.hanhuan.codeTest.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanhuan.codeTest.dto.Item;
import com.hanhuan.codeTest.dto.Recipe;
/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */
public class GsonUtil {

	public static String createJsonObject(Object wrapper) {
		Gson gson = GsonFactory.createInstance();
		String json = gson.toJson(wrapper);
		return json;
	}

	public static Map<String, Object> toObject(String json) {
		Gson gson = GsonFactory.createInstance();
		Map<String, Object> result = gson.fromJson(json,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		return result;
	}

	public static List<Recipe> toList(String json) {
		Gson gson = GsonFactory.createInstance();
		List<Recipe> result = gson.fromJson(json,
				new TypeToken<List<Recipe>>() {
				}.getType());
		return result;
	}

	public static List<Item> toFridge(String json) {
		Gson gson = GsonFactory.createInstance();
		List<Item> result = gson.fromJson(json, new TypeToken<List<Item>>() {
		}.getType());
		return result;
	}

}
