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

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.request.IdentityRequest;
import com.xooa.response.IdentityResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.WebCalloutResponse;

public class IdentityApiTest {
	
	@Test
	public void testGetCurrentIdentity() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/me", "GET", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    IdentityResponse identity = xooaClient.getCurrentIdentity();
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	}
	
	@Test
	public void testGetCurrentIdentityAttributes() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject attribute = new JSONObject();
		attribute.put("name", "Name");
		attribute.put("value", "value");
		attribute.put("ecert", false);
		
		JSONArray array = new JSONArray();
		array.put(attribute);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	    jsonObject.put("Attrs", array);
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/me", "GET", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    IdentityResponse identity = xooaClient.getCurrentIdentity();
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	}
	
	@Test
	public void testGetCurrentIdentity_ParseException() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		try {
			JSONObject attribute = new JSONObject();
			attribute.put("name", "Name");
			attribute.put("value", "value");
			attribute.put("ecert", false);
			
			JSONArray array = new JSONArray();
			array.put(attribute);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("IdentityName", "Xooa");
		    jsonObject.put("Access", "r");
		    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
		    jsonObject.put("AppId", "kavixooatvw2s9e27");
		    jsonObject.put("canManageIdentities", true);
		    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
		    jsonObject.put("Attrs", array);
		        
		    WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText("{," + jsonObject.toString());
		    response.setResponseCode(200);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/me", "GET", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getCurrentIdentity();
		    
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	@Test
	public void testGetCurrentIdentity_RequestTimeout() throws JSONException, XooaApiException {
		
		try {
						
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "Xooa");
		    jsonObject.put("resultId", "r");
		        
		    WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText(jsonObject.toString());
		    response.setResponseCode(202);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/me", "GET", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getCurrentIdentity();
		    
		} catch (XooaRequestTimeoutException e) {
			
			assertEquals("Xooa", e.getResultUrl());
			assertEquals("r", e.getResultId());
		}
	}
	
	@Test
	public void testGetCurrentIdentity_APIException() throws JSONException, XooaRequestTimeoutException {
		
		try {
						
			WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText("Exception");
		    response.setResponseCode(402);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/me", "GET", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getCurrentIdentity();
		    
		} catch (XooaApiException e) {
			
			assertEquals("Exception", e.getErrorMessage());
			assertEquals(402, e.getErrorCode());
		}
	}
	
	@Test
	public void testGetCurrentIdentity_Exception() throws JSONException, XooaRequestTimeoutException {
		
		try {
						
			NullPointerException exception = new NullPointerException();
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/me", "GET", null)).thenThrow(exception);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getCurrentIdentity();
		    
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	
	@Test
	public void testEnrollIdentity() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	    jsonObject.put("ApiToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcGlLZXkiOiJLN0cxMDNILUJGN01TNFYtTlFBMlFOTi1BRkhNVFE4IiwiQXBpU2VjcmV0IjoiWXJxUEFER29HTUR3V0lzIiwiUGFzc3BocmFzZSI6IjBlMjk5ZTcxZTUw");
	    
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	    
	    IdentityRequest request = new IdentityRequest();
	    request.setIdentityName("Xooa");
	    request.setAccessType("r");
	    request.setCanManageIdentities(true);
	    
	    String requestString = new Gson().toJson(request);
	    
	    WebService webService = mock(WebService.class);
	    
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities", "POST", requestString)).thenReturn(response);
	    
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	    
	    IdentityResponse identity = xooaClient.enrollIdentity(request);
	    
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	    assertEquals(jsonObject.getString("ApiToken"), identity.getApiToken());
	}
	
	@Test
	public void testEnrollIdentity_Timeout() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	    jsonObject.put("ApiToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcGlLZXkiOiJLN0cxMDNILUJGN01TNFYtTlFBMlFOTi1BRkhNVFE4IiwiQXBpU2VjcmV0IjoiWXJxUEFER29HTUR3V0lzIiwiUGFzc3BocmFzZSI6IjBlMjk5ZTcxZTUw");
	    
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	    
	    IdentityRequest request = new IdentityRequest();
	    request.setIdentityName("Xooa");
	    request.setAccessType("r");
	    request.setCanManageIdentities(true);
	    
	    String requestString = new Gson().toJson(request);
	    
	    WebService webService = mock(WebService.class);
	    
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/?timeout=4000", "POST", requestString)).thenReturn(response);
	    
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	    
	    IdentityResponse identity = xooaClient.enrollIdentity(request, 4000);
	    
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	    assertEquals(jsonObject.getString("ApiToken"), identity.getApiToken());
	}
	
	
	@Test
	public void testEnrollIdentityAsync() throws JSONException, XooaApiException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
		jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
		
		WebCalloutResponse response = new WebCalloutResponse();
		response.setResponseText(jsonObject.toString());
		response.setResponseCode(202);
		
		IdentityRequest request = new IdentityRequest();
		request.setIdentityName("Xooa");
		request.setAccessType("r");
		request.setCanManageIdentities(true);
		
		String requestString = new Gson().toJson(request);
		
		WebService webService = mock(WebService.class);
		
		when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/?async=true", "POST", requestString)).thenReturn(response);
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setWebService(webService);
		
		PendingTransactionResponse identity = xooaClient.enrollIdentityAsync(request);
		
		assertNotNull(identity.getResultId());
		assertNotNull(identity.getResultUrl());
		
	}
	
	@Test
	public void testEnrollIdentityAsync_APIException() throws JSONException, XooaApiException {
		
		try {
			WebCalloutResponse response = new WebCalloutResponse();
			response.setResponseText("Exception");
			response.setResponseCode(300);
			
			IdentityRequest request = new IdentityRequest();
			request.setIdentityName("Xooa");
			request.setAccessType("r");
			request.setCanManageIdentities(true);
			
			String requestString = new Gson().toJson(request);
			
			WebService webService = mock(WebService.class);
			
			when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/?async=true", "POST", requestString)).thenReturn(response);
			
			XooaClient xooaClient = new XooaClient();
			xooaClient.setWebService(webService);
			
			xooaClient.enrollIdentityAsync(request);
			
		} catch (XooaApiException e) {
			
			assertEquals("Exception", e.getErrorMessage());
			assertEquals(300, e.getErrorCode());
		}
	}
	
	@Test
	public void testEnrollIdentityAsync_Exception() throws JSONException, XooaApiException {
		
		try {
			NullPointerException exception = new NullPointerException();
			
			IdentityRequest request = new IdentityRequest();
			request.setIdentityName("Xooa");
			request.setAccessType("r");
			request.setCanManageIdentities(true);
			
			String requestString = new Gson().toJson(request);
			
			WebService webService = mock(WebService.class);
			
			when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/?async=true", "POST", requestString)).thenThrow(exception);
			
			XooaClient xooaClient = new XooaClient();
			xooaClient.setWebService(webService);
			
			xooaClient.enrollIdentityAsync(request);
			
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	
	@Test
	public void testRegenerateIdentityApiToken() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	    jsonObject.put("ApiToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcGlLZXkiOiJLN0cxMDNILUJGN01TNFYtTlFBMlFOTi1BRkhNVFE4IiwiQXBpU2VjcmV0IjoiWXJxUEFER29HTUR3V0lzIiwiUGFzc3BocmFzZSI6IjBlMjk5ZTcxZTUw");
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	       
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556/regeneratetoken", "POST", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    IdentityResponse identity = xooaClient.regenerateIdentityApiToken("248691b7-2cee-4087-8306-2aa6d23ca556");
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	    assertEquals(jsonObject.getString("ApiToken"), identity.getApiToken());
	}
	
	@Test
	public void testRegenerateIdentityApiTokenTimeout() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	    jsonObject.put("ApiToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcGlLZXkiOiJLN0cxMDNILUJGN01TNFYtTlFBMlFOTi1BRkhNVFE4IiwiQXBpU2VjcmV0IjoiWXJxUEFER29HTUR3V0lzIiwiUGFzc3BocmFzZSI6IjBlMjk5ZTcxZTUw");
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	       
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556/regeneratetoken/?timeout=4000", "POST", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    IdentityResponse identity = xooaClient.regenerateIdentityApiToken("248691b7-2cee-4087-8306-2aa6d23ca556", 4000);
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	    assertEquals(jsonObject.getString("ApiToken"), identity.getApiToken());
	}
	
	
	@Test
	public void testRegenerateIdentityApiTokenAsync() throws JSONException, XooaApiException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
		jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
		
		WebCalloutResponse response = new WebCalloutResponse();
		response.setResponseText(jsonObject.toString());
		response.setResponseCode(202);
		
		WebService webService = mock(WebService.class);
		
		when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556/regeneratetoken?async=true", "POST", null)).thenReturn(response);
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setWebService(webService);
		
		PendingTransactionResponse identity = xooaClient.regenerateIdentityApiTokenAsync("248691b7-2cee-4087-8306-2aa6d23ca556");
		
		assertNotNull(identity.getResultId());
		assertNotNull(identity.getResultUrl());
		
	}
	
	@Test
	public void testGetIdentity() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556", "GET", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    IdentityResponse identity = xooaClient.getIdentity("248691b7-2cee-4087-8306-2aa6d23ca556");
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	}
	
	@Test
	public void testGetIdentities() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	        
	    JSONArray array = new JSONArray();
	    array.put(jsonObject);
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(array.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/", "GET", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    List<IdentityResponse> identities = xooaClient.getIdentities();
	    IdentityResponse identity = identities.get(0);
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(identity.getCreatedAt(), jsonObject.getString("createdAt"));
	}
	
	@Test
	public void testGetIdentitiesAttributes() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject attributesJson = new JSONObject();
	    attributesJson.put("name", "Name");
	    attributesJson.put("value", "Value");
	    attributesJson.put("ecert", false);
	        
	    JSONArray attributesArray = new JSONArray();
	    attributesArray.put(attributesJson);
			
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("IdentityName", "Xooa");
	    jsonObject.put("Access", "r");
	    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
	    jsonObject.put("AppId", "kavixooatvw2s9e27");
	    jsonObject.put("canManageIdentities", true);
	    jsonObject.put("createdAt", "2018-10-11T07:31:47.380Z");
	    jsonObject.put("Attrs", attributesArray);
	        
	    JSONArray array = new JSONArray();
	    array.put(jsonObject);
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(array.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/", "GET", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    List<IdentityResponse> identities = xooaClient.getIdentities();
	    IdentityResponse identity = identities.get(0);
	        
	    assertEquals(jsonObject.getString("IdentityName"), identity.getIdentityName());
	    assertEquals(jsonObject.getString("Id"), identity.getId());
	    assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	    assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	    assertEquals(jsonObject.getBoolean("canManageIdentities"), identity.isCanManageIdentities());
	    assertEquals(identity.getCreatedAt(), jsonObject.getString("createdAt"));
	}
	
	@Test
	public void testGetIdentities_ParseException() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("IdentityName", "Xooa");
		    jsonObject.put("Access", "r");
		    jsonObject.put("Id", "248691b7-2cee-4087-8306-2aa6d23ca556");
		    jsonObject.put("AppId", "kavixooatvw2s9e27");
		    jsonObject.put("canManageIdentities", true);
		    jsonObject.put("createdAt", "{2018-10-11T07:31:47.380Z");
		        
		    JSONArray array = new JSONArray();
		    array.put(jsonObject);
		        
		    WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText("{," + array.toString()+ "]");
		    response.setResponseCode(200);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/", "GET", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getIdentities();
		    
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	@Test
	public void testGetIdentities_RequestTimeout() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "Xooa");
		    jsonObject.put("resultId", "r");
		    
		    WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText(jsonObject.toString());
		    response.setResponseCode(202);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/", "GET", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getIdentities();
		    
		} catch (XooaRequestTimeoutException e) {
			
			assertEquals("r", e.getResultId());
			assertEquals("Xooa", e.getResultUrl());
		}
	}
	
	@Test
	public void testGetIdentities_APIException() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		try {
			WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText("Exception");
		    response.setResponseCode(400);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/", "GET", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getIdentities();
		    
		} catch (XooaApiException e) {
			
			assertEquals("Exception", e.getErrorMessage());
			assertEquals(400, e.getErrorCode());
		}
	}
	
	@Test
	public void testGetIdentities_Exception() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		try {
			NullPointerException exception = new NullPointerException();
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/", "GET", null)).thenThrow(exception);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.getIdentities();
		    
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	
	@Test
	public void testDeleteIdentity() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("deleted", true);
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556", "DELETE", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    boolean identity = xooaClient.deleteIdentity("248691b7-2cee-4087-8306-2aa6d23ca556");
	        
	    assertEquals(jsonObject.getBoolean("deleted"), identity);
	}
	
	@Test
	public void testDeleteIdentity_RequestTimeout() throws JSONException, XooaApiException {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "Xooa");
			jsonObject.put("resultId", "xooa");
		        
		    WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText(jsonObject.toString());
		    response.setResponseCode(202);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556", "DELETE", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.deleteIdentity("248691b7-2cee-4087-8306-2aa6d23ca556");
		   
		} catch (XooaRequestTimeoutException e) {
			
			assertEquals("Xooa", e.getResultUrl());
			assertEquals("xooa", e.getResultId());
		}
	}
	
	@Test
	public void testDeleteIdentity_APIException() throws JSONException, XooaRequestTimeoutException {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "Xooa");
			jsonObject.put("resultId", "xooa");
		        
		    WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText("Exception");
		    response.setResponseCode(400);
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556", "DELETE", null)).thenReturn(response);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.deleteIdentity("248691b7-2cee-4087-8306-2aa6d23ca556");
		   
		} catch (XooaApiException e) {
			
			assertEquals("Exception", e.getErrorMessage());
			assertEquals(400, e.getErrorCode());
		}
	}
	
	@Test
	public void testDeleteIdentity_Exception() throws JSONException, XooaRequestTimeoutException {
		
		try {
			NullPointerException exception = new NullPointerException();
		        
		    WebService webService = mock(WebService.class);
		        
		    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556", "DELETE", null)).thenThrow(exception);
		        
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		        
		    xooaClient.deleteIdentity("248691b7-2cee-4087-8306-2aa6d23ca556");
		   
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	@Test
	public void testDeleteIdentityTimeout() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("deleted", true);
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556?timeout=4000", "DELETE", null)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    boolean identity = xooaClient.deleteIdentity("248691b7-2cee-4087-8306-2aa6d23ca556", 4000);
	        
	    assertEquals(jsonObject.getBoolean("deleted"), identity);
	}
	
	
	@Test
	public void testDeleteIdentityAsync() throws JSONException, XooaApiException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
		jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
		
		WebCalloutResponse response = new WebCalloutResponse();
		response.setResponseText(jsonObject.toString());
		response.setResponseCode(202);
		
		WebService webService = mock(WebService.class);
		
		when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556?async=true", "DELETE", null)).thenReturn(response);
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setWebService(webService);
		
		PendingTransactionResponse identity = xooaClient.deleteIdentityAsync("248691b7-2cee-4087-8306-2aa6d23ca556");
		
		assertNotNull(identity.getResultId());
		assertNotNull(identity.getResultUrl());
	
	}
}