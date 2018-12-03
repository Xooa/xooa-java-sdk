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
import com.xooa.response.BlockResponse;
import com.xooa.response.CurrentBlockResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.TransactionResponse;
import com.xooa.response.WebCalloutResponse;

public class BlockchainApiTest {
	
	@Test
	public void TestGetCurrentBlock() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currentBlockHash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a");
	        jsonObject.put("previousBlockHash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a-vsjbdbcas-ddsafz");
	        jsonObject.put("blockNumber", 20);
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeBlockchainCall("https://api.xooa.com/api/v1/block/current", "GET")).thenReturn(response);
	        
	        String apiToken = "example_api_token";
	        
	        XooaClient xooaClient = new XooaClient(apiToken);
	        xooaClient.setWebService(webService);
	        
	        CurrentBlockResponse currentBlock = xooaClient.getCurrentBlock();
	        
	        assertEquals(jsonObject.getString("currentBlockHash"), currentBlock.getCurrentBlockHash());
	        assertEquals(jsonObject.getString("previousBlockHash"), currentBlock.getPreviousBlockHash());
	        assertEquals(jsonObject.getLong("blockNumber"), currentBlock.getBlockNumber());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void TestGetCurrentBlockAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeBlockchainCall("https://api.xooa.com/api/v1/block/current/?async=true", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse currentBlock = xooaClient.getCurrentBlockAsync();
	        
	        assertEquals(jsonObject.getString("resultId"), currentBlock.getResultId());
	        assertEquals(jsonObject.getString("resultURL"), currentBlock.getResultUrl());
	        
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		}
	}
	
	
	@Test
	public void TestGetBlockByNumber() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("previous_hash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a");
	        jsonObject.put("data_hash", "7de96d8c-182f-4de3-83f8-955eaaa88f1a-vsjbdbcas-ddsafz");
	        jsonObject.put("blockNumber", 20);
	        jsonObject.put("numberOfTransactions", "4");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeBlockchainCall("https://api.xooa.com/api/v1/block/20", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        BlockResponse blockResponse = xooaClient.getBlockByNumber(20);
	        
	        assertEquals(jsonObject.getString("previous_hash"), blockResponse.getPreviousHash());
	        assertEquals(jsonObject.getString("data_hash"), blockResponse.getDataHash());
	        assertEquals(jsonObject.getLong("blockNumber"), blockResponse.getBlockNumber());
	        assertEquals(jsonObject.getInt("numberOfTransactions"), blockResponse.getNumberOfTransactions());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void TestGetBlockByNumberAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeBlockchainCall("https://api.xooa.com/api/v1/block/20/?async=true", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse currentBlock = xooaClient.getBlockByNumberAsync(20);
	        
	        assertEquals(jsonObject.getString("resultId"), currentBlock.getResultId());
	        assertEquals(jsonObject.getString("resultURL"), currentBlock.getResultUrl());
	        
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			assertNotNull(xae.getErrorMessage());
			
		}
	}
	
	@Test
	public void TestGetTransaction() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("txid", "7ef699082c475383bfa68f15f1edbec06afb053f59cc0b0f118e72d3a6f3e7eb");
	        jsonObject.put("smartcontract", "kavixooatvw2s9e27");
	        jsonObject.put("creator_msp_id", "XooaMSP");
	        jsonObject.put("type", "ENDORSER_TRANSACTION");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeTransactionCall("https://api.xooa.com/api/v1/transaction/7ef699082c475383bfa68f15f1edbec06afb053f59cc0b0f118e72d3a6f3e7eb", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        TransactionResponse transactionResponse = xooaClient.getTransaction("7ef699082c475383bfa68f15f1edbec06afb053f59cc0b0f118e72d3a6f3e7eb");
	        
	        assertEquals(jsonObject.getString("txid"), transactionResponse.getTransactionId());
	        assertEquals(jsonObject.getString("smartcontract"), transactionResponse.getSmartcontract());
	        assertEquals(jsonObject.getString("creator_msp_id"), transactionResponse.getCreatorMspId());
	        assertEquals(jsonObject.getString("type"), transactionResponse.getType());
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	
	@Test
	public void TestGetTransactionAsync() throws JSONException {
		
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("resultURL", "/kavixooatvw2s9e27/results/b5d14976-050c-4b0a-bd49-6be0da258176");
	        jsonObject.put("resultId", "b5d14976-050c-4b0a-bd49-6be0da258176");
	        
	        WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(jsonObject.toString());
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeTransactionCall("https://api.xooa.com/api/v1/transaction/7ef699082c475383bfa68f15f1edbec06afb053f59cc0b0f118e72d3a6f3e7eb/?async=true", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        PendingTransactionResponse currentBlock = xooaClient.getTransactionAsync("7ef699082c475383bfa68f15f1edbec06afb053f59cc0b0f118e72d3a6f3e7eb");
	        
	        assertEquals(jsonObject.getString("resultId"), currentBlock.getResultId());
	        assertEquals(jsonObject.getString("resultURL"), currentBlock.getResultUrl());
	        
	        
		} catch (XooaApiException xae) {
			
			assertNotNull(xae.getErrorCode());
			//assertNotNull(xae.getErrorMessage());
			
		}
	}
}