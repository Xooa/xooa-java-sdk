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

/**
 * Xooa Request Timeout Exception received when a synchronous call to the API times out and results in a pending response.
 *
 * @author kavi
 *
 */
public class XooaRequestTimeoutException extends Exception {

	private static final long serialVersionUID = -2762335665779877682L;

	private String resultURL;
	private String resultId;

	public String getResultUrl() {

		return resultURL;
	}

	public void setResultUrl(String resultUrl) {

		this.resultURL = resultUrl;
	}

	public String getResultId() {

		return resultId;
	}

	public void setResultId(String resultId) {

		this.resultId = resultId;
	}

	public void display() {
		System.out.println("Result Id - " + resultId);
		System.out.println("Result Url - " + resultURL);
	}
}
