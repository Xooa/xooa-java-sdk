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

package com.xooa.response;

/**
 * Web Callout Response received from the Xooa Api. This contains the status code and the response body received in response.
 *
 * @author kavi
 *
 */
public class WebCalloutResponse {

	private int responseCode;
	private String responseText;


	public int getResponseCode() {

		return responseCode;
	}

	public void setResponseCode(int responseCode) {

		this.responseCode = responseCode;
	}

	public String getResponseText() {

		return responseText;
	}

	public void setResponseText(String responseText) {

		this.responseText = responseText;
	}

	public void display() {
		System.out.println("Response Code - " + responseCode);
		System.out.println("Response Text - " + responseText);
	}
}
