package com.xooa.test.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xooa.response.InvokeResponse;

public class InvokeResponseTest {

	final private String transactionId = "vdhsakjvsv";
	final private String payload = "cshcsjcmscd";
	
	@Test
	public void testInvokeResponse() {
		
		new InvokeResponse();
	}
	
	@Test
	public void testGetTransactionId() {
		
		InvokeResponse invoke = new InvokeResponse();
		invoke.setTransactionId(transactionId);
		invoke.setPayload(payload);
		
		assertEquals(transactionId, invoke.getTransactionId());
	}
	
	@Test
	public void testSetTransactionId() {
		
		InvokeResponse invoke = new InvokeResponse();
		invoke.setTransactionId(transactionId);
		invoke.setPayload(payload);
		
		assertEquals(transactionId, invoke.getTransactionId());
	}
	
	@Test
	public void testGetPayload() {
		
		InvokeResponse invoke = new InvokeResponse();
		invoke.setTransactionId(transactionId);
		invoke.setPayload(payload);
		
		assertEquals(payload, invoke.getPayload());
	}
	
	@Test
	public void testSetPayload() {
		
		InvokeResponse invoke = new InvokeResponse();
		invoke.setTransactionId(transactionId);
		invoke.setPayload(payload);
		
		assertEquals(payload, invoke.getPayload());
	}
	
	@Test
	public void testDisplay() {
		
		InvokeResponse invoke = new InvokeResponse();
		invoke.setTransactionId(transactionId);
		invoke.setPayload(payload);
		
		invoke.display();
	}
}
