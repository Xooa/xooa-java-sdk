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

package com.xooa.api;

import com.google.gson.Gson;

import com.xooa.WebService;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.response.InvokeResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.WebCalloutResponse;


public class InvokeApi {
	
	/**
	 * Call the Invoke API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param functionName function to be invoked in the smart contract
	 * @param args list of arguments to be passed to the invoked method
	 * @return InvokeResponse gives the payload assocciated with the invoke call
	 * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
	 * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
	 */
	public InvokeResponse invoke(WebService webService, String calloutBaseUrl, String functionName, String[] args) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/invoke/" + functionName;
		
		return callInvokeApi(webService, url, args);
	}
	
	/**
	 * Call the Invoke API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param functionName function to be invoked in the smart contract
	 * @param args list of arguments to be passed to the invoked method
	 * @param timeout The Timeout time to wait before converting the request to async
	 * @return InvokeResponse gives the payload assocciated with the invoke call 
	 * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
	 * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
	 */
	public InvokeResponse invoke(WebService webService, String calloutBaseUrl, String functionName, String[] args, long timeout) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/invoke/" + functionName + "?timeout=" + timeout;
		
		return callInvokeApi(webService, url, args);
	}
	
	/**
	 * Call the Invoke API in async
	 *  
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running 
	 * @param functionName function to be invoked in the smart contract
	 * @param args list of arguments to be passed to the invoked method
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
	 */
	public PendingTransactionResponse invokeAsync(WebService webService, String calloutBaseUrl, String functionName, String[] args) throws XooaApiException {
		
		String url = calloutBaseUrl + "/invoke/" + functionName + "?async=true";
		
		return callInvokeApiAsync(webService, url, args);
	}
	
	
	/**
	 * Process the response from Invoke API
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param args list of arguments to be passed to the invoked method
	 * @return InvokeResponse gives the payload assocciated with the invoke call
	 * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
	 * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
	 */
	private InvokeResponse callInvokeApi(WebService webService, String url, String[] args) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			WebCalloutResponse response = webService.makeInvokeCall(url, args);
			
			if (response.getResponseCode() == 200) {
				
				return new Gson().fromJson(response.getResponseText(), InvokeResponse.class);
				
			} else if (response.getResponseCode() == 202) {
				
				throw new Gson().fromJson(response.getResponseText(), XooaRequestTimeoutException.class);
				
			} else {
				
				XooaApiException apiException = new XooaApiException();
				apiException.setErrorCode(response.getResponseCode());
				apiException.setErrorMessage(response.getResponseText());
				
				throw apiException;
			}
		} catch (XooaApiException xae) {
			
			throw xae;
			
		} catch (XooaRequestTimeoutException xrte) {
			
			throw xrte;
			
		} catch (Exception e) {
			
			XooaApiException apiException = new XooaApiException();
			apiException.setErrorCode(0);
			apiException.setErrorMessage(e.getMessage());
			
			throw apiException;
		}
	}
	
	/**
	 * Process the response for async calls
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param args list of arguments to be passed to the invoked method
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
	 */
	private PendingTransactionResponse callInvokeApiAsync(WebService webService, String url, String[] args) throws XooaApiException {
		
		try {
			
			WebCalloutResponse response = webService.makeInvokeCall(url, args);
			
			if (response.getResponseCode() == 202) {
				
				return new Gson().fromJson(response.getResponseText(), PendingTransactionResponse.class);
				
			} else {
				
				XooaApiException apiException = new XooaApiException();
				apiException.setErrorCode(response.getResponseCode());
				apiException.setErrorMessage(response.getResponseText());
				
				throw apiException;
			}
		} catch (XooaApiException xae) {
			xae.printStackTrace();
			throw xae;
			
		} catch (Exception e) {
			
			XooaApiException apiException = new XooaApiException();
            apiException.setErrorCode(0);
            apiException.setErrorMessage(e.getMessage());
            
            throw apiException;
		}
	}
}