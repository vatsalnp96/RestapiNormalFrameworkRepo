package tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import client.RestClient;
import util.TestUtil;

public class GetAPITest extends TestBase {
	
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
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
			//need to call the get() method from RestClient
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url); //because returntype of get() method is closeablehttpresponse
		
				//a. Status Code:
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
					//Achieving integer status code of response
				
				System.out.println("Status Cose------>"+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				
				//b. Json String:
					//we need complete response string. for that we have EntityUtils class
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
					//UTF-8 is the character format, which will give pure string through modification
				
					//converting the response string in JSON format
				JSONObject responseJson = new JSONObject(responseString);
					//JsonObject utility will convert the responsestring in the key=value pair json response
				
				System.out.println("Response JSON from API------>"+responseJson);
				
				//single value assertions
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Value of per page is------>"+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of total is------>"+totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//get the value of Json Array:
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				
				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				
				//c. All Headers:
					//we can also get the header
				Header[] headersArray = closeableHttpResponse.getAllHeaders();
					// .getAllHeaders() method returns on header array
				
					//these headers are in key=value pair so we can convert it into HashMap
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				
				for(Header header : headersArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array------>"+allHeaders);
	}

	
	
	@Test(priority=2)  //for the get requests inwhich we need to give headers
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
			//need to call the get() method from RestClient
		restClient = new RestClient();
		
		//need to generate hashmap object
		HashMap<String, String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "vatsal");
//		
		closeableHttpResponse = restClient.get(url, headerMap); //signature iss changed and now contains hashMap
		
				//a. Status Code:
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
					//Achieving integer status code of response
				
				System.out.println("Status Cose------>"+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				
				//b. Json String:
					//we need complete response string. for that we have EntityUtils class
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
					//UTF-8 is the character format, which will give pure string through modification
				
					//we also need json response
				JSONObject responseJson = new JSONObject(responseString);
					//JsonObject utility will convert the responsestring in the key=value pair json response
				
				System.out.println("Response JSON from API------>"+responseJson);
				
				//single value assertions
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Value of per page is------>"+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Value of total is------>"+totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//get the value of Json Array:
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				
				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				
				//c. All Headers:
					//we can also get the header
				Header[] headersArray = closeableHttpResponse.getAllHeaders();
					// .getAllHeaders() method returns on header array
				
					//these headers are in key=value pair so we can convert it into HashMap
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				
				for(Header header : headersArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array------>"+allHeaders);
	}
}
