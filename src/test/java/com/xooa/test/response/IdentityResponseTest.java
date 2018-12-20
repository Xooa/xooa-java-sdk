package com.xooa.test.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xooa.request.Attributes;
import com.xooa.response.IdentityResponse;

public class IdentityResponseTest {
	
	private final String identityName = "Name";
	private final String id = "22422";
	private final String appId = "asdsnfs22423";
	private final String apiToken = "Hello";
	private final String accessType = "r";
	private final boolean canManageIdentities = false;
	private final List<Attributes> attributes = new ArrayList<>();

	@Test
	public void testIdentityResponse() {
		
		new IdentityResponse();
	}
	
	@Test
	public void testGetIdentityName() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(identityName, identity.getIdentityName());
	}
	
	@Test
	public void testSetIdentityName() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(identityName, identity.getIdentityName());
	}
	
	@Test
	public void testGetId() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(id, identity.getId());
	}
	
	@Test
	public void testSetId() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(id, identity.getId());
	}
	
	@Test
	public void testGetAppId() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(appId, identity.getAppId());
	}
	
	@Test
	public void testSetAppId() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(appId, identity.getAppId());
	}
	
	@Test
	public void testGetApiToken() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(apiToken, identity.getApiToken());
	}
	
	@Test
	public void testSetApiToken() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(apiToken, identity.getApiToken());
	}
	
	@Test
	public void testGetAccessType() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(accessType, identity.getAccessType());
	}
	
	@Test
	public void testSetAccessType() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(accessType, identity.getAccessType());
	}
	
	@Test
	public void testIsCanManageIdentities() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertFalse(canManageIdentities);
	}
	
	@Test
	public void testSetCanManageIdentities() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertFalse(canManageIdentities);
	}
	
	@Test
	public void testGetAttributes() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(attributes, identity.getAttributes());
	}
	
	@Test
	public void testSetAttributes() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		assertEquals(attributes, identity.getAttributes());
	}
	
	@Test
	public void testDisplay() {
		
		IdentityResponse identity = new IdentityResponse();
		identity.setIdentityName(identityName);
		identity.setId(id);
		identity.setAppId(appId);
		identity.setApiToken(apiToken);
		identity.setAccessType(accessType);
		identity.setCanManageIdentities(canManageIdentities);
		identity.setAttributes(attributes);
		
		identity.display();
	}
}
