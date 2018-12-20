package com.xooa.test.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xooa.exception.XooaRequestTimeoutException;

public class XooaRequestTimeoutExceptionTest {

	@Test
	public void testXooaRequestTimeoutException() {
		
		new XooaRequestTimeoutException();
			
	}
	
	@Test
	public void testGetResultUrl() {
		
		XooaRequestTimeoutException apiException = new XooaRequestTimeoutException();
		apiException.setResultId("1234567890");
		apiException.setResultUrl("www.xooa.com/result");
		
		assertEquals("www.xooa.com/result", apiException.getResultUrl());
	}
	
	@Test
	public void testSetResultUrl() {
		
		XooaRequestTimeoutException apiException = new XooaRequestTimeoutException();
		apiException.setResultId("1234567890");
		apiException.setResultUrl("www.xooa.com/result");
		
		assertEquals("www.xooa.com/result", apiException.getResultUrl());
	}
	
	@Test
	public void testGetResultId() {
		
		XooaRequestTimeoutException apiException = new XooaRequestTimeoutException();
		apiException.setResultId("1234567890");
		apiException.setResultUrl("www.xooa.com/result");
		
		assertEquals("1234567890", apiException.getResultId());
	}
	
	@Test
	public void testSetResultId() {
		
		XooaRequestTimeoutException apiException = new XooaRequestTimeoutException();
		apiException.setResultId("1234567890");
		apiException.setResultUrl("www.xooa.com/result");
		
		assertEquals("1234567890", apiException.getResultId());
	}
	
	@Test
	public void testDisplay() {
		
		XooaRequestTimeoutException apiException = new XooaRequestTimeoutException();
		apiException.setResultId("1234567890");
		apiException.setResultUrl("www.xooa.com/result");
		
		apiException.display();
	}
}
