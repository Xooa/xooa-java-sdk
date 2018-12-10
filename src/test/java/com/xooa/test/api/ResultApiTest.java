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
	public void testGetResultForInvoke() throws JSONException, XooaApiException {
		
		try {
			
			String res = "{\"requestType\": \"invoke\", \"result\": {\"txId\": \"0edd83dac5049995bbc01e2132ee7d29c38fb692127357f28d60b5690439ed90\"," + 
					"\"payload\": \"{\\\"result\\\":[{\\\"key\\\":\\\"a\\\",\\\"value\\\":\\\"c\\\"}],\\\"errors\\\":[]}\"}}";
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(res);
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class);
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c180", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        InvokeResponse invoke = xooaClient.getResultForInvoke("0af1fa06-5639-440a-987f-f7ac2587c180");
	        
	        assertEquals("{\"result\":[{\"key\":\"a\",\"value\":\"c\"}],\"errors\":[]}", invoke.getPayload());
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForQuery() throws JSONException, XooaApiException {
		
		try {
			
			String resp = "{\"requestType\": \"query\",\"result\":{\"payload\":{\"result\":[{\"key\": \"a\",\"value\":\"c\"}],\"errors\":[]}}}";
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(resp);
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c181", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        QueryResponse query = xooaClient.getResultForQuery("0af1fa06-5639-440a-987f-f7ac2587c181");
	        
	        assertEquals("{\"payload\":{\"result\":[{\"value\":\"c\",\"key\":\"a\"}],\"errors\":[]}}", query.getPayload());
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForIdentity() throws JSONException, XooaApiException {
		
		try {
			
			String res = "{\n" + 
					"  \"requestType\": \"identity\",\n" + 
					"  \"result\": {\n" + 
					"    \"Id\": \"74bb91b5-bb2a-493e-b2a1-bbc312fae5f4\",\n" + 
					"    \"IdentityName\": \"string\",\n" + 
					"    \"Access\": \"r\",\n" + 
					"    \"Attrs\": [\n" + 
					"      {\n" + 
					"        \"name\": \"string\",\n" + 
					"        \"ecert\": true,\n" + 
					"        \"value\": \"string\"\n" + 
					"      }\n" + 
					"    ],\n" + 
					"    \"canManageIdentities\": true,\n" + 
					"    \"createdAt\": \"2018-12-10T08:25:57.570Z\",\n" + 
					"    \"ApiToken\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcGlLZXkiOiJFSlhTM0RCLVFDTjRKRk0tUEFHVlFHVC0yQlhFQlg1IiwiQXBpU2VjcmV0Ijoib1h0OHN2QzlRbHZXcG1WIiwiUGFzc3BocmFzZSI6IjU2ZGNiZDE3ZmE4ZjdjYzE5MmM2OTE0N2M1YzcyNzhiIiwiaWF0IjoxNTQ0NDMwMzU3fQ.6ZTSIr6r9V6FqqM54R5cRov_4D9UBgVbkug_KZ2z_Q0\",\n" + 
					"    \"AppId\": \"kavixooatvw2s9e27\",\n" + 
					"    \"AppName\": \"Get-Set-Del\"\n" + 
					"  }\n" + 
					"}";
			
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(res);
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c182", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        IdentityResponse identity = xooaClient.getResultForIdentity("0af1fa06-5639-440a-987f-f7ac2587c182");
	        
	        assertEquals("string", identity.getIdentityName());
	        assertEquals("74bb91b5-bb2a-493e-b2a1-bbc312fae5f4", identity.getId());
	        assertEquals("kavixooatvw2s9e27", identity.getAppId());
	        assertEquals("r", identity.getAccessType());
	        assertEquals(true, identity.isCanManageIdentities());
	        
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForCurrentBlock() throws JSONException, XooaApiException {
		
		try {
			String res = "{\n" + 
					"  \"requestType\": \"currentBlock\",\n" + 
					"  \"result\": {\n" + 
					"    \"currentBlockHash\": \"f13c57b882a361b873e4e00d4accc13fa5d0223ba03b78d1f33c6ffbeb6139ac\",\n" + 
					"    \"previousBlockHash\": \"b57c5375d01c159486aa401c144d4f64e4999600b0e561c6231d92aafb3fd9ca\",\n" + 
					"    \"blockNumber\": 116\n" + 
					"  }\n" + 
					"}";
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(res);
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c183", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        CurrentBlockResponse currentBlock = xooaClient.getResultForCurrentBlock("0af1fa06-5639-440a-987f-f7ac2587c183");
	        
	        assertEquals("f13c57b882a361b873e4e00d4accc13fa5d0223ba03b78d1f33c6ffbeb6139ac", currentBlock.getCurrentBlockHash());
	        assertEquals("b57c5375d01c159486aa401c144d4f64e4999600b0e561c6231d92aafb3fd9ca", currentBlock.getPreviousBlockHash());
	        assertEquals(116, currentBlock.getBlockNumber());
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForBlockByNumber() throws JSONException, XooaApiException {
		
		try {
			
			String res = "{\n" + 
					"  \"requestType\": \"blockByNumber\",\n" + 
					"  \"result\": {\n" + 
					"    \"previous_hash\": \"8946410bd4a65ca7362883fc2a38024b7aaee5898ef8123fee756f4d990d7d8e\",\n" + 
					"    \"data_hash\": \"e228d0a334ddade49d49dd6e9fbdacae9beaee10cbd85d96d1fa40826df46282\",\n" + 
					"    \"numberOfTransactions\": 1,\n" + 
					"    \"blockNumber\": 10\n" + 
					"  }\n" + 
					"}";
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(res);
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c184", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        BlockResponse blockResponse = xooaClient.getResultForBlockByNumber("0af1fa06-5639-440a-987f-f7ac2587c184");
	        
	        assertEquals("8946410bd4a65ca7362883fc2a38024b7aaee5898ef8123fee756f4d990d7d8e", blockResponse.getPreviousHash());
	        assertEquals("e228d0a334ddade49d49dd6e9fbdacae9beaee10cbd85d96d1fa40826df46282", blockResponse.getDataHash());
	        assertEquals(10, blockResponse.getBlockNumber());
	        assertEquals(1, blockResponse.getNumberOfTransactions());
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
	
	@Test
	public void testGetResultForTransaction() throws JSONException, XooaApiException {
		
		try {
			String res = "{\n" + 
					"  \"requestType\": \"getByTransactionId\",\n" + 
					"  \"result\": {\n" + 
					"    \"txid\": \"e9703a826356d3958384bfe83e25e9b9a5f387882fcfeb754de40eb1eb53ca01\",\n" + 
					"    \"createdt\": \"2018-12-10T08:33:14.459Z\",\n" + 
					"    \"smartcontract\": \"kavixooatvw2s9e27\",\n" + 
					"    \"creator_msp_id\": \"XooaMSP\",\n" + 
					"    \"endorser_msp_id\": [\n" + 
					"      \"XooaMSP\"\n" + 
					"    ],\n" + 
					"    \"type\": \"ENDORSER_TRANSACTION\",\n" + 
					"    \"read_set\": [\n" + 
					"      {\n" + 
					"        \"chaincode\": \"kavixooatvw2s9e27\",\n" + 
					"        \"set\": []\n" + 
					"      },\n" + 
					"      {\n" + 
					"        \"chaincode\": \"lscc\",\n" + 
					"        \"set\": [\n" + 
					"          {\n" + 
					"            \"key\": \"kavixooatvw2s9e27\",\n" + 
					"            \"version\": {\n" + 
					"              \"block_num\": \"66\",\n" + 
					"              \"tx_num\": \"0\"\n" + 
					"            }\n" + 
					"          }\n" + 
					"        ]\n" + 
					"      }\n" + 
					"    ],\n" + 
					"    \"write_set\": [\n" + 
					"      {\n" + 
					"        \"chaincode\": \"kavixooatvw2s9e27\",\n" + 
					"        \"set\": [\n" + 
					"          {\n" + 
					"            \"key\": \"a\",\n" + 
					"            \"is_delete\": false,\n" + 
					"            \"value\": \"c\"\n" + 
					"          }\n" + 
					"        ]\n" + 
					"      },\n" + 
					"      {\n" + 
					"        \"chaincode\": \"lscc\",\n" + 
					"        \"set\": []\n" + 
					"      }\n" + 
					"    ]\n" + 
					"  }\n" + 
					"}";
			
			WebCalloutResponse response = new WebCalloutResponse();
	        response.setResponseText(res);
	        response.setResponseCode(200);
	        
	        WebService webService = mock(WebService.class, withSettings().useConstructor("example_api_token"));
	        
	        when(webService.makeResultCall("https://api.xooa.com/api/v1/results/0af1fa06-5639-440a-987f-f7ac2587c185", "GET")).thenReturn(response);
	        
	        XooaClient xooaClient = new XooaClient();
	        xooaClient.setWebService(webService);
	        
	        TransactionResponse transactionResponse = xooaClient.getResultForTransaction("0af1fa06-5639-440a-987f-f7ac2587c185");
	        
	        assertEquals("e9703a826356d3958384bfe83e25e9b9a5f387882fcfeb754de40eb1eb53ca01", transactionResponse.getTransactionId());
	        assertEquals("kavixooatvw2s9e27", transactionResponse.getSmartcontract());
	        assertEquals("XooaMSP", transactionResponse.getCreatorMspId());
	        assertEquals("ENDORSER_TRANSACTION", transactionResponse.getType());
	        
		} catch (XooaRequestTimeoutException xrte) {
			
			assertNotNull(xrte.getResultId());
			assertNotNull(xrte.getResultUrl());
		}
	}
}