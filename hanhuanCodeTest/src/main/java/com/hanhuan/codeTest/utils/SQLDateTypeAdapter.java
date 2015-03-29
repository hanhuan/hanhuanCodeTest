 
package com.hanhuan.codeTest.utils;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/**
 * @author hanhuan@cn.ibm.com
 * @version 1.0
 * @date 2015/3/29
 * 
 */
public class SQLDateTypeAdapter implements JsonSerializer<java.sql.Timestamp>,
	JsonDeserializer<Date> {
    private final DateFormat format = new SimpleDateFormat(
	    "dd/MM/yyyy");

    public JsonElement serialize(java.sql.Timestamp src, Type typeOfSrc,
	    JsonSerializationContext context) {
	String dateFormatAsString = format.format(src);
	return new JsonPrimitive(dateFormatAsString);
    }

    public Date deserialize(JsonElement json, Type typeOfT,
	    JsonDeserializationContext context) throws JsonParseException {
	if (!(json instanceof JsonPrimitive)) {
	    throw new JsonParseException("The date should be a string value");
	}

	try {
	    return format.parse(json.getAsString());
	} catch (ParseException e) {
	    throw new JsonParseException(e);
	}

    }
}
