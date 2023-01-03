package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
		
							//#Steps for creating Methods:
							//-Create a client
							//-create a request
							//-create a payload if required
							//-define header
							//-execute the request
	
	
	//1. GET Method Without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();      
			//createDefault() method will create one default client connection and return one closableHttpClient object
		
		HttpGet httpget = new HttpGet(url); 
			//http Get request
			//for GET Method we have one httpGet class 
			//it will generate a Get connection with the Url
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
			//we have .execute method in which we have to pass our required request. here httpget is our get request
			//it will hit the GET URL
			//it will return one closeableHttpResponse. So, everything that we are recieving as a response in postman can easily be achieved by it.
		
		return closeableHttpResponse;
			//because we want response as an output
		
	}
	
	//2. GET Method With Headers:
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); 
			
				//for Headers
				//for hashmap Map util is used
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
			return closeableHttpResponse;
		}
		
		
	//3. POST Method:
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();                       //client created
			HttpPost httppost = new HttpPost(url);												//http post request
			
				//in POST call we have to pass the information to create a new set of data (payload)
				//That's why entityString is given as signature of the post() method
			httppost.setEntity(new StringEntity(entityString));
				
				//for Headers
				//for hashmap Map util is used
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
				
				//hit the request
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);
			return closeableHttpResponse;
		}
}
