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

/**
 * Invoke Response received when an Invoke call is made to the API.
 *
 * @author kavi
 *
 */
public class InvokeResponse {

	@SerializedName("txId")
	private String transactionId;

	@SerializedName("payload")
	private String payload;

	public String getTransactionId() {

		return transactionId;
	}

	public void setTransactionId(String transactionId) {

		this.transactionId = transactionId;
	}

	public String getPayload() {

		return payload;
	}

	public void setPayload(String payload) {

		this.payload = payload;
	}

	public void display() {
		System.out.println("Transaction Id - " + transactionId);
		System.out.println("Payload - " + payload);
	}
}
