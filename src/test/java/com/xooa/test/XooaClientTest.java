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
 * @author Vishal Mullur
 * @author Kavi Sarna
 */

package com.xooa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xooa.IEventListener;
import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.response.WebCalloutResponse;

public class XooaClientTest {
	
	private final String API_TOKEN = "example_api_token";
	private final String APP_URL = "https://api.xooa.com/api/v1";
	
	private final IEventListener LISTENER = new IEventListener() {
		@Override
		public void onUnauthorized(String error) {
			System.out.println(error);
		}
		@Override
		public void onEventReceived(JSONObject jsonObject) {
			System.out.println(jsonObject);
		}
		@Override
		public void onError(String error) {
			System.out.println(error);
		}
		@Override
		public void onConnected(String message) {
			System.out.println(message);
		}
		@Override
		public void onAuthenticated(String message) {
			System.out.println(message);
		}
	};
	
	@Test
	public void testSetApiToken() {
		
		XooaClient client = new XooaClient();
		client.setApiToken(API_TOKEN);
		
		assertEquals(API_TOKEN, client.getApiToken());
	}
	
	@Test
	public void testXooaClient() {
		
		XooaClient xooaClient = new XooaClient(API_TOKEN);
		
		assertEquals(API_TOKEN, xooaClient.getApiToken());
	}
	
	@Test
	public void testXooaClient_String() {
		
		XooaClient xooaClient = new XooaClient(API_TOKEN, APP_URL);
		
		assertEquals(API_TOKEN, xooaClient.getApiToken());
		assertEquals(APP_URL, xooaClient.getAppUrl());
	}
	
	@Test
	public void testXooaClient_EventListener() {
		
		XooaClient xooaClient = new XooaClient(API_TOKEN, LISTENER);
		
		assertEquals(API_TOKEN, xooaClient.getApiToken());
	}
	
	@Test
	public void testSubscribe() {
		
		XooaClient xooaClient = new XooaClient(API_TOKEN, LISTENER);
		
		xooaClient.subscribe();
	}
	
	@Test
	public void testUnsubscribe() {
		
		XooaClient xooaClient = new XooaClient(API_TOKEN, LISTENER);
		
		xooaClient.subscribe();
		
		xooaClient.unsubscribe();	
	}
	
	@Test
	public void testUnsubscribe2() {
		
		try {
			XooaClient xooaClient = new XooaClient(API_TOKEN, LISTENER);
			
			xooaClient.unsubscribe();
		} catch (Exception e) {
			
		}
			
	}
	
	@Test
	public void testCalloutUrlAssignment() {
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setAppUrl(APP_URL);
		
		assertEquals(xooaClient.getAppUrl(), APP_URL);
	}
	
	@Test
	public void testValidation() throws XooaApiException {
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("IdentityName", "Xooa");
        jsonObject.addProperty("canManageIdentities", true);
        jsonObject.addProperty("createdAt", "2018-10-11T07:31:47.380Z");
        jsonObject.addProperty("Id", "7de96d8c-182f-4de3-83f8-955eaaa88f1a");
        
        WebCalloutResponse response = new WebCalloutResponse();
        response.setResponseText(new Gson().toJson(jsonObject));
        response.setResponseCode(200);
        
        WebService webService = mock(WebService.class);
        
        when(webService.validateDetails("https://api.xooa.com/api/v1")).thenReturn(response);
        
        XooaClient xooaClient = new XooaClient();
        xooaClient.setWebService(webService);
        
        boolean result = xooaClient.isValid();
        	
        assertTrue(result);
    }
	
	@Test
	public void testValidationFalse() throws XooaApiException {
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("canManageIdentities", true);
        jsonObject.addProperty("createdAt", "2018-10-11T07:31:47.380Z");
        
        WebCalloutResponse response = new WebCalloutResponse();
        response.setResponseText(new Gson().toJson(jsonObject));
        response.setResponseCode(200);
        
        WebService webService = mock(WebService.class);
        
        when(webService.validateDetails("https://api.xooa.com/api/v1")).thenReturn(response);
        
        XooaClient xooaClient = new XooaClient();
        xooaClient.setWebService(webService);
        
        boolean result = xooaClient.isValid();
        
        assertFalse(result);
        
    }
	
	@Test
	public void testValidationException() throws XooaApiException {
		
		try {
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText("Exception");
	        response.setResponseCode(202);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.validateDetails("https://api.xooa.com/api/v1")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        xooaClient.isValid();
	        
		} catch(XooaApiException e) {
			
			assertEquals(202, e.getErrorCode());
		}
    }
	
	@Test
	public void testApiException() {
		
		try {
			
			WebCalloutResponse response = new WebCalloutResponse();
			response.setResponseCode(404);
			
			WebService webService = mock(WebService.class);
			
			when(webService.validateDetails("https://api.xooa.com/api/v1")).thenReturn(response);
			
			XooaClient xooaClient = new XooaClient();
			xooaClient.setWebService(webService);
			
			xooaClient.isValid();
			
		} catch (XooaApiException e) {
			
			assertEquals(404, e.getErrorCode());
			
		}
	}
}