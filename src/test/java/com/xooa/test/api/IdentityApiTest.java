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
	public void testGetCurrentIdentity() throws JSONException {
		
		try {
			
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
	        
	        assertEquals(jsonObject.getString("Xooa"), identity.getIdentityName());
	        assertEquals(jsonObject.getString("Id"), identity.getId());
	        assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	        assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	        assertEquals(jsonObject.getString("canManageIdentities"), identity.isCanManageIdentities());
	        assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testEnrollIdentity() throws JSONException {
		
		try {
			
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
	        
	        when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities", "GET", requestString)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        IdentityResponse identity = xooaClient.enrollIdentity(request);
	        
	        assertEquals(jsonObject.getString("Xooa"), identity.getIdentityName());
	        assertEquals(jsonObject.getString("Id"), identity.getId());
	        assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	        assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	        assertEquals(jsonObject.getString("canManageIdentities"), identity.isCanManageIdentities());
	        assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	        assertEquals(jsonObject.getString("ApiToken"), identity.getApiToken());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testEnrollIdentityAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        IdentityRequest request = new IdentityRequest();
	        request.setIdentityName("Xooa");
	        request.setAccessType("r");
	        request.setCanManageIdentities(true);
	        
	        String requestString = new Gson().toJson(request);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/?async=true", "GET", requestString)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse identity = xooaClient.enrollIdentityAsync(request);
	        
	        assertNotNull(identity.getResultId());
			assertNotNull(identity.getResultUrl());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
		}
	}
	
	
	@Test
	public void testRegenerateIdentityApiToken() throws JSONException {
		
		try {
			
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
	        
	        when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556/regeneratetoken", "GET", null)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        IdentityResponse identity = xooaClient.regenerateIdentityApiToken("248691b7-2cee-4087-8306-2aa6d23ca556");
	        
	        assertEquals(jsonObject.getString("Xooa"), identity.getIdentityName());
	        assertEquals(jsonObject.getString("Id"), identity.getId());
	        assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	        assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	        assertEquals(jsonObject.getString("canManageIdentities"), identity.isCanManageIdentities());
	        assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	        assertEquals(jsonObject.getString("ApiToken"), identity.getApiToken());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testRegenerateIdentityApiTokenAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556/regeneratetoken/?async=true", "GET", null)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse identity = xooaClient.regenerateIdentityApiTokenAsync("248691b7-2cee-4087-8306-2aa6d23ca556");
	        
	        assertNotNull(identity.getResultId());
			assertNotNull(identity.getResultUrl());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
		}
	}
	
	@Test
	public void testGetIdentity() throws JSONException {
		
		try {
			
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
	        
	        assertEquals(jsonObject.getString("Xooa"), identity.getIdentityName());
	        assertEquals(jsonObject.getString("Id"), identity.getId());
	        assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	        assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	        assertEquals(jsonObject.getString("canManageIdentities"), identity.isCanManageIdentities());
	        assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testGetIdentities() throws JSONException {
		
		try {
			
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
	        
	        assertEquals(jsonObject.getString("Xooa"), identity.getIdentityName());
	        assertEquals(jsonObject.getString("Id"), identity.getId());
	        assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	        assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	        assertEquals(jsonObject.getString("canManageIdentities"), identity.isCanManageIdentities());
	        assertEquals(identity.getCreatedAt(), jsonObject.getString("createdAt"));
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testDeleteIdentity() throws JSONException {
		
		try {
			
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
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void testDeleteIdentityAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeIdentityCall("https://api.xooa.com/api/v1/identities/248691b7-2cee-4087-8306-2aa6d23ca556/?async=true", "DELETE", null)).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse identity = xooaClient.deleteIdentityAsync("248691b7-2cee-4087-8306-2aa6d23ca556");
	        
	        assertNotNull(identity.getResultId());
			assertNotNull(identity.getResultUrl());
	        
		} catch (XooaApiException xae) {
			
			xae.printStackTrace();
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
		}
	}
}