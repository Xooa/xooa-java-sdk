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
import org.json.JSONObject;

import com.google.gson.Gson;
import com.xooa.WebService;
import com.xooa.exception.XooaApiException;
import com.xooa.exception.XooaRequestTimeoutException;
import com.xooa.request.Attributes;
import com.xooa.request.IdentityRequest;
import com.xooa.response.IdentityResponse;
import com.xooa.response.PendingTransactionResponse;
import com.xooa.response.WebCalloutResponse;


public class IdentityApi {
	
	
	static Logger logger = LogManager.getLogger(IdentityApi.class.getName());
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse getCurrentIdentity(WebService webService, String calloutBaseUrl) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/me";
		
		return callIdentityApi(webService, url, WebService.REQUEST_METHOD_GET, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityRequest Idnetity Request giving the name, priviliges and attributes related to the new identity 
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse enrollIdentity(WebService webService, String calloutBaseUrl, IdentityRequest identityRequest) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities";
        String requestString = new Gson().toJson(identityRequest);
        
        return callIdentityApi(webService, url, WebService.REQUEST_METHOD_POST, requestString);
    }
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityRequest Idnetity Request giving the name, priviliges and attributes related to the new identity 
	 * @param timeout The Timeout time to wait before converting the request to async
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse enrollIdentity(WebService webService, String calloutBaseUrl, IdentityRequest identityRequest, long timeout) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/?timeout=" + timeout;
        String requestString = new Gson().toJson(identityRequest);
        
        return callIdentityApi(webService, url, WebService.REQUEST_METHOD_POST, requestString);
    }
	
	/**
	 * Call the Identity API in async
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityRequest Idnetity Request giving the name, priviliges and attributes related to the new identity 
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	public PendingTransactionResponse enrollIdentityAsync(WebService webService, String calloutBaseUrl, IdentityRequest identityRequest) throws XooaApiException {
		
		String url = calloutBaseUrl + "/identities/?async=true";
        String requestString = new Gson().toJson(identityRequest);
        
        return callIdentityApiAsync(webService, url, WebService.REQUEST_METHOD_POST, requestString);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse regenerateIdentityApiToken(WebService webService, String calloutBaseUrl, String identityId) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/" + identityId + "/regeneratetoken";
		 
		return callIdentityApi(webService, url, WebService.REQUEST_METHOD_POST, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @param timeout The Timeout time to wait before converting the request to async
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse regenerateIdentityApiToken(WebService webService, String calloutBaseUrl, String identityId, long timeout) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/" + identityId + "/regeneratetoken/?timeout=" + timeout;
		
		return callIdentityApi(webService, url, WebService.REQUEST_METHOD_POST, null);
	}
	
	/**
	 * Call the Identity API in async
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	public PendingTransactionResponse regenerateIdentityApiTokenAsync(WebService webService, String calloutBaseUrl, String identityId) throws XooaApiException {
		
		String url = calloutBaseUrl + "/identities/" + identityId + "/regeneratetoken/?async=true";
		
		return callIdentityApiAsync(webService, url, WebService.REQUEST_METHOD_POST, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse getIdentity(WebService webService, String calloutBaseUrl, String identityId) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/" + identityId;
		
		return callIdentityApi(webService, url, WebService.REQUEST_METHOD_GET, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @param timeout The Timeout time to wait before converting the request to async
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public IdentityResponse getIdentity(WebService webService, String calloutBaseUrl, String identityId, long timeout) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/" + identityId + "/?timeout=" + timeout;
		
		return callIdentityApi(webService, url, WebService.REQUEST_METHOD_GET, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public boolean deleteIdentity(WebService webService, String calloutBaseUrl, String identityId) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/" + identityId;
		
		return deleteIdentity(webService, url, WebService.REQUEST_METHOD_DELETE, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @param timeout The Timeout time to wait before converting the request to async
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public boolean deleteIdentity(WebService webService, String calloutBaseUrl, String identityId, long timeout) throws XooaApiException, XooaRequestTimeoutException {
		
		String url = calloutBaseUrl + "/identities/" + identityId + "/?timeout=" + timeout;
		
		return deleteIdentity(webService, url, WebService.REQUEST_METHOD_DELETE, null);
	}
	
	/**
	 * Call the Identity API in async 
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @param identityId Id of the identity to regenerate API Token for
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	public PendingTransactionResponse deleteIdentityAsync(WebService webService, String calloutBaseUrl, String identityId) throws XooaApiException {
		
		String url = calloutBaseUrl + "/identities/" + identityId + "/?async=true";
		
		return callIdentityApiAsync(webService, url, WebService.REQUEST_METHOD_DELETE, null);
	}
	
	/**
	 * Call the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param calloutBaseUrl the base url where the app is running
	 * @return List<IdentityResponse> Gives the details about all the Identities and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	public List<IdentityResponse> getIdentities(WebService webService, String calloutBaseUrl) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			String url = calloutBaseUrl + "/identities/";
			
			WebCalloutResponse response = webService.makeIdentityCall(url, WebService.REQUEST_METHOD_GET, null);
			
			if (response.getResponseCode() == 200) {
				
				List<IdentityResponse> identityResponseList = new ArrayList<>();
				
				try {
					
					JSONArray identitiesArray = new JSONArray(response.getResponseText());
					
					for (int i = 0; i < identitiesArray.length(); i++) {
						
						// For each identity, we parse the respective object to get the inner JsonArray for attributes
						JSONObject identity = identitiesArray.optJSONObject(i);
						
						JSONArray attribuesArray = identity.optJSONArray("Attrs");
						
						List<Attributes> attributesList = new ArrayList<>();
						
						for (int j = 0; j < attribuesArray.length(); j++) {
							
							// Create a new object from the attribute value
							Attributes attributes = new Gson().fromJson(attribuesArray.optJSONObject(j).toString(), Attributes.class);
							
							attributesList.add(attributes);
						}
						
						IdentityResponse identityResponse = new Gson().fromJson(String.valueOf(identitiesArray.optJSONObject(i)), IdentityResponse.class);
						
						identityResponse.setAttributes(attributesList);
						
						identityResponseList.add(identityResponse);
					}
				} catch (Exception e) {
					
					logger.error(e);
					
					// NOTE: This error is not thrown to the user of the lib since this is supposed to be handled here and not by the user
					XooaApiException apiException = new XooaApiException();
					apiException.setErrorCode(0);
					apiException.setErrorMessage(e.getMessage());
					
					throw apiException;
				}
				
				return identityResponseList;
				
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
	 * Process the response from the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param requestMethod http method for the call
	 * @param requestString the request body that needs to be sent
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	private boolean deleteIdentity(WebService webService, String url, String requestMethod, String requestString) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			WebCalloutResponse response = webService.makeIdentityCall(url, requestMethod, requestString);
			
			if (response.getResponseCode() == 200) {
				
				JSONObject responseJson = new JSONObject(response.getResponseText());
				
				return responseJson.getBoolean("deleted");
				
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
	 * Process the response from the Identity API
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param requestMethod http method for the call
	 * @param requestString the request body that needs to be sent
	 * @return IdentityResponse Gives the details about the Identity and the access priviliges
	 * @throws XooaApiException
	 * @throws XooaRequestTimeoutException
	 */
	private IdentityResponse callIdentityApi(WebService webService, String url, String requestMethod, String requestString) throws XooaApiException, XooaRequestTimeoutException {
		
		try {
			
			WebCalloutResponse response = webService.makeIdentityCall(url, requestMethod, requestString);
			
			if (response.getResponseCode() == 200) {
				
				try {
					
					//We parse the response to get the JsonArray for attributes
					JSONObject jsonObject = new JSONObject(response.getResponseText());
					
					JSONArray jsonArray = jsonObject.optJSONArray("Attrs");
					
					List<Attributes> attributesList = new ArrayList<>();
					
					for (int i = 0; i < jsonArray.length(); i++) {
						
						// Create a new object from the attribute value
						Attributes attributes = new Gson().fromJson(jsonArray.optString(i), Attributes.class);
						
						attributesList.add(attributes);
					}
					
					IdentityResponse identityResponse = new Gson().fromJson(response.getResponseText(), IdentityResponse.class);
					
					identityResponse.setAttributes(attributesList);
					
					return identityResponse;
					
				} catch (Exception e) {
					
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
	 * Process the response for async calls
	 * 
	 * @param webService WebService object used to call the API
	 * @param url the base url where the app is running
	 * @param requestMethod http method for the call
	 * @param requestString the request body that needs to be sent
	 * @return PendingTransactionResponse Gives the ResultId and ResultUrl for the transaction
	 * @throws XooaApiException
	 */
	private PendingTransactionResponse callIdentityApiAsync(WebService webService, String url, String requestMethod, String requestString) throws XooaApiException {
		
		try {
			
			WebCalloutResponse response = webService.makeIdentityCall(url, requestMethod, requestString);
			
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