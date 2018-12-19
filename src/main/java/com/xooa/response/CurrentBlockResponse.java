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

public class CurrentBlockResponse {

	@SerializedName("currentBlockHash")
	private String currentBlockHash;

	@SerializedName("previousBlockHash")
	private String previousBlockHash;

	@SerializedName("blockNumber")
	private long blockNumber;


	public String getCurrentBlockHash() {

		return currentBlockHash;
	}

	public void setCurrentBlockHash(String currentBlockHash) {

		this.currentBlockHash = currentBlockHash;
	}

	public String getPreviousBlockHash() {

		return previousBlockHash;
	}

	public void setPreviousBlockHash(String previousBlockHash) {

		this.previousBlockHash = previousBlockHash;
	}

	public long getBlockNumber() {

		return blockNumber;
	}

	public void setBlockNumber(long blockNumber) {

		this.blockNumber = blockNumber;
	}

	public void display() {
		System.out.println("Block Number - " + blockNumber);
		System.out.println("Current Block Hash - " + currentBlockHash);
		System.out.println("Previous Block Hash - " + previousBlockHash);
	}
}
