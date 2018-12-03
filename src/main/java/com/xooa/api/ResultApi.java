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

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.xooa.WebService;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.request.Attributes;
import com.xooa.response.BlockResponse;
import com.xooa.response.CurrentBlockResponse;
import com.xooa.response.IdentityResponse;
import com.xooa.response.InvokeResponse;
import com.xooa.response.QueryResponse;
import com.xooa.response.TransactionResponse;
import com.xooa.response.WebCalloutResponse;


public class ResultApi {
	
	static Logger logger = LogManager.getLogger(ResultApi.class.getName());
	
	/**
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return InvokeResponse Gives the Transaction Id and payload from Xooa
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public InvokeResponse getResultForInvoke(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					String payload = responseJson.get("payload").toString();
					
					InvokeResponse invokeResponse = new InvokeResponse();
					invokeResponse.setPayload(payload);
					return invokeResponse;
					
				} catch (Exception e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
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
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return QueryResponse Gives the payload received from the smart contract
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public QueryResponse getResultForQuery(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					String payload = responseJson.get("payload").toString();
					
					QueryResponse queryResponse =  new QueryResponse();
					queryResponse.setPayload(payload);
					
					return queryResponse;
					
				} catch (Exception e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
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
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse getResultForIdentity(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				IdentityResponse identityResponse = null;
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					JSONObject payload = (JSONObject) responseJson.get("payload");
					
					JSONArray attributesArray = payload.optJSONArray("Attrs");
					
					List<Attributes> attributesList = new ArrayList<>();
					
					for (int i = 0; i < attributesArray.length(); i++) {
						
						// Create a new object from the attribute value
						Attributes attributes = new Gson().fromJson(attributesArray.optString(i), Attributes.class);
						
						attributesList.add(attributes);
					}
					
					identityResponse = new Gson().fromJson(payload.toString(), IdentityResponse.class);
					
					identityResponse.setAttributes(attributesList);
					
				} catch (Exception e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
				
				return identityResponse;
				
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
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return CurrentBlockResponse Gives the details about the block number and the hashes
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public CurrentBlockResponse getResultForCurrentBlock(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				CurrentBlockResponse currentBlockResponse = null;
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					String payload = responseJson.get("payload").toString();
					
					currentBlockResponse = new Gson().fromJson(payload, CurrentBlockResponse.class);
					
				} catch (Exception e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
				
				return currentBlockResponse;
				
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
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return BlockResponse gives the block number, number of transaction and hashes for the block
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public BlockResponse getResultForBlockByNumber(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					String payload = responseJson.get("payload").toString();
					
					return new Gson().fromJson(payload, BlockResponse.class);
					
				} catch (Exception e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
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
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return TransactionResponse data related to the transaction
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public TransactionResponse getResultForTransaction(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					String payload = responseJson.get("payload").toString();
					
					return new Gson().fromJson(payload, TransactionResponse.class);
					
				} catch (Exception e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
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
	 * Call the Result API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param resultId result id from a previous blockchain transaction
	 * @return boolean
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public boolean getResultForDeleteIdentity(WebService webService, String calloutBaseUrl, String resultId) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/results/" + resultId;
			
			WebCalloutResponse response = webService.makeResultCall(url, WebService.REQUEST_METHOD_GET);
			
			if (response.getResponseCode() == 200) {
				
				try {
					
					JSONObject responseJson = new JSONObject(response.getResponseText());
					
					JSONObject payload = (JSONObject) responseJson.get("payload");
					
					return payload.getBoolean("deleted");
					
				} catch (JSONException e) {
					
					logger.error(e);
					
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage("Exception in Parsing response JSON - " + e.getMessage());
					
					throw apiException;
				}
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
}