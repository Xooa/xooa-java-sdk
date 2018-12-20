package com.xooa.test.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xooa.response.BlockResponse;

public class BlockResponseTest {
	
	final private long blockNumber = 10;
	final private int numberOfTransactions = 4;
	final private String dataHash = "fsdgsdvsf";
	final private String previousHash = "csfsgsa";
	
	@Test
	public void testBlockResponse() {
		
		new BlockResponse();
	}
	
	@Test
	public void testGetBlockNumber() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(blockNumber, blockResponse.getBlockNumber());
	}
	
	@Test
	public void testSetBlockNumber() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(blockNumber, blockResponse.getBlockNumber());
	}
	
	@Test
	public void testGetNumberOfTransactions() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(numberOfTransactions, blockResponse.getNumberOfTransactions());
	}
	
	@Test
	public void testSetNumberOfTransactions() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(numberOfTransactions, blockResponse.getNumberOfTransactions());
	}

	@Test
	public void testGetDataHash() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(dataHash, blockResponse.getDataHash());
	}
	
	@Test
	public void testSetDataHash() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(dataHash, blockResponse.getDataHash());
	}
	
	@Test
	public void testSetPreviousHash() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(previousHash, blockResponse.getPreviousHash());
	}
	
	@Test
	public void testGetPreviousHash() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		assertEquals(previousHash, blockResponse.getPreviousHash());
	}
	
	@Test
	public void testDisplay() {
		
		BlockResponse blockResponse = new BlockResponse();
		blockResponse.setBlockNumber(blockNumber);
		blockResponse.setNumberOfTransactions(numberOfTransactions);
		blockResponse.setDataHash(dataHash);
		blockResponse.setPreviousHash(previousHash);
		
		blockResponse.display();
	}
}
