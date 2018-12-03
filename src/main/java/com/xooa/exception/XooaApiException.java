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

package com.xooa.exception;


public class XooaApiException extends Exception {
	
	private static final long serialVersionUID = -1989271294313077374L;
	
	private int errorCode;
    private String errorMessage;
    
    
    public int getErrorCode() {
    	
    	return errorCode;
    }
    
    public void setErrorCode(int errorCode) {
    	
    	this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
    	
    	return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
    	
    	this.errorMessage = errorMessage;
    }
    
    public void display() {
    	System.out.println("Exception Code - " + errorCode);
    	System.out.println("Exception Message - " + errorMessage);
    }
}