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

import com.google.gson.annotations.SerializedName;

public class PendingTransactionResponse {

	@SerializedName("resultURL")
	private String resultUrl;

	@SerializedName("resultId")
	private String resultId;


	public String getResultUrl() {

		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {

		this.resultUrl = resultUrl;
	}

	public String getResultId() {

		return resultId;
	}

	public void setResultId(String resultId) {

		this.resultId = resultId;
	}

	public void display() {
		System.out.println("Result Id - " + resultId);
		System.out.println("Result Url - " + resultUrl);
	}
}
