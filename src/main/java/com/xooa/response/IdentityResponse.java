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
 * @author Vishal Mullur
 * @author Kavi Sarna
 */

package com.xooa.response;

import com.google.gson.annotations.SerializedName;
import com.xooa.request.Attributes;

import java.util.List;

/**
 * Identity Response received when a request is made to get identities details
 * from API.
 *
 * @author kavi
 *
 */
public class IdentityResponse {

	@SerializedName("IdentityName")
	private String identityName;

	@SerializedName("Access")
	private String accessType;

	@SerializedName("canManageIdentities")
	private boolean canManageIdentities;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("AppId")
	private String appId;

	@SerializedName("ApiToken")
	private String apiToken;

	@SerializedName("Id")
	private String id;

	@SerializedName("attrs")
	private List<Attributes> attributes;

	public String getIdentityName() {

		return identityName;
	}

	public void setIdentityName(String identityName) {

		this.identityName = identityName;
	}

	public String getAccessType() {

		return accessType;
	}

	public void setAccessType(String accessType) {

		this.accessType = accessType;
	}

	public boolean isCanManageIdentities() {

		return canManageIdentities;
	}

	public void setCanManageIdentities(boolean canManageIdentities) {

		this.canManageIdentities = canManageIdentities;
	}

	public String getCreatedAt() {

		return createdAt;
	}

	public void setCreatedAt(String createdAt) {

		this.createdAt = createdAt;
	}

	public String getAppId() {

		return appId;
	}

	public void setAppId(String appId) {

		this.appId = appId;
	}

	public String getApiToken() {

		return apiToken;
	}

	public void setApiToken(String apiToken) {

		this.apiToken = apiToken;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public List<Attributes> getAttributes() {

		return attributes;
	}

	public void setAttributes(List<Attributes> attributes) {

		this.attributes = attributes;
	}

	public void display() {

		System.out.println("Identity Name - " + identityName);
		System.out.println("Id - " + id);
		System.out.println("App Id - " + appId);
		System.out.println("API Token - " + apiToken);
		System.out.println("Access - " + accessType);
		System.out.println("Can Manage Identities - " + canManageIdentities);
		System.out.println("Created At - " + createdAt);
		System.out.println("\nAttributes - ");

		for (Attributes attribute : attributes) {
			attribute.display();
		}
	}
}
