package com.xooa.test.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xooa.request.Attributes;
import com.xooa.request.IdentityRequest;
import com.xooa.response.IdentityResponse;

public class IdentityRequestTest {
	
	private final String identityName = "Name";
	private final String accessType = "r";
	private final boolean canManageIdentities = false;
	private final List<Attributes> attributes = new ArrayList<Attributes>();
	
	@Test
	public void testIdentityRequest() {
		
		new IdentityRequest();
	}
	
	@Test
	public void testSetIdentityName() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(identityName, identity.getIdentityName());
	}
	
	@Test
	public void testGetIdentityName() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(identityName, identity.getIdentityName());
	}
	
	@Test
	public void testSetAccessType() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(accessType, identity.getAccessType());
	}
	
	@Test
	public void testGetAccessType() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(accessType, identity.getAccessType());
	}
	
	@Test
	public void testSetCanManageIdentities() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertFalse(identity.isCanManageIdentities());
	}
	
	@Test
	public void testIsCanManageIdentities() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertFalse(identity.isCanManageIdentities());
	}
	
	@Test
	public void testGetAttributes() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(attributes, identity.getAttributes());
	}
	
	@Test
	public void testSetAttributes() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(attributes, identity.getAttributes());
	}
	
	@Test
	public void testDisplay() {
		
		IdentityRequest identity = new IdentityRequest();
		identity.setIdentityName(identityName);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		identity.display();
		
		
	}
}
