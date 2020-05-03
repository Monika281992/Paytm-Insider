package ApiTesting.paytmInsider;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import groovyjarjarcommonscli.ParseException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Apitesting {
	
	
    
	
	   @Test
	 public void getresponse() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException, java.text.ParseException{
	 RestAssured.baseURI= "https://apiproxy.paytm.com/v2/movies";
	 
	 //Requestopbject
	 RequestSpecification httprequest = RestAssured.given();
	 //getresponse body
	 
     Response response = httprequest.request(Method.GET,"/upcoming");
     String responsebody = response.getBody().asString();
     System.out.println("Response body is = " + responsebody);
	 //int statuscode = response.getStatusCode();
	 //System.out.println("Status code is " + statuscode);
	 //Assert.assertEquals(statuscode, 200);
	 
	  //get currrnt date 
	 
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	 Date date = new Date(); 
	 System.out.println(formatter.format(date));
	
	//get json value
	 
	 
     JSONParser parser = new JSONParser(); JSONObject json = (JSONObject) parser.parse(responsebody);
     JSONArray Movieslist = (JSONArray) json.get("upcomingMovieData");
     //System.out.println(Movieslist);
     
     for (Object o : Movieslist) 
     { JSONObject obj = (JSONObject) o; 
     Object releaseDate =   obj.get("provider_moviename");
     


     System.out.println(releaseDate);
     
     //Assert.assertTrue(date.after(releaseDate));
}
     
   }}
	
	  
		  
	     

	 

	
	


	
