package com.xooa.test.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xooa.exception.XooaApiException;

public class XooaApiExceptionTest {
	
	@Test
	public void testXooaApiException() {
		
		new XooaApiException();
	}
	
	@Test
	public void testDisplay() {
		
		XooaApiException apiException = new XooaApiException();
		apiException.setErrorCode(300);
		apiException.setErrorMessage("Test Message");

		apiException.display();
	}
	
	@Test
	public void testGetErrorCode() {
		
		XooaApiException apiException = new XooaApiException();
		apiException.setErrorCode(300);
		apiException.setErrorMessage("Test Message");
		
		assertEquals(300, apiException.getErrorCode());
	}
	
	@Test
	public void testSetErrorCode() {
		
		XooaApiException apiException = new XooaApiException();
		apiException.setErrorCode(300);
		apiException.setErrorMessage("Test Message");
		
		assertEquals(300, apiException.getErrorCode());
	}
	
	@Test
	public void testGetErrorMessage() {
		
		XooaApiException apiException = new XooaApiException();
		apiException.setErrorCode(300);
		apiException.setErrorMessage("Test Message");
		
		assertEquals("Test Message", apiException.getErrorMessage());
	}
	
	@Test
	public void testSetErrorMessage() {
		
		XooaApiException apiException = new XooaApiException();
		apiException.setErrorCode(300);
		apiException.setErrorMessage("Test Message");
		
		assertEquals("Test Message", apiException.getErrorMessage());
	}
}
