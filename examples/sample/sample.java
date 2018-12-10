/**
 * Xooa Java SDK usage example
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

package com.xooa;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.request.Attributes;
import com.xooa.request.IdentityRequest;
import com.xooa.response.IdentityResponse;
import com.xooa.response.InvokeResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.QueryResponse;

public class sample {

	public static void main(String ...args) throws InterruptedException {
		
		String apiToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBcGlLZXkiOiJFMVpBQVNBLVBZU01WQkstS1BGM0JRUy1BMVQ1NVRFIiwiQXBpU2VjcmV0IjoicXNYMTZuUkhBNVQyaWFLIiwiUGFzc3BocmFzZSI6IjAyZGJiM2U1YTlhNDg5YjYyNTYwYjY2YjdhMzcxNmE1IiwiaWF0IjoxNTQ0MDkzMTA0fQ.yqiE9SEo_fXBE3SbJ7AZ7FDOgPRSwhjkcl8aQ81ZoHY";
		
		XooaClient xooaClient = new XooaClient(apiToken, new IEventListener() {
			
			@Override
			public void onUnauthorized(String error) {
				
				System.out.println("UnAuthorized");
				
			}
			
			@Override
			public void onEventReceived(JSONObject jsonObject) {
				
				System.out.println("event - " + jsonObject.toString());
				
			}
			
			@Override
			public void onError(String error) {
				
				System.out.println(error);
				
			}
			
			@Override
			public void onConnected(String message) {
				
				System.out.println("connected - " + message);
				
			}
			
			@Override
			public void onAuthenticated(String message) {
				
				System.out.println("Authorized - " + message);
				
			}
		});
		
		try {
			
			//xooaClient.subscribe();
			
			System.out.println("----- Start -----");
			
			System.out.println();
			
			System.out.println("----- Current Identity -----");
			
			IdentityResponse currentIdentity = xooaClient.getCurrentIdentity();
			currentIdentity.display();
			
			System.out.println();
			
			System.out.println("----- All Identities -----");
			
			for (IdentityResponse ir : xooaClient.getIdentities()) {
				
				ir.display();
				
				System.out.println();
				System.out.println();
			}
			
			System.out.println();
			
			System.out.println("----- New Identity -----");
			
			Attributes attribute = new Attributes();
			attribute.setName("Name");
			attribute.setValue("Value");
			attribute.setEcert(false);
			
			List<Attributes> list = new ArrayList<>();
			list.add(attribute);
			
			IdentityRequest identityRequest = new IdentityRequest();
			identityRequest.setIdentityName("Xooa");
			identityRequest.setCanManageIdentities(true);
			identityRequest.setAccessType("r");
			identityRequest.setAttributes(list);
			
			IdentityResponse newIdentity = xooaClient.enrollIdentity(identityRequest);
			newIdentity.display();
			
			System.out.println();
			
			System.out.println("----- Pending New Identity -----");
			
			PendingTransactionResponse pendingIdentityResponse = xooaClient.enrollIdentityAsync(identityRequest);
			pendingIdentityResponse.display();
			
			System.out.println();
			Thread.sleep(3000);
			
			System.out.println("----- Pending New Identity Details -----");
			
			IdentityResponse newIdentity2 = xooaClient.getResultForIdentity(pendingIdentityResponse.getResultId());
			newIdentity2.display();
			
			System.out.println();
			
			System.out.println("----- Regenerate New Token -----");
			
			IdentityResponse newTokenIdentity = xooaClient.regenerateIdentityApiToken(newIdentity.getId());
			newTokenIdentity.display();
			
			System.out.println();
			
			System.out.println("----- Pending Regenerate New API Token -----");
			
			PendingTransactionResponse newIdentityTokenResponse = xooaClient.regenerateIdentityApiTokenAsync(newIdentity.getId());
			newIdentityTokenResponse.display();
			
			System.out.println();
			Thread.sleep(1000);
			
			System.out.println("----- Pending Regenerate Token Response -----");
			
			xooaClient.getResultForIdentity(newIdentityTokenResponse.getResultId()).display();
			
			System.out.println();
			
			System.out.println("----- Get Identity Details -----");
			
			IdentityResponse getIdentity = xooaClient.getIdentity(newIdentity.getId());
			getIdentity.display();
			
			System.out.println();
			Thread.sleep(2000);
			
			System.out.println("----- Delete Identity -----");
			
			boolean deleteIdentity = xooaClient.deleteIdentity(newIdentity.getId());
			System.out.println(deleteIdentity);
			
			System.out.println();
			Thread.sleep(4000);
			
			System.out.println("----- Pending Delete Identity -----");
			
			PendingTransactionResponse deleteResponse = xooaClient.deleteIdentityAsync(newIdentity2.getId());
			deleteResponse.display();
			Thread.sleep(4000);
			
			System.out.println();
			
			System.out.println("----- Pending Delete Identity Details -----");
			
			boolean delete = xooaClient.getResultForIdentityDelete(deleteResponse.getResultId());
			System.out.println(delete);
			
			System.out.println();
			
			System.out.println("----- Invoke Arguments -----");
			
			String[] invokeArguments = {"args1", "x100", "args2", "x200"};
			InvokeResponse invokeResponse = xooaClient.invoke("set", invokeArguments);
			invokeResponse.display();
			
			System.out.println();
			
			System.out.println("----- Transaction Details -----");
			
			xooaClient.getTransaction(invokeResponse.getTransactionId()).display();;
			
			System.out.println();
			
			System.out.println("----- Query Arguments -----");
			
			String queryArguments[] = {"args1"};
			QueryResponse queryResponse = xooaClient.query("get", queryArguments);
			queryResponse.display();
			
			System.out.println();
			
			System.out.println("----- Current Block -----");
			
			xooaClient.getCurrentBlock().display();
			
			System.out.println();
			
			System.out.println("----- Pending Current Block -----");
			
			PendingTransactionResponse currentBlock = xooaClient.getCurrentBlockAsync();
			currentBlock.display();
			
			System.out.println();
			Thread.sleep(1000);
			
			System.out.println("----- Pending Current Block Details -----");
			
			xooaClient.getResultForCurrentBlock(currentBlock.getResultId()).display();
			
			System.out.println();
			
			System.out.println("----- Block Response -----");
			
			xooaClient.getBlockByNumber(5).display();
			
			System.out.println();
			
			System.out.println("----- Pending Block Response -----");
			
			PendingTransactionResponse blockResponse = xooaClient.getBlockByNumberAsync(8);
			blockResponse.display();
			
			System.out.println();
			Thread.sleep(4000);
			
			System.out.println("----- Pending Block Response Details -----");
			
			xooaClient.getResultForBlockByNumber(blockResponse.getResultId()).display();
			
			System.out.println();
			
			System.out.println("----- End -----");
			
			
			
		} catch (XooaApiException xae) {
			
			xae.display();
			
		} catch(XooaRequestTimeoutException xrte) {
			
			xrte.display();
		}
	}
}
