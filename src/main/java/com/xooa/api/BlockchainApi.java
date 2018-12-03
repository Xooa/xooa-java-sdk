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
import com.xooa.response.*;


public class BlockchainApi {
	
	/**
	 * Call the CurrentBlock api
	 *  
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @return CurrentBlockResponse Gives the details about the block number and the hashes
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public CurrentBlockResponse getCurrentBlock(WebService webService, String calloutBaseUrl) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/block/current";
		
		return callBlockchainApi(webService, url, WebService.REQUEST_METHOD_GET);
	}
	
	/**
	 * Call the CurrentBlock api in async
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	public PendingTransactionResponse getCurrentBlockAsync(WebService webService, String calloutBaseUrl) throws XooaApiException {
		
		String url = calloutBaseUrl + "/block/current/?async=true";
		
		return callBlockchainApiAsync(webService, url, WebService.REQUEST_METHOD_GET);
	}
	
	/**
	 * Call the BlockByNumber api
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param blockNumber block number to get the data for
	 * @return BlockResponse gives the block number, number of transaction and hashes for the block 
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public BlockResponse getBlockByNumber(WebService webService, String calloutBaseUrl, long blockNumber) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/block/" + blockNumber;
		
		return callBlockApi(webService, url, WebService.REQUEST_METHOD_GET);
	}
	
	/**
	 * Call the BlockByNumber api in async
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param blockNumber block number to get the data for
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	public PendingTransactionResponse getBlockByNumberAsync(WebService webService, String calloutBaseUrl, long blockNumber) throws XooaApiException {
		
		String url = calloutBaseUrl + "/block/" + blockNumber + "/?async=true";
		
		return callBlockchainApiAsync(webService, url, WebService.REQUEST_METHOD_GET);
	}
	
	/**
	 * Call the Transaction api
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param transactionId transaction Id from a previous Xooa transaction
	 * @return TransactionResponse data related to the transaction
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public TransactionResponse getTransaction(WebService webService, String calloutBaseUrl, String transactionId) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/transactions/" + transactionId;
		
		return callTransactionApi(webService, url, WebService.REQUEST_METHOD_GET);
	}
	
	/**
	 * Call the Transaction api in async
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param transactionId transaction Id from a previous Xooa transaction
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	public PendingTransactionResponse getTransactionAsync(WebService webService, String calloutBaseUrl, String transactionId) throws XooaApiException {
		
		String url = calloutBaseUrl + "/transactions/" + transactionId + "/?async=true";
		
		return callBlockchainApiAsync(webService, url, WebService.REQUEST_METHOD_GET);
	}
	
	
	/**
	 * Process the response from Transaction api
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param requestMethod  http method for the call
	 * @return TransactionResponse data related to the transaction
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	private TransactionResponse callTransactionApi(WebService webService, String url, String requestMethod) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			WebCalloutResponse response = webService.makeTransactionCall(url, requestMethod);
			
			if (response.getResponseCode() == 200) {
				
				return new Gson().fromJson(response.getResponseText(), TransactionResponse.class);
				
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
	 * Process the response from CurrentBlock api
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param requestMethod  http method for the call
	 * @return CurrentBlockResponse Gives the details about the block number and the hashes
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	private CurrentBlockResponse callBlockchainApi(WebService webService, String url, String requestMethod) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			WebCalloutResponse response = webService.makeBlockchainCall(url, requestMethod);
			
			if (response.getResponseCode() == 200) {
				
				return new Gson().fromJson(response.getResponseText(), CurrentBlockResponse.class);
				
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
	 * Process the response from Block api
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param requestMethod  http method for the call
	 * @return BlockResponse gives the block number, number of transaction and hashes for the block 
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	private BlockResponse callBlockApi(WebService webService, String url, String requestMethod) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			WebCalloutResponse response = webService.makeBlockchainCall(url, requestMethod);
			
			if (response.getResponseCode() == 200) {
				
				return new Gson().fromJson(response.getResponseText(), BlockResponse.class);
				
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
	 * @param requestMethod http method for the call
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	private PendingTransactionResponse callBlockchainApiAsync(WebService webService, String url, String requestMethod) throws XooaApiException {
		
		try {
			
			WebCalloutResponse response = webService.makeBlockchainCall(url, requestMethod);
			
			if (response.getResponseCode() == 200) {
				
				return new Gson().fromJson(response.getResponseText(), PendingTransactionResponse.class);
				
			} else {
				
				XooaApiException apiException = new XooaApiException();
				apiException.setErrorCode(response.getResponseCode());
				apiException.setErrorMessage(response.getResponseText());
				
				throw apiException;
			}
		} catch (XooaApiException xae) {
			
			throw xae;
			
		} catch (Exception e) {
			
			XooaApiException apiException = new XooaApiException();
			apiException.setErrorCode(0);
			apiException.setErrorMessage(e.getMessage());
			
			throw apiException;
		}
	}
}