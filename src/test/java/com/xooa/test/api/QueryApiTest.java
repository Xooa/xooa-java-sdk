/**
 * Java SDK for Xooa
 * 
 * Copyright 2018 Xooa
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 * for the specific language governing permissions and limitations under the License.
 * 
 * @author Kavi Sarna
 */

package com.xooa.test.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.QueryResponse;
import com.xooa.response.WebCalloutResponse;

public class QueryApiTest {
	
	@Test
	public void testQuery() throws JSONException {
		
		try {
			
			JSONObject result = new JSONObject();
			result.put("key", "args1");
			result.put("Value", "12345");
			
			JSONArray results = new JSONArray();
			results.put(result);
			
			JSONArray errors = new JSONArray();
			
			JSONObject payload = new JSONObject();
			payload.put("result", results);
			payload.put("errors", errors);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("payload", payload);
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(new Gson().toJson(jsonObject));
	        response.setResponseCode(200);
	        
	        String[] args = {"args1"};
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeQueryCall("https://api.xooa.com/api/v1/query/get", args)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        QueryResponse query = xooaClient.query("get", args);
	        
	        assertEquals(jsonObject.getString("payload"), query.getPayload());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testQueryAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        String[] args = {"args1"};
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        webService.setApiToken("example_api_token");
	        
	        when(webService.makeQueryCall("https://api.xooa.com/api/v1/query/get/?async=true", args)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse query = xooaClient.queryAsync("get", args);
	        
	        assertNotNull(query.getResultId());
			assertNotNull(query.getResultUrl());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
		}
	}
}