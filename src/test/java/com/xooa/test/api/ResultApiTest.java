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

import com.xooa.WebService;
import com.xooa.XooaClient;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.response.BlockResponse;
import com.xooa.response.CurrentBlockResponse;
import com.xooa.response.IdentityResponse;
import com.xooa.response.InvokeResponse;
import com.xooa.response.QueryResponse;
import com.xooa.response.TransactionResponse;
import com.xooa.response.WebCalloutResponse;

public class ResultApiTest {

	
	@Test
	public void testGetResultForInvoke() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("requestType", "Invoke");
			jsonObject.put("payload", "{'payload' : {'result':[{'key':'args1','value':'12345'}],'errors':[]}}");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c180", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        InvokeResponse invoke = xooaClient.getResultForInvoke("0af1fa06-5639-440a-987f-f7ac2587c180");
	        
	        assertEquals(jsonObject.getString("payload"), invoke.getPayload());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForQuery() throws JSONException {
		
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
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c181", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        QueryResponse query = xooaClient.getResultForQuery("0af1fa06-5639-440a-987f-f7ac2587c181");
	        
	        assertEquals(jsonObject.get("payload").toString(), query.getPayload());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForIdentity() throws JSONException {
		
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
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c182", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        IdentityResponse identity = xooaClient.getResultForIdentity("0af1fa06-5639-440a-987f-f7ac2587c182");
	        
	        assertEquals(jsonObject.getString("Xooa"), identity.getIdentityName());
	        assertEquals(jsonObject.getString("Id"), identity.getId());
	        assertEquals(jsonObject.getString("AppId"), identity.getAppId());
	        assertEquals(jsonObject.getString("Access"), identity.getAccessType());
	        assertEquals(jsonObject.getString("canManageIdentities"), identity.isCanManageIdentities());
	        assertEquals(jsonObject.getString("createdAt"), identity.getCreatedAt());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForCurrentBlock() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currentBlockHash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a");
	        jsonObject.put("previousBlockHash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a-vsjbdbcas-ddsafz");
	        jsonObject.put("blockNumber", "20");
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c183", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        CurrentBlockResponse currentBlock = xooaClient.getResultForCurrentBlock("0af1fa06-5639-440a-987f-f7ac2587c183");
	        
	        assertEquals(jsonObject.getString("currentBlockHash"), currentBlock.getCurrentBlockHash());
	        assertEquals(jsonObject.getString("previousBlockHash"), currentBlock.getPreviousBlockHash());
	        assertEquals(jsonObject.getString("blockNumber"), currentBlock.getBlockNumber());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForBlockByNumber() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("previous_hash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a");
	        jsonObject.put("data_hash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a-vsjbdbcas-ddsafz");
	        jsonObject.put("blockNumber", "20");
	        jsonObject.put("numberOfTransactions", "4");
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c184", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        BlockResponse blockResponse = xooaClient.getResultForBlockByNumber("0af1fa06-5639-440a-987f-f7ac2587c184");
	        
	        assertEquals(jsonObject.getString("previous_hash"), blockResponse.getPreviousHash());
	        assertEquals(jsonObject.getString("data_hash"), blockResponse.getDataHash());
	        assertEquals(jsonObject.getString("blockNumber"), blockResponse.getBlockNumber());
	        assertEquals(jsonObject.getString("numberOfTransactions"), blockResponse.getNumberOfTransactions());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForTransaction() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("txid", "7ef699082c475383bfa68f15f1edbec06afb053f59cc0b0f118e72d3a6f3e7eb");
	        jsonObject.put("smartcontract", "kavixooatvw2s9e27");
	        jsonObject.put("creator_msp_id", "XooaMSP");
	        jsonObject.put("type", "ENDORSER_TRANSACTION");
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c185", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        TransactionResponse transactionResponse = xooaClient.getResultForTransaction("0af1fa06-5639-440a-987f-f7ac2587c185");
	        
	        assertEquals(jsonObject.getString("txid"), transactionResponse.getTransactionId());
	        assertEquals(jsonObject.getString("smartcontract"), transactionResponse.getSmartcontract());
	        assertEquals(jsonObject.getString("creator_msp_id"), transactionResponse.getCreatorMspId());
	        assertEquals(jsonObject.getString("type"), transactionResponse.getType());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
}