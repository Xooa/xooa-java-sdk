package com.xooa.test.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.xooa.request.Attributes;

public class AttributesTest {
	
	@Test
	public void testAttributes() {
		
		new Attributes();
	}
	
	@Test
	public void testGetName() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		assertEquals(name, attribute.getName());
	}
	
	@Test
	public void testSetName() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		assertEquals(name, attribute.getName());
	}
	
	@Test
	public void testGetValue() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		assertEquals(value, attribute.getValue());
	}
	
	@Test
	public void testSetValue() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		assertEquals(value, attribute.getValue());
	}
	
	@Test
	public void testSetEcert() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		assertTrue(attribute.isEcert());
	}
	
	@Test
	public void testGetEcert() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		assertTrue(attribute.isEcert());
	}
	
	@Test
	public void testDisplay() {
		
		final String name = "Name";
		final String value = "Value";
		final boolean ecert = true;
		
		Attributes attribute = new Attributes();
		attribute.setName(name);
		attribute.setValue(value);
		attribute.setEcert(ecert);
		
		attribute.display();
	}
}
