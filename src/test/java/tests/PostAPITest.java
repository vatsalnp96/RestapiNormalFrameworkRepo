package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import base.TestBase;
import client.RestClient;
import data.Users;

public class PostAPITest extends TestBase {
	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	public void setUP() throws ClientProtocolException, IOException {
		
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		 	// we need https://reqres.in/api/users
		url = serviceUrl + apiUrl;	
	}
	
	
	@Test
	public void postAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String,String>();   //creating hashmap for headers 
		headerMap.put("Content-Type", "application/json");			//inserting headers values individually(if iput value is in xml form than "application/xml"
				
		//The data that we are going to add through POST request is stored in data package.
		//follow notes in datafile
		
		//Jackson API: (need dependency for marshelling)
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Vatsal", "QA");
		
		//Marshelling (Obj---->JSON file)
		mapper.writeValue(new File("C:\\Selenium_Workspace\\RestapiNormalFramework\\src\\main\\java\\data\\Users.json"), users);
		
		//obj--->json in string (As our signature is string value of json, we have to convert it)
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		//enter all 3 signatures in restClient
		closeableHttpResponse = restClient.post(url, userJsonString, headerMap);
		
		
		
				//Validation of response that we get from API:
				//1. staus code:
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Cose------>"+statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201, "Status code is not 201");
				
				//2. JsonString:
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API------>"+responseJson);
				
				
				//Un-Marshelling: (Json----java object)
				Users userResObj = mapper.readValue(responseString, Users.class);// creating obj that contains actual response value
				System.out.println(userResObj);
				
				Assert.assertTrue(users.getName().equals(userResObj.getName()));//verifying the name
				
				Assert.assertTrue(users.getJob().equals(userResObj.getJob()));	//verifying the job
				
				System.out.println(userResObj.getId());
				
				System.out.println(userResObj.getCreatedAt());
		
		
	}
	
	
	
	
	
	
	
}
