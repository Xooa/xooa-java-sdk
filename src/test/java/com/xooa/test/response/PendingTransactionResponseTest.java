package com.xooa.test.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xooa.response.PendingTransactionResponse;

public class PendingTransactionResponseTest {
	
	private final String resultId = "csvdsvssdvsd";
	private final String resultUrl = "vsvsdbsdgsdfzcsd";
	
	@Test
	public void testPendingTransactionResponse() {
		
		new PendingTransactionResponse();
	}
	
	@Test
	public void testGetResultId() {
		
		PendingTransactionResponse response = new PendingTransactionResponse();
		response.setResultId(resultId);
		response.setResultUrl(resultUrl);
		
		assertEquals(resultId, response.getResultId());
	}
	
	@Test
	public void testSetResultId() {
		
		PendingTransactionResponse response = new PendingTransactionResponse();
		response.setResultId(resultId);
		response.setResultUrl(resultUrl);
		
		assertEquals(resultId, response.getResultId());
	}
	
	@Test
	public void testGetResultUrl() {
		
		PendingTransactionResponse response = new PendingTransactionResponse();
		response.setResultId(resultId);
		response.setResultUrl(resultUrl);
		
		assertEquals(resultUrl, response.getResultUrl());
	}
	
	@Test
	public void testSetResultUrl() {
		
		PendingTransactionResponse response = new PendingTransactionResponse();
		response.setResultId(resultId);
		response.setResultUrl(resultUrl);
		
		assertEquals(resultUrl, response.getResultUrl());
	}
	
	@Test
	public void testDisplay() {
		
		PendingTransactionResponse response = new PendingTransactionResponse();
		response.setResultId(resultId);
		response.setResultUrl(resultUrl);
		
		response.display();
	}
}
