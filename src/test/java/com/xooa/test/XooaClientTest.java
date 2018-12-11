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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.response.WebCalloutResponse;

public class XooaClientTest {
	
	@Test
	public void testApiTokenAssignment() {
		
		String apiToken = "example_api_token";
		
		XooaClient xooaClient = new XooaClient(apiToken);
		
		assertEquals(apiToken, xooaClient.getApiToken());
	}
	
	@Test
	public void testCalloutUrlAssignment() {
		
		String appUrl = "https://api.xooa.com/api/v1";
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setAppUrl(appUrl);
		
		assertEquals(xooaClient.getAppUrl(), appUrl);
	}
	
	@Test
	public void testValidationTrue() throws XooaApiException {
		
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
	public void testApiException() {
		
		try {
			
			WebCalloutResponse response = new WebCalloutResponse();
			response.setResponseCode(404);
			
			WebService webService = mock(WebService.class);
			
			when(webService.validateDetails("https://api.xooa.com/api/v1")).thenReturn(response);
			
			XooaClient xooaClient = new XooaClient();
			xooaClient.setWebService(webService);
			
			xooaClient.isValid();
			
			fail("Did not throw an exception even when response code was set to 404");
			
		} catch (XooaApiException e) {
			
			e.printStackTrace();
			
		}
	}
}