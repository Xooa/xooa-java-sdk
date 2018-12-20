package com.xooa.test.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xooa.response.CurrentBlockResponse;

public class CurrentBlockResponseTest {

	final private int blockNumber = 10;
	final private String currentBlockHash = "csbcahsaducbads";
	final private String previousBlockHash = "csbchjsbcjssdcdc";
	
	@Test
	public void testCurrentBlockResponse() {
		
		new CurrentBlockResponse();
	}
	
	@Test
	public void testGetBlockNumber() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		assertEquals(blockNumber, currentBlock.getBlockNumber());
	}
	
	@Test
	public void testSetBlockNumber() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		assertEquals(blockNumber, currentBlock.getBlockNumber());
	}
	
	@Test
	public void testGetCurrentBlockHash() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		assertEquals(currentBlockHash, currentBlock.getCurrentBlockHash());
	}
	
	@Test
	public void testSetCurrentBlockHash() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		assertEquals(currentBlockHash, currentBlock.getCurrentBlockHash());
	}
	
	@Test
	public void testGetPreviousBlockHash() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		assertEquals(previousBlockHash, currentBlock.getPreviousBlockHash());
	}
	
	@Test
	public void testSetPreviousBlockHash() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		assertEquals(previousBlockHash, currentBlock.getPreviousBlockHash());
	}
	
	@Test
	public void testDisplay() {
		
		CurrentBlockResponse currentBlock = new CurrentBlockResponse();
		currentBlock.setBlockNumber(blockNumber);
		currentBlock.setCurrentBlockHash(currentBlockHash);
		currentBlock.setPreviousBlockHash(previousBlockHash);
		
		currentBlock.display();
	}
}
