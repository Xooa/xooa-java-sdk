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

import com.google.gson.annotations.SerializedName;


public class Attributes {
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("ecert")
	private boolean ecert;
	
	@SerializedName("value")
	private String value;
	
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public boolean isEcert() {
		
		return ecert;
	}
	
	public void setEcert(boolean ecert) {
		
		this.ecert = ecert;
	}
	
	public String getValue() {
		
		return value;
	}
	
	public void setValue(String value) {
		
		this.value = value;
	}
	
	public void display() {
		System.out.println("\t Name - " + name);
		System.out.println("\t Value - " + value);
		System.out.println("\t Ecert - " + ecert);
	}
}