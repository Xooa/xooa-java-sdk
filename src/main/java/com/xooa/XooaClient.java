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

package com.xooa;

import java.net.URISyntaxException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.xooa.api.BlockchainApi;
import com.xooa.api.IdentityApi;
import com.xooa.api.InvokeApi;
import com.xooa.api.QueryApi;
import com.xooa.api.ResultApi;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.request.IdentityRequest;
import com.xooa.response.BlockResponse;
import com.xooa.response.CurrentBlockResponse;
import com.xooa.response.IdentityResponse;
import com.xooa.response.InvokeResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.QueryResponse;
import com.xooa.response.TransactionResponse;
import com.xooa.response.WebCalloutResponse;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class XooaClient {
	
	private static Logger logger = LogManager.getLogger(XooaClient.class.getName());
	
	private String apiToken;
    private String appUrl = "https://api.xooa.com/api/v1";
    
    private WebService webService;
    private IEventListener iEventListener;
    private Socket socket;
    
    
    // -------- GETTERS AND SETTERS FOR CLASS VARIABLES ---------
    public void setApiToken(String apiToken) {
    	this.apiToken = apiToken;
    	
    	setWebService(new WebService(apiToken));
    }
    
    public String getApiToken() {
    	return apiToken;
    }
    
    public void setAppUrl(String appUrl) {
    	this.appUrl = appUrl;
    }
    
    public String getAppUrl() {
    	return appUrl;
    }
    
    public void setWebService(WebService webService) {
    	this.webService = webService;
    }
    
    
    // -------- CLASS CONSTRUCTORS ---------
    
    // Default constructor
    public XooaClient() {
    	
    }
    
    /**
     * Constructor to get an instance of XooaClient
     * 
     * @param apiToken API Token for the app to connect to.
     */
    public XooaClient(String apiToken) {
    	
    	this.apiToken = apiToken;
    	
    	setWebService(new WebService(apiToken));
    }
    
    /**
     * Constructor to get an instance of XooaClient
     * 
     * @param appUrl Url where the app is deployed. Default to https://api.xooa.com/api/v1.
     * @param apiToken API Token for the app to connect to
     */
    public XooaClient(String apiToken, String appUrl) {
    	
    	this.appUrl = appUrl;
    	this.apiToken = apiToken;
    	
    	setWebService(new WebService(apiToken));
    }
    
    /**
     * Constructor to get an instance of XooaClient
     *     
     * @param apiToken API Token for the app to connect to.
     * @param eventListener Event Listener Object to listen to events on Xooa.
     */
    public XooaClient(String apiToken, IEventListener eventListener) {
    	
    	this.apiToken = apiToken;
        this.iEventListener = eventListener;
        
        // Creates a new instance of webservice class and sets it using the setter method
        setWebService(new WebService(apiToken));
    }
    
    
    // -------- PUBLIC CLASS METHODS ---------
    
    /**
     * To validate if the API Token and App Url are valid and point to an existing App in xooa
     * 
     * @return isValid true if API Token is valid, false otherwise.
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public boolean isValid() throws XooaApiException {
    	
    	WebCalloutResponse response = webService.validateDetails(appUrl);
    	
    	if (response.getResponseCode() != 200) {
    		
    		XooaApiException apiException = new XooaApiException();
    		apiException.setErrorCode(response.getResponseCode());
    		apiException.setErrorMessage(response.getResponseText());
    		
    		logger.error(apiException);
    		
    		throw apiException;
    		
    	} else {
    		
    		IdentityResponse identityResponse = new Gson().fromJson(response.getResponseText(), IdentityResponse.class);
    		
    		if (identityResponse.getId() != null) {
    			
    			return true;
    			
    		} else {
    			
    			return false;
    		}
    	}
    }
    
    /**
     * Subscribe to all the events that take placce on the Xooa platform
     */
    public void subscribe() {
    	
    	initSocketConnection();
    }
    
    /**
     * Unsubscribe to events from Xooa. Disconnects the connection with Xooa.
     */
    public void unsubscribe() {
    	
    	if (socket.connected()) {
    		
    		socket.disconnect();
    	}
    }
    
    
    
    // -------- INVOKE METHODS ---------
    
    /**
     * Invoke Submit blockchain transaction
     * The Invoke API End Point is used for submitting transaction for processing by the blockchain Smart Contract app.
     * The end point must call a function already defined in your Smart Contract app which will process the Invoke request.
     * The function name is part of the endpoint URL, or can be entered as the fcn parameter when testing using the Sandbox.
     * For example, if testing the sample get-set Smart Contract app, use ‘set’ (without quotes) as the value for fcn.
     * The function arguments (number of arguments and type) is determined by the Smart Contract.
     * The Smart Contract is also responsible for arguments validation and exception management.
     * The payload object of Invoke Transaction Response in case of Final Response is determined by the Smart Contract app.
     * A success response may be either 200 or 202.
     * 
     * @param functionName Function name to invoke as defined in the smart contract
     * @param args Arguments to pass to the smart contract
     * @return InvokeResponse Gives the Transaction Id and payload from Xooa
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public InvokeResponse invoke(String functionName, String[] args) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new InvokeApi().invoke(webService, appUrl, functionName, args);
    }
    
    /**
     * Invoke Submit blockchain transaction
     * The Invoke API End Point is used for submitting transaction for processing by the blockchain Smart Contract app.
     * The end point must call a function already defined in your Smart Contract app which will process the Invoke request.
     * The function name is part of the endpoint URL, or can be entered as the fcn parameter when testing using the Sandbox.
     * For example, if testing the sample get-set Smart Contract app, use ‘set’ (without quotes) as the value for fcn.
     * The function arguments (number of arguments and type) is determined by the Smart Contract.
     * The Smart Contract is also responsible for arguments validation and exception management.
     * The payload object of Invoke Transaction Response in case of Final Response is determined by the Smart Contract app.
     * A success response may be either 200 or 202.
     * 
     * @param functionName Function name to invoke as defined in the smart contract
     * @param args Arguments to pass to the smart contract
     * @param timeout The Timeout time to wait before converting the request to async
     * @return InvokeResponse Gives the Transaction Id and payload from Xooa
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public InvokeResponse invoke(String functionName, String[] args, long timeout) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new InvokeApi().invoke(webService, appUrl, functionName, args, timeout);
    }
    
    /**
     * Invoke Submit blockchain transaction
     * The Invoke API End Point is used for submitting transaction for processing by the blockchain Smart Contract app.
     * The end point must call a function already defined in your Smart Contract app which will process the Invoke request.
     * The function name is part of the endpoint URL, or can be entered as the fcn parameter when testing using the Sandbox.
     * For example, if testing the sample get-set Smart Contract app, use ‘set’ (without quotes) as the value for fcn.
     * The function arguments (number of arguments and type) is determined by the Smart Contract.
     * The Smart Contract is also responsible for arguments validation and exception management.
     * The payload object of Invoke Transaction Response in case of Final Response is determined by the Smart Contract app.
     * A success response may be either 200 or 202.
     * 
     * @param functionName Function name to invoke as defined in the smart contract
     * @param args Arguments to pass to the smart contract
     * @return PendingTransactionResponse Gives  the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse invokeAsync(String functionName, String[] args) throws XooaApiException {
    	
    	return new InvokeApi().invokeAsync(webService, appUrl, functionName, args);
    }
    
    
    // -------- QUERY METHODS ---------
    
    /**
     * Query - Query Blockchain data
     * The query API End Point is used for querying blockchain state.
     * The end point must call a function already defined in your Smart Contract app which will process the query request.
     * The function name is part of the endpoint URL, or can be entered as the fcn parameter when testing using the Sandbox.
     * The function arguments (number of arguments and type) is determined by the Smart Contract.
     * The Smart Contract is responsible for validation and exception management.
     * For example, if testing the sample get-set Smart Contract app, enter ‘get’ (without quotes) as the value for fcn.
     * The response body is also determined by the Smart Contract app, and that’s also the reason why a consistent response sample is unavailable for this end point.
     * A success response may be either 200 or 202. 
     * 
     * @param functionName Function name as defined in the smart contract
     * @param args Arguments to pass to the smart contract
     * @return QueryResponse Gives the payload received from the smart contract
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public QueryResponse query(String functionName, String[] args) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new QueryApi().query(webService, appUrl, functionName, args);
    }
    
    /**
     * Query - Query Blockchain data
     * The query API End Point is used for querying blockchain state.
     * The end point must call a function already defined in your Smart Contract app which will process the query request.
     * The function name is part of the endpoint URL, or can be entered as the fcn parameter when testing using the Sandbox.
     * The function arguments (number of arguments and type) is determined by the Smart Contract.
     * The Smart Contract is responsible for validation and exception management.
     * For example, if testing the sample get-set Smart Contract app, enter ‘get’ (without quotes) as the value for fcn.
     * The response body is also determined by the Smart Contract app, and that’s also the reason why a consistent response sample is unavailable for this end point.
     * A success response may be either 200 or 202. 
     * 
     * @param functionName Function name as defined in the smart contract
     * @param args Arguments to pass to the smart contract
     * @param timeout The Timeout time to wait before converting the request to async
     * @return QueryResponse Gives the payload received from the smart contract
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public QueryResponse query(String functionName, String[] args, long timeout) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new QueryApi().query(webService, appUrl, functionName, args, timeout);
    }
    
    /**
     * Query - Query Blockchain data
     * The query API End Point is used for querying blockchain state.
     * The end point must call a function already defined in your Smart Contract app which will process the query request.
     * The function name is part of the endpoint URL, or can be entered as the fcn parameter when testing using the Sandbox.
     * The function arguments (number of arguments and type) is determined by the Smart Contract.
     * The Smart Contract is responsible for validation and exception management.
     * For example, if testing the sample get-set Smart Contract app, enter ‘get’ (without quotes) as the value for fcn.
     * The response body is also determined by the Smart Contract app, and that’s also the reason why a consistent response sample is unavailable for this end point.
     * A success response may be either 200 or 202. 
     * 
     * @param functionName Function name as defined in the smart contract
     * @param args Arguments to pass to the smart contract
     * @return PendingTransactionResponse Gives  the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse queryAsync(String functionName, String[] args) throws XooaApiException {
    	
    	return new QueryApi().queryAsync(webService, appUrl, functionName, args);
    }
    
    
    // -------- IDENTITY METHODS ---------
    
    /**
     * Returns the current Identity the API Token points to in the app
     * 
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse getCurrentIdentity() throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().getCurrentIdentity(webService, appUrl);
    }
    
    /**
     * Get all identities from the identity registry
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public List<IdentityResponse> getIdentities() throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().getIdentities(webService, appUrl);
    }
    
    /**
     * The Enroll identity endpoint is used to enroll new identities for the smart contract app.
     * A success response includes the API Token generated for the identity.
     * This API Token can be used to call API End points on behalf of the enrolled identity.
     * 
     * This endpoint provides equivalent functionality to adding new identity manually using Xooa console, 
     * and identities added using this endpoint will appear, and can be managed, using Xooa console under the identities tab of the smart contract app.
     * 
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityRequest Idnetity Request giving the name, priviliges and attributes related to the new identity 
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse enrollIdentity(IdentityRequest identityRequest) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().enrollIdentity(webService, appUrl, identityRequest);
    }
    
    /**
     * The Enroll identity endpoint is used to enroll new identities for the smart contract app.
     * A success response includes the API Token generated for the identity.
     * This API Token can be used to call API End points on behalf of the enrolled identity.
     * 
     * This endpoint provides equivalent functionality to adding new identity manually using Xooa console, 
     * and identities added using this endpoint will appear, and can be managed, using Xooa console under the identities tab of the smart contract app.
     * 
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityRequest Idnetity Request giving the name, priviliges and attributes related to the new identity 
     * @param timeout The Timeout time to wait before converting the request to async
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse enrollIdentity(IdentityRequest identityRequest, long timeout) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().enrollIdentity(webService, appUrl, identityRequest, timeout);
    }
    
    /**
     * The Enroll identity endpoint is used to enroll new identities for the smart contract app.
     * A success response includes the API Token generated for the identity.
     * This API Token can be used to call API End points on behalf of the enrolled identity.
     * 
     * This endpoint provides equivalent functionality to adding new identity manually using Xooa console, 
     * and identities added using this endpoint will appear, and can be managed, using Xooa console under the identities tab of the smart contract app.
     * 
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityRequest Idnetity Request giving the name, priviliges and attributes related to the new identity 
     * @return PendingTransactionResponse Gives  the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error. 
     */
    public PendingTransactionResponse enrollIdentityAsync(IdentityRequest identityRequest) throws XooaApiException {
    	
    	return new IdentityApi().enrollIdentityAsync(webService, appUrl, identityRequest);
    }
    
    /**
     * Generates new identity API Token
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiExceptionIt is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse regenerateIdentityApiToken(String identityId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().regenerateIdentityApiToken(webService, appUrl, identityId);
    }
    
    /**
     * Generates new identity API Token
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @param timeout The Timeout time to wait before converting the request to async
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse regenerateIdentityApiToken(String identityId, long timeout) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().regenerateIdentityApiToken(webService, appUrl, identityId, timeout);
    }
    
    /**
     * Generates new identity API Token
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse regenerateIdentityApiTokenAsync(String identityId) throws XooaApiException {
    	
    	return new IdentityApi().regenerateIdentityApiTokenAsync(webService, appUrl, identityId);
    }
    
    /**
     * Get the specified identity from the identity registry
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse getIdentity(String identityId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().getIdentity(webService, appUrl, identityId);
    }
    
    /**
     * Deletes an identity.
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public boolean deleteIdentity(String identityId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().deleteIdentity(webService, appUrl, identityId);
    }
    
    /**
     * Deletes an identity.
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @param timeout The Timeout time to wait before converting the request to async
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public boolean deleteIdentity(String identityId, long timeout) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new IdentityApi().deleteIdentity(webService, appUrl, identityId, timeout);
    }
    
    /**
     * Deletes an identity.
     * Required permission: manage identities (canManageIdentities=true)
     * 
     * @param identityId Id of the identity to regenerate API Token for
     * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse deleteIdentityAsync(String identityId) throws XooaApiException {
    	
    	return new IdentityApi().deleteIdentityAsync(webService, appUrl, identityId);
    }
    
    
    // -------- BLOCKCHAIN METHODS ---------
    
    /**
     * Use this endpoint to Get the block number and hashes of current (highest) block in the network
     * 
     * @return CurrentBlockResponse Gives the details about the block number and the hashes
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public CurrentBlockResponse getCurrentBlock() throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new BlockchainApi().getCurrentBlock(webService, appUrl);
    }
    
    /**
     * Use this endpoint to Get the block number and hashes of current (highest) block in the network
     * 
     * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse getCurrentBlockAsync() throws XooaApiException {
    	
    	return new BlockchainApi().getCurrentBlockAsync(webService, appUrl);
    }
    
    /**
     * Use this endpoint to Get the number of transactions and hashes of a specific block in the network parameters
     * 
     * @param blockNumber block number to get the data for
     * @return BlockResponse gives the block number, number of transaction and hashes for the block 
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public BlockResponse getBlockByNumber(long blockNumber) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new BlockchainApi().getBlockByNumber(webService, appUrl, blockNumber);
    }
    
    /**
     * Use this endpoint to Get the number of transactions and hashes of a specific block in the network parameters
     * 
     * @param blockNumber block number to get the data for
     * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse getBlockByNumberAsync(long blockNumber) throws XooaApiException {
    	
    	return new BlockchainApi().getBlockByNumberAsync(webService, appUrl, blockNumber);
    }
    
    /**
     * Use this endpoint to Get transaction by transaction id
     * 
     * @param transactionId transaction Id from a previous Xooa transaction
     * @return TransactionResponse data related to the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public TransactionResponse getTransaction(String transactionId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new BlockchainApi().getTransaction(webService, appUrl, transactionId);
    }
    
    /**
     * Use this endpoint to Get transaction by transaction id
     * 
     * @param transactionId transaction id from a previous Xooa transaction
     * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public PendingTransactionResponse getTransactionAsync(String transactionId) throws XooaApiException {
    	
    	return new BlockchainApi().getTransactionAsync(webService, appUrl, transactionId);
    }
    
    
    // -------- RESULT METHODS ---------
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return InvokeResponse Gives the Transaction Id and payload from Xooa
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public boolean getResultForIdentityDelete(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForDeleteIdentity(webService, appUrl, resultId);
    }
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return InvokeResponse Gives the Transaction Id and payload from Xooa
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public InvokeResponse getResultForInvoke(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForInvoke(webService, appUrl, resultId);
    }
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return QueryResponse Gives the payload received from the smart contract
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public QueryResponse getResultForQuery(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForQuery(webService, appUrl, resultId);
    }
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return IdentityResponse Gives the details about the Identity and the access priviliges
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public IdentityResponse getResultForIdentity(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForIdentity(webService, appUrl, resultId);
    }
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return CurrentBlockResponse Gives the details about the block number and the hashes
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public CurrentBlockResponse getResultForCurrentBlock(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForCurrentBlock(webService, appUrl, resultId);
    }
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return BlockResponse gives the block number, number of transaction and hashes for the block
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error. 
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public BlockResponse getResultForBlockByNumber(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForBlockByNumber(webService, appUrl, resultId);
    }
    
    /**
     * This endpoint returns the result of previously submitted api request
     * 
     * @param resultId result id from a previous blockchain transaction
     * @return TransactionResponse data related to the transaction
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     * @throws XooaRequestTimeoutException It is thrown when a synchronous call to API returns a pending response due to timeout.
     */
    public TransactionResponse getResultForTransaction(String resultId) throws XooaApiException, XooaRequestTimeoutException {
    	
    	return new ResultApi().getResultForTransaction(webService, appUrl, resultId);
    }
    
    
    //----------- Private Method for socket ----------
    
    /**
     * Create a socket connection and subscribe to events
     */
    private void initSocketConnection() {
    	
    	IO.Options options = new IO.Options();
    	
    	options.path = "/subscribe";
    	
    	try {
    		
    		socket = IO.socket("https://api.xooa.com", options);
    		
    		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
    			
    			@Override
    			public void call(Object... args) {
    				
    				iEventListener.onConnected("Connection successful. Trying authentication with ApiToken " + apiToken);
    				
    				JSONObject jsonObject = new JSONObject();
    				
    				try {
    					
    					jsonObject.put("token", apiToken);
    					
    					socket.emit("authenticate", jsonObject);
    					
    				} catch (JSONException e) {
    					
    					e.printStackTrace();
    				}
    			}
    		}).on("authenticated", new Emitter.Listener() {
    			
    			@Override
    			public void call(Object... args) {
    				
    				iEventListener.onAuthenticated("Auth successful");
    			}
    		}).on("event", new Emitter.Listener() {
    			
    			@Override
    			public void call(Object... args) {
    				
    				iEventListener.onEventReceived((JSONObject) args[0]);
    			}
    		}).on("error", new Emitter.Listener() {
    			
    			@Override
    			public void call(Object... args) {
    				
    				//JSONObject jsonObject = (JSONObject) args[0];
    				
    				iEventListener.onError("Error : " + args[0].toString());
    			}
    		}).on("unauthorized", new Emitter.Listener() {
    			
    			@Override
    			public void call(Object... args) {
    				
    				socket.disconnect();
    				
    				JSONObject jsonObject = (JSONObject) args[0];
    				
    				iEventListener.onUnauthorized("Unauthorized error : " + jsonObject.toString());
    			}
    		});
    		
    		socket.connect();
    	} catch (URISyntaxException e) {
    		
    		e.printStackTrace();
    	}
    }
}