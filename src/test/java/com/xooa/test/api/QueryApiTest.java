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

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.QueryResponse;
import com.xooa.response.WebCalloutResponse;

public class QueryApiTest {
	
	@Test
	public void testQuery() throws JSONException, XooaApiException {
		
		try {
			
			String res = "{\"payload\":{\"result\":[{\"key\": \"a\", \"value\": \"c\"}], \"errors\": []}}";
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(res);
	        response.setResponseCode(200);
	        
	        String[] args = {"args1"};
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeQueryCall("https://api.xooa.com/api/v1/query/get", args)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        QueryResponse query = xooaClient.query("get", args);
	        
	        assertEquals("{\"result\":[{\"value\":\"c\",\"key\":\"a\"}],\"errors\":[]}", query.getPayload());
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testQueryAsync() throws JSONException, XooaApiException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
		jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
		
		WebCalloutResponse response = new WebCalloutResponse();
		response.setResponseText(jsonObject.toString());
		response.setResponseCode(202);
		
		String[] args = {"args1"};
		
		WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
		webService.setApiToken("example_api_token");
		
		when(webService.makeQueryCall("https://api.xooa.com/api/v1/query/get?async=true", args)).thenReturn(response);
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setWebService(webService);
		
		PendingTransactionResponse query = xooaClient.queryAsync("get", args);
		
		assertNotNull(query.getResultId());
		assertNotNull(query.getResultUrl());
		
	}
}