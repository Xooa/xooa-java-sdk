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
 * Block Response received when a request is made to get block data from API.
 *
 * @author kavi
 *
 */
public class BlockResponse {

	@SerializedName("previous_hash")
	private String previousHash;

	@SerializedName("data_hash")
	private String dataHash;

	@SerializedName("numberOfTransactions")
	private int numberOfTransactions;

	@SerializedName("blockNumber")
	private long blockNumber;

	public String getPreviousHash() {

		return previousHash;
	}

	public void setPreviousHash(String previousHash) {

		this.previousHash = previousHash;
	}

	public String getDataHash() {

		return dataHash;
	}

	public void setDataHash(String dataHash) {

		this.dataHash = dataHash;
	}

	public int getNumberOfTransactions() {

		return numberOfTransactions;
	}

	public void setNumberOfTransactions(int numberOfTransactions) {

		this.numberOfTransactions = numberOfTransactions;
	}

	public long getBlockNumber() {

		return blockNumber;
	}

	public void setBlockNumber(long blockNumber) {

		this.blockNumber = blockNumber;
	}

	public void display() {
		System.out.println("Block Number - " + blockNumber);
		System.out.println("Number of Transactions - " + numberOfTransactions);
		System.out.println("Data Hash - " + dataHash);
		System.out.println("Previous Hash - " + previousHash);
	}
}
