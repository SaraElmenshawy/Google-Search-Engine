package com.googlesearch.app.general;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTemplatesReader {

	public static JSONObject jsonFileReader(String filePath)
	{
		JSONParser parser = new JSONParser();
	
		Object obj =new Object();
		try {
			obj = parser.parse(new FileReader(filePath));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	
		JSONObject jsonObject = (JSONObject) obj;
		
		return jsonObject;
	}
}
