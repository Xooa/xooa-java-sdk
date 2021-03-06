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

package com.xooa.request;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Identity Request needed to be submitted to enrollIdentity call on API.
 *
 * @author kavi
 *
 */
public class IdentityRequest {

	@SerializedName("IdentityName")
	private String identityName;

	@SerializedName("Access")
	private String accessType;

	@SerializedName("canManageIdentities")
	private boolean canManageIdentities;

	@SerializedName("Attrs")
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

	public List<Attributes> getAttributes() {

		return attributes;
	}

	public void setAttributes(List<Attributes> attributes) {

		this.attributes = attributes;
	}

	public void display() {
		System.out.println("Identity Name - " + identityName);
		System.out.println("Access Type - " + accessType);
		System.out.println("Can Manage Identities - " + canManageIdentities);
		System.out.println("\nAttributes - ");

		for (Attributes attribute : attributes) {
			attribute.display();
		}
	}
}
