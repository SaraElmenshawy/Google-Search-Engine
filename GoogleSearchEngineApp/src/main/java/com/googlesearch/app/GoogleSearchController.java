package com.googlesearch.app;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.json.simple.JSONObject;
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
import com.googlesearch.app.general.JsonTemplatesReader;

@RestController
public class GoogleSearchController {
	@RequestMapping(value="/GoogleSearch",method = RequestMethod.POST, consumes="application/json")
	public List<Result> getSearchResults(@RequestBody SearchKeyWord keyWord) throws IOException, GeneralSecurityException
	{
		//read credentials json file
		String filePath="..\\GoogleSearchEngineApp\\src\\main\\resources\\templates\\Credentials.json";
		JSONObject jsonObject = JsonTemplatesReader.jsonFileReader(filePath);		
		String searchQuery = keyWord.getKeyword(); //The query to search
	    String cx = (String) jsonObject.get("CX"); //Your search engine CX
	    String applicationName=(String) jsonObject.get("ApplicationName");// Add the engine name created in google
	    String googleKey=(String) jsonObject.get("GoogleKey"); //Add google generated key 
	    
	    //Instance Custom search
	    Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null) 
	                   .setApplicationName(applicationName) 
	                   .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer(googleKey)) 
	                   .build();

	    //Set search parameter	 
	    Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(cx); 

	    //Execute search
	    Search result = list.execute();
	    	        
		return result.getItems();
	}
}
