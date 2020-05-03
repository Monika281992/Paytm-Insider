package ApiTesting.paytmInsider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;




public class date {
	
	@Test
       public void getresponse() throws org.json.simple.parser.ParseException, ParseException {
	   RestAssured.baseURI = "https://apiproxy.paytm.com/v2/movies";
	   RequestSpecification httprequest = RestAssured.given();
	   Response response = httprequest.request(Method.GET,"/upcoming");
	   String responsebody = response.getBody().asString();
	   
	   
	   
	   //Get the current date
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	   Date localdate = new Date(); 
	   System.out.println("Current date " + formatter.format(localdate));
	   
	   // Get upcomingMovieData 
	   JSONParser parser = new JSONParser(); 
	   JSONObject json = (JSONObject) parser.parse(responsebody);
	   JSONArray Movieslist = (JSONArray) json.get("upcomingMovieData");
	   
	   //get Json elements
	   for (Object o : Movieslist) 
	   { JSONObject obj = (JSONObject) o; 
	    Object releaseDate =  obj.get("rank"); 
	    System.out.println(releaseDate);
	 }
	   
	   }}


	
	
	
		
		
	     
	     
	     

	


