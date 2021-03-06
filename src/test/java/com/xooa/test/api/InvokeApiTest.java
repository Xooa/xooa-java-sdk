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

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.response.InvokeResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.WebCalloutResponse;

public class InvokeApiTest {
	
	
	@Test
	public void testInvoke() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("txId", "3df933d87beba75ceebaad7c95e28876acbd783b94e1437d6901c329443dbba3");
		jsonObject.put("payload", "12345");
	    
		WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	    
	    String[] args = {"args1", "12345"};
	    
	    WebService webService = mock(WebService.class);
	    
	    when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set", args)).thenReturn(response);
	    
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	    
	    InvokeResponse invoke = xooaClient.invoke("set", args);
	    
	    assertEquals(jsonObject.getString("txId"), invoke.getTransactionId());
	    assertEquals(jsonObject.getString("payload"), invoke.getPayload());
	}
	
	@Test
	public void testInvoke_RequestTimeout() throws JSONException, XooaApiException {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "3df933d87beba75ceebaad7c95e28876acbd783b94e1437d6901c329443dbba3");
			jsonObject.put("resultId", "12345");
		    
			WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText(jsonObject.toString());
		    response.setResponseCode(202);
		    
		    String[] args = {"args1", "12345"};
		    
		    WebService webService = mock(WebService.class);
		    
		    when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set", args)).thenReturn(response);
		    
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		    
		    xooaClient.invoke("set", args);
		    
		} catch (XooaRequestTimeoutException e) {
			
			assertEquals("3df933d87beba75ceebaad7c95e28876acbd783b94e1437d6901c329443dbba3", e.getResultUrl());
		    assertEquals("12345", e.getResultId());
		}
	}
	
	@Test
	public void testInvoke_APIException() throws JSONException, XooaRequestTimeoutException {
		
		try {
			WebCalloutResponse response = new WebCalloutResponse();
		    response.setResponseText("Exception");
		    response.setResponseCode(400);
		    
		    String[] args = {"args1", "12345"};
		    
		    WebService webService = mock(WebService.class);
		    
		    when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set", args)).thenReturn(response);
		    
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		    
		    xooaClient.invoke("set", args);
		    
		} catch (XooaApiException e) {
			
			assertEquals("Exception", e.getErrorMessage());
		    assertEquals(400, e.getErrorCode());
		}
	}
	
	@Test
	public void testInvoke_Exception() throws JSONException, XooaRequestTimeoutException {
		
		try {
			NullPointerException response = new NullPointerException();
		    
		    String[] args = {"args1", "12345"};
		    
		    WebService webService = mock(WebService.class);
		    
		    when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set", args)).thenThrow(response);
		    
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		    
		    xooaClient.invoke("set", args);
		    
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
	@Test
	public void testInvokeTimeout() throws JSONException, XooaApiException, XooaRequestTimeoutException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("txId", "3df933d87beba75ceebaad7c95e28876acbd783b94e1437d6901c329443dbba3");
		jsonObject.put("payload", "12345");
	        
	    WebCalloutResponse response = new WebCalloutResponse();
	    response.setResponseText(jsonObject.toString());
	    response.setResponseCode(200);
	        
	    String[] args = {"args1", "12345"};
	        
	    WebService webService = mock(WebService.class);
	        
	    when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set?timeout=4000", args)).thenReturn(response);
	        
	    XooaClient xooaClient = new XooaClient();
	    xooaClient.setWebService(webService);
	        
	    InvokeResponse invoke = xooaClient.invoke("set", args, 4000L);
	        
	    assertEquals(jsonObject.getString("txId"), invoke.getTransactionId());
	    assertEquals(jsonObject.getString("payload"), invoke.getPayload());
	}
	
	
	@Test
	public void testInvokeAsync() throws JSONException, XooaApiException {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
		jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
		
		WebCalloutResponse response = new WebCalloutResponse();
		response.setResponseText(jsonObject.toString());
		response.setResponseCode(202);
		
		String[] args = {"args1", "190"};
		
		WebService webService = mock(WebService.class);
		
		when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set?async=true", args)).thenReturn(response);
		
		XooaClient xooaClient = new XooaClient();
		xooaClient.setWebService(webService);
		
		PendingTransactionResponse identity = xooaClient.invokeAsync("set", args);
		
		assertNotNull(identity.getResultId());
		assertNotNull(identity.getResultUrl());
		
	}
	
	@Test
	public void testInvokeAsync_APIException() throws JSONException, XooaApiException {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
			jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
			
			WebCalloutResponse response = new WebCalloutResponse();
			response.setResponseText("Exception");
			response.setResponseCode(400);
			
			String[] args = {"args1", "190"};
			
			WebService webService = mock(WebService.class);
			
			when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set?async=true", args)).thenReturn(response);
			
			XooaClient xooaClient = new XooaClient();
			xooaClient.setWebService(webService);
			
			xooaClient.invokeAsync("set", args);
			
		} catch (XooaApiException e) {
			
			assertEquals("Exception", e.getErrorMessage());
		    assertEquals(400, e.getErrorCode());
		}
	}
	
	@Test
	public void testInvokeAsync_Exception() throws JSONException, XooaRequestTimeoutException {
		
		try {
			NullPointerException response = new NullPointerException();
		    
		    String[] args = {"args1", "12345"};
		    
		    WebService webService = mock(WebService.class);
		    
		    when(webService.makeInvokeCall("https://api.xooa.com/api/v1/invoke/set?async=true", args)).thenThrow(response);
		    
		    XooaClient xooaClient = new XooaClient();
		    xooaClient.setWebService(webService);
		    
		    xooaClient.invokeAsync("set", args);
		    
		} catch (XooaApiException e) {
			
			assertNotNull(e);
		}
	}
	
}