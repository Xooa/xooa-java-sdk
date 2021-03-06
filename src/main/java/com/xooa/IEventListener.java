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
 */

package com.xooa;

import org.json.JSONObject;

/**
 * Event Listener interface needed to create a listener which is required to
 * subscribe to events on the smart contract.
 *
 * @author kavi
 *
 */
public interface IEventListener {

	void onConnected(String message);

	void onAuthenticated(String message);

	void onEventReceived(JSONObject jsonObject);

	void onError(String error);

	void onUnauthorized(String error);
}
