package com.googlesearch.app;

import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.googlesearch.app.dto.SearchKeyWord;

@RestController
public class GoogleSearchController {
	@RequestMapping(value="/GoogleSearch",method = RequestMethod.POST, consumes="application/json")
	public List<Result> getSearchResults(@RequestBody SearchKeyWord keyWord) throws IOException, GeneralSecurityException
	{
		JSONParser parser = new JSONParser();

		Object obj =new Object();
		try {
			obj = parser.parse(new FileReader("..\\GoogleSearchEngineApp\\src\\main\\resources\\templates\\Credentials.json"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
 
		JSONObject jsonObject = (JSONObject) obj;
		
		String searchQuery = keyWord.getKeyword(); //The query to search
	    String cx = (String) jsonObject.get("cx"); //Your search engine CX
	    String ApplicationName=(String) jsonObject.get("ApplicationName");// Add the engine name created in google
	    String GoogleKey=(String) jsonObject.get("GoogleKey"); //Add google generated key 
	    
	    //Instance Custom search
	    Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null) 
	                   .setApplicationName(ApplicationName) 
	                   .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer(GoogleKey)) 
	                   .build();

	    //Set search parameter	 
	    Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx); 

	    //Execute search
	    Search result = list.execute();
	    	        
		return result.getItems();
	}
}
