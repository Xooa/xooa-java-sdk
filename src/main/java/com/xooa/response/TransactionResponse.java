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
 * @author Kavi Sarna
 */

package com.xooa.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TransactionResponse {

	@SerializedName("txid")
	private String transactionId;

	@SerializedName("createdt")
	private String createdAt;

	@SerializedName("smartcontract")
	private String smartcontract;

	@SerializedName("creator_msp_id")
	private String creatorMspId;

	@SerializedName("endorser_msp_id")
	private List<String> endorserMspId;

	@SerializedName("type")
	private String type;

	@SerializedName("read_set")
	private List<ReadSet> readSet;

	@SerializedName("write_set")
	private List<WriteSet> writeSet;


	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getSmartcontract() {
		return smartcontract;
	}

	public void setSmartcontract(String smartcontract) {
		this.smartcontract = smartcontract;
	}

	public String getCreatorMspId() {
		return creatorMspId;
	}

	public void setCreatorMspId(String creatorMspId) {
		this.creatorMspId = creatorMspId;
	}

	public List<String> getEndorserMspId() {
		return endorserMspId;
	}

	public void setEndorserMspId(List<String> endorserMspId) {
		this.endorserMspId = endorserMspId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ReadSet> getReadSet() {
		return readSet;
	}

	public void setReadSet(List<ReadSet> readSet) {
		this.readSet = readSet;
	}

	public List<WriteSet> getWriteSet() {
		return writeSet;
	}

	public void setWriteSet(List<WriteSet> writeSet) {
		this.writeSet = writeSet;
	}

	public void display() {
		System.out.println("Transaction Id - " + transactionId);
		System.out.println("Smart Contract - " + smartcontract);
		System.out.println("Creator MSP Id - " + creatorMspId);
		System.out.println("Endorser MSP Id - ");

		for (String emi : endorserMspId) {
			System.out.println("\t " + emi);
		}

		System.out.println("Type - " + type);
		System.out.println("Created At - " + createdAt);
		System.out.println("ReadSet - ");

		for (ReadSet rs : readSet) {
			rs.display();
		}

		System.out.println("WriteSet - ");

		for (WriteSet ws : writeSet) {
			ws.display();
		}
	}


	static class ReadSet {

		@SerializedName("chaincode")
		private String chaincode;

		@SerializedName("set")
		private List<ReadSubSet> set;

		public String getChaincode() {
			return chaincode;
		}

		public void setChaincode(String chaincode) {
			this.chaincode = chaincode;
		}

		public List<ReadSubSet> getSet() {
			return set;
		}

		public void setSet(List<ReadSubSet> set) {
			this.set = set;
		}

		public void display() {
			System.out.println("\t Chaincode - " + chaincode);
			System.out.println("\t Set - ");

			for (ReadSubSet rss : set) {
				rss.display();
			}
		}
	}


	static class ReadSubSet {

		@SerializedName("key")
		private String key;

		@SerializedName("version")
		private Version version;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Version getVersion() {
			return version;
		}

		public void setVersion(Version version) {
			this.version = version;
		}

		public void display() {
			System.out.println("\t\t Key - " + key);
			System.out.println("\t\t Version - ");
			version.display();
		}
	}


	static class Version {

		@SerializedName("block_num")
		private String blockNumber;

		@SerializedName("tx_num")
		private String transactionNumber;

		public String getBlockNumber() {
			return blockNumber;
		}

		public void setBlockNumber(String blockNumber) {
			this.blockNumber = blockNumber;
		}

		public String getTransactionNumber() {
			return transactionNumber;
		}

		public void setTransactionNumber(String transactionNumber) {
			this.transactionNumber = transactionNumber;
		}

		public void display() {
			System.out.println("\t\t\t Block Number - " + blockNumber);
			System.out.println("\t\t\t Transaction Number - " + transactionNumber);
		}
	}


	static class WriteSet {

		@SerializedName("chaincode")
		private String chaincode;

		@SerializedName("set")
		private List<WriteSubSet> set;


		public String getChaincode() {
			return chaincode;
		}

		public void setChaincode(String chaincode) {
			this.chaincode = chaincode;
		}

		public List<WriteSubSet> getSet() {
			return set;
		}

		public void setSet(List<WriteSubSet> set) {
			this.set = set;
		}

		public void display() {
			System.out.println("\t Chaincode - " + chaincode);
			System.out.println("\t Sets - ");

			for (WriteSubSet wss : set) {
				wss.display();
			}
		}
	}


	static class WriteSubSet {

		@SerializedName("key")
		private String key;

		@SerializedName("value")
		private String value;

		@SerializedName("is_delete")
		private boolean isDelete;


		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isDelete() {
			return isDelete;
		}

		public void setDelete(boolean isDelete) {
			this.isDelete = isDelete;
		}

		public void display() {
			System.out.println("\t\t Key - " + key);
			System.out.println("\t\t Value - " + value);
			System.out.println("\t\t Is_Delete - " + isDelete);
		}
	}
}
