package ApiTesting.paytmInsider;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.testng.annotations.Test;

import groovyjarjarcommonscli.ParseException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.opencsv.CSVWriter;
public class Apitesting {
	
	String responsebody = null ;
	
	 @Test
	 public void getresponse() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException, java.text.ParseException{
	 RestAssured.baseURI= "https://apiproxy.paytm.com/v2/movies";
	 
	 //Requestopbject
	 RequestSpecification httprequest = RestAssured.given();
	 
	 //getresponse body
	 Response response = httprequest.request(Method.GET,"/upcoming");
     String responsebody = response.getBody().asString();
     System.out.println("Response body is = " + responsebody); 
     
     //1. Validate Status code
	 
     int statuscode = response.getStatusCode();
	 System.out.println("Status code is " + statuscode);
	 Assert.assertEquals(statuscode, 200);
	   
     //get currrnt date 
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	 Date CurentDate = new Date();
	 System.out.println(formatter.format(CurentDate));

	 //get json value
     JSONParser parser = new JSONParser(); JSONObject json = (JSONObject) parser.parse(responsebody);
     JSONArray Movieslist = (JSONArray) json.get("upcomingMovieData");
     System.out.println(Movieslist);
     for (Object o : Movieslist) 
     { JSONObject obj = (JSONObject) o; 
     String releaseDate =   (String) obj.get("releaseDate");
     String moviename =   (String) obj.get("provider_moviename");
     String paytmMovieCode =   (String) obj.get("paytmMovieCode");
     String moviePosterUrl = (String) obj.get("moviePosterUrl");
     String isContentAvailable = obj.get("isContentAvailable").toString();
     String Language = obj.get("language").toString();
     

     
    // 2. Movie release date: should not be before today’s date
     
    SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
    Date RelDate=formatter1.parse(releaseDate);
         
    Assert.assertTrue(RelDate.after(CurentDate));  //This assertion will fail because releaseDate of last movie is null
     
     
    // 3. Paytm movie code: is unique
     
     List<String> list = Arrays.asList(paytmMovieCode);
	 String[] array = list.toArray(new String[0]);
	 Set <String> hs = new HashSet<String>();
     hs.add(paytmMovieCode);
     Assert.assertEquals(hs.size(), list.size());
     
     // 4. Movie Poster URL: should only have .jpg format
   
     Assert.assertTrue(moviePosterUrl.endsWith(".jpg"));
     
     //5. No movie code should have more than 1 language format
     Assert.assertTrue(!Language.contains(","));
      }}}
    

	
		
	
	 
	 
     