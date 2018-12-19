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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xooa.exception.XooaApiException;
import com.xooa.response.WebCalloutResponse;

public class WebService {

	public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_DELETE = "DELETE";

    static Logger logger = LogManager.getLogger(WebService.class.getName());

    private String apiToken;

    public void setApiToken(String apiToken) {
    	this.apiToken = apiToken;
    }


    // -------- CLASS CONSTRUCTORS ---------

    // Default constructor to get an instance of WebService
    public WebService() {

    }

    /**
     * Constructor to get an instance of WebService
     *
     * @param apiToken ApiToken to be used in the HTTPS header
     */
    public WebService(String apiToken) {

    	this.apiToken = apiToken;
    }


    // -------- PUBLIC CLASS METHODS ---------

    /**
     * To validate the details eneterd by the user by invoking the Identities Api
     * 
     * @param baseUrl the base url where the blockchain service is running
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse validateDetails(String baseUrl) throws XooaApiException {

    	String calloutUrl = baseUrl + "/identities/me";

    	return makeHttpCall(calloutUrl, REQUEST_METHOD_GET, null);
    }

    /**
     * Create a request to Xooa for an Invoke Request 
     * 
     * @param calloutUrl the url to call the invoke api
     * @param args the arguments to pass to the invoke call as request body
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse makeInvokeCall(String calloutUrl, String[] args) throws XooaApiException {

    	try {
    		if (args != null) {

        		StringBuilder requestBody = new StringBuilder();

        		requestBody.append("[");

        		for (int i = 0; i < args.length; i++) {

        			requestBody.append("\"");
                    requestBody.append(args[i]);
                    requestBody.append("\"");

                    if (i != args.length - 1) {

                    	requestBody.append(",");
                    }
                }

        		requestBody.append("]");

        		return makeHttpCall(calloutUrl, REQUEST_METHOD_POST, requestBody.toString());

        	} else {

        		return makeHttpCall(calloutUrl, REQUEST_METHOD_POST, null);
        	}
    	} catch (XooaApiException xae) {
    		throw xae;

    	} catch (Exception e) {

    		XooaApiException xae = new XooaApiException();
    		xae.setErrorCode(0);
    		xae.setErrorMessage(e.getMessage());

    		throw xae;
    	}
    }

    /**
     * Create a request to Xooa for a Query Request 
     * 
     * @param calloutUrl the url to call the query api
     * @param args the arguments to pass to the call as request body
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse makeQueryCall(String calloutUrl, String[] args) throws XooaApiException {

    	try {

        	if (args != null) {

        		StringBuilder requestBody = new StringBuilder();

        		requestBody.append("[");

        		for (int i = 0; i < args.length; i++) {

        			requestBody.append("\"");
                    requestBody.append(args[i]);
                    requestBody.append("\"");

                    if (i != args.length - 1) {
                    	requestBody.append(",");
                    }
                }

        		requestBody.append("]");

        		return makeHttpCall(calloutUrl, REQUEST_METHOD_POST, requestBody.toString());

        	} else {

        		return makeHttpCall(calloutUrl, REQUEST_METHOD_POST, null);
        	}
    	} catch (XooaApiException xae) {

    		throw xae;

    	} catch (Exception e) {

    		XooaApiException xae = new XooaApiException();
    		xae.setErrorCode(0);
    		xae.setErrorMessage(e.getMessage());

    		throw xae;
    	}
    }

    /**
     * Create a request to Xooa for a Identity Request
     * 
     * @param calloutUrl the url to call the identity api
     * @param requestMethod the http method for the request 
     * @param requestString the request body
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse makeIdentityCall(String calloutUrl, String requestMethod, String requestString) throws XooaApiException {

    	return makeHttpCall(calloutUrl, requestMethod, requestString);
    }

    /**
     * Create a request to Xooa for a Blockchain Request
     * 
     * @param calloutUrl the url to call the blockchain api
     * @param requestMethod the http method for the request
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse makeBlockchainCall(String calloutUrl, String requestMethod) throws XooaApiException {

    	return makeHttpCall(calloutUrl, requestMethod, null);
    }

    /**
     * Create a request to Xooa for a Transaction Request
     * 
     * @param calloutUrl the url to call the transaction api
     * @param requestMethod the http method for the request
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse makeTransactionCall(String calloutUrl, String requestMethod) throws XooaApiException {

    	return makeHttpCall(calloutUrl, requestMethod, null);
    }

    /**
     * Create a request to Xooa for a Result Request
     * 
     * @param calloutUrl the url to call the result api
     * @param requestMethod the http method for the request
     * @return WebCalloutResponse  status code and response received by the call
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    public WebCalloutResponse makeResultCall(String calloutUrl, String requestMethod) throws XooaApiException {

    	return makeHttpCall(calloutUrl, requestMethod, null);
    }


    // -------- PRIVATE CLASS METHODS ---------

    /**
     * Provides a way to call the server
     *
     * @param calloutUrl    The server url to be called
     * @param requestMethod The type of Https method - GET, POST, DELETE, etc
     * @param requestBody   The request body to be attached while making the call
     * @return Returns the data from the server as a String
     * @throws XooaApiException It is thrown in case of any internal error or if the API returns any error.
     */
    private WebCalloutResponse makeHttpCall(String calloutUrl, String requestMethod, String requestBody) throws XooaApiException {

    	OutputStreamWriter writeStream = null;
    	BufferedReader inputReader = null;

    	try {

    		URL url = new URL(calloutUrl);

    		HttpsURLConnection httpsConnection = (HttpsURLConnection) url.openConnection();

    		httpsConnection.setDoOutput(true);
            httpsConnection.setRequestMethod(requestMethod);
            httpsConnection.setRequestProperty("Content-Type", "application/json");
            httpsConnection.setRequestProperty("Authorization", "bearer " + apiToken);

            if (!(requestBody.equals("")) && !requestBody.equals(null)) {

            	logger.debug(requestBody);

            	writeStream = new OutputStreamWriter(httpsConnection.getOutputStream(), "UTF-8");

            	writeStream.write(requestBody);
            	writeStream.flush();
            }

            inputReader = new BufferedReader(new InputStreamReader(httpsConnection.getInputStream(), "UTF-8"));

            String line;
            StringBuilder content = new StringBuilder();

            while (!(line = inputReader.readLine()).equals(null)) {

            	content.append(line);
            	content.append(System.lineSeparator());
            }

            logger.debug(content.toString());

            WebCalloutResponse response = new WebCalloutResponse();
            response.setResponseCode(httpsConnection.getResponseCode());
            response.setResponseText(content.toString());

            return response;

        } catch (Exception e) {

        	logger.error(e);

        	XooaApiException apiException = new XooaApiException();
        	apiException.setErrorCode(0);
        	apiException.setErrorMessage(e.getMessage());

        	throw apiException;
        } finally {
        	try {
        		writeStream.close();
        		inputReader.close();
        	} catch (IOException ioe) {

        	}
        }
    }
}
