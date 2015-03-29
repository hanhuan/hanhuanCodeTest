 
package com.hanhuan.codeTest.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */

public class GsonFactory {
    public static Gson createInstance() {
	Gson gson = new GsonBuilder()
		.setDateFormat("dd/MM/yyyy")
		.registerTypeAdapter(java.sql.Timestamp.class,
			new SQLDateTypeAdapter()).create();
	return gson;
    }
}
