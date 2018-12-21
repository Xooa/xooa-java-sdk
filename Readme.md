[![Build Status](http://3.84.45.239:8080/buildStatus/icon?job=xooa-java-sdk)](http://3.84.45.239:8080/job/xooa-java-sdk/)

# Xooa Java SDK

The official Xooa SDK for Java to connect with the Xooa Blockchain PaaS.

Xooa (pronounced ZUU-ah) is dedicated to making blockchain easy. Focus on business problems, not blockchain problems.

This SDK refers to APIs available for Xooa platform. For more details, refer: <https://api.xooa.com/explorer>

The platform documentation is available at <https://docs.xooa.com>

## Installation
---

There are two ways to install Xooa SDK in your project.

You can either download the source code from [Git](https://github.com/Xooa/xooa-java-sdk) and run

    $ mvn clean install
    
this will build the jar for xooa-java-sdk-VERSION-jar-with-dependencies which you can then include in your project as an external jar.

The other and easier way is to use a dependency management:

You can add a dependency in maven as:
```
    <dependency>
        <groupId>com.xooa</groupId>
        <artifactId>xooa-java-sdk</artifactId>
        <version>1.0.3</version>
    </dependency>
```

You can include it in Gradle with:
```
    compile 'com.xooa:xooa-java-sdk:VERSION'
```
    

## Usage
---
### [com.xooa.XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java)
```Java
import com.xooa.XooaClient
```
*This is the base class of Xooa Java SDK.*
This class contains all the methods made available by the SDK. 

## Summary
-------

### Properties

##### API Token
The API Token for the app provided on Xooa Platform when you deploy an app there. You can also regenerate a new API Token for an Identity by going to Identities tab in the app and clicking on Actions -> Regenerate API Token.
```Java
    private String apiToken
        public void setApiToken(String apiToken)
        public String getApiToken()
```

##### App URL
The App URL where the app is deployed. It is default to [Xooa](https://api.xooa.com/api/v1). You can change it if you want to test your app in a local environment.
```Java
    private String appUrl
        public void setAppUrl(String appUrl)
        public String getAppUrl()
```

##### Web Service
Internal [Web Service](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/WebService.java) Object used to [invoke Xooa API](https://api.xooa.com/explorer/#!/Smart_Contract/Invoke). It is usually automatically set when you create a [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java) object.
```Java
    private WebService webService
        public void setWebService(WebService webService)
```

##### IEventListener
[Event Listener](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/IEventListener.java) callback interface to listen to events generated by the smart contract. If you wish to listen to the event generated by the snart contract you will need to create an object of [IEventListener](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/IEventListener.java) interface and give implementations for all tasks related to it. You can then pass that object to [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java) constructor and call the subscribe() method on it.
```Java
    private IEventListener eventListener
        public XooaClient(String apiToken, IEventListener eventListener)
```


### Constructors

##### public XooaClient()

```Java
    public XooaClient()
```
Default Constructor of [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java).


##### public XooaClient(String apiToken)

```Java
    public XooaClient(String apiToken)
```
Constructor to create a [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java) object with the given API Token.


##### public XooaClient(String apiToken, String appUrl)

```Java
    public XooaClient(String apiToken, String appUrl)
```
Constructor to create a [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java) object with the given Xooa API Token and the App Url where the app is deployed.


##### public XooaClient(String apiToken, IEventListener eventListener)

```Java
    public XooaClient(String apiToken, IEventListener eventListener)
```
Constructor to create a [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java) object with given API Token and the [IEventListener](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/IEventListener.java) object for callback.


### Methods

##### isValid()

```Java
    public boolean isValid() throws XooaApiException
```
Method to Validate if the given API Token is valid or not.

Return - Boolean
True or False based on the authentication of the API Token.

Throws - [XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java)
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) giving the error code and error message.


##### subscribe()
```Java
    public void subscribe()
```
Method to subscribe to events generated in the smart contract. This method can only be used if an event listener was provided at the time of creation of [XooaClient](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/XooaClient.java).


##### unsubscribe()

```Java
    public void unsubscribe()
```
Method to unsubscribe the events from the smart contract.


##### invoke(String functionName, String[] args)
```Java
    public InvokeResponse invoke(String functionName, String[] args) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to Invoke blockchain to submit transactions. For more details refer [Xooa Invoke API](https://api.xooa.com/explorer/#!/Smart_Contract/Invoke).

Return - InvokeResponse
Instance of [Invoke Response](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/InvokeResponse.java) giving Transaction Id and Payload returned by the smart contract.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### invoke(String functionName, String[] args, long timeout)
```Java
    public InvokeResponse invoke(String functionName, String[] args, long timeout) throws 
    XooaApiException, XooaRequestTimeoutException
```
Method to Invoke blockchain to submit transactions. For more details refer [Xooa Invoke API](https://api.xooa.com/explorer/#!/Smart_Contract/Invoke).

Return - InvokeResponse
Instance of [Invoke Response](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/InvokeResponse.java) giving Transaction Id and Payload returned by the smart contract.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### invokeAsync(String functionName, String[] args)
```Java
    public PendingTransactionResponse invokeAsync(String functionName, String[] args) throws 
    XooaApiException
```
Method to Invoke blockchain in async mode to submit transactions. For more details refer [Xooa Invoke API](https://api.xooa.com/explorer/#!/Smart_Contract/Invoke).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.



##### query(String functionName, String[] args)
```Java
    public QueryResponse query(String functionName, String[] args) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to Query blockchain to fetch state for arguments. For more details refer [Xooa Query API](https://api.xooa.com/explorer/#!/Smart_Contract/Query).

Return - QueryResponse
Instance of [QueryResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/QueryResponse.java) giving payload returned by the smart contract.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### query(String functionName, String[] args, long timeout)
```Java
    public QueryResponse query(String functionName, String[] args, long timeout) throws 
    XooaApiException, XooaRequestTimeoutException
```
Method to Query blockchain to fetch state for arguments. For more details refer [Xooa Query API](https://api.xooa.com/explorer/#!/Smart_Contract/Query).

Return - QueryResponse
Instance of [QueryResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/QueryResponse.java) giving payload returned by the smart contract.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### queryAsync(String functionName, String[] args)
```Java
    public PendingTransactionResponse queryAsync(String functionName, String[] args) throws 
    XooaApiException
```
Method to Query blockchain in async mode to fetch state for arguments. For more details refer [Xooa Query API](https://api.xooa.com/explorer/#!/Smart_Contract/Query).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.



##### getCurrentIdentity()
```Java
    public IdentityResponse getCurrentIdentity() throws XooaApiException, XooaRequestTimeoutException
```
Method to get the authenticating identity details. For more details refer [Authenticated Identity](https://api.xooa.com/explorer/#!/Identities/Authenticated_Identity).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving details about the identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getIdentities()
```Java
    public List<IdentityResponse> getIdentities() throws XooaApiException, XooaRequestTimeoutException
```
Method to get a list of all the identities associated with the app. For more details refer [Get All Identitites](https://api.xooa.com/explorer/#!/Identities/Identities_getAllIdentities).

Return - List<IdentityResponse>
List containing all the instances of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java).

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### enrollIdentity(IdentityRequest identityRequest)
```Java
    public IdentityResponse enrollIdentity(IdentityRequest identityRequest) throws XooaApiException, 
    XooaRequestTimeoutException 
```
Method to enroll a new identity for the app. For more details refer [Enroll Identity](https://api.xooa.com/explorer/#!/Identities/Enrollment).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving details about the new identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### enrollIdentity(IdentityRequest identityRequest, long timeout)
```Java
    public IdentityResponse enrollIdentity(IdentityRequest identityRequest, long timeout) throws 
    XooaApiException, XooaRequestTimeoutException
```
Method to enroll a new identity for the app. For more details refer [Enroll Identity](https://api.xooa.com/explorer/#!/Identities/Enrollment).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving details about the new identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### enrollIdentityAsync(IdentityRequest identityRequest)
```Java
    public PendingTransactionResponse enrollIdentityAsync(IdentityRequest identityRequest) throws
    XooaApiException
```
Method to enroll a new identity for the app in async mode. For more details refer [Enroll Identity](https://api.xooa.com/explorer/#!/Identities/Enrollment).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.


##### regenerateIdentityApiToken(String identityId)
```Java
    public IdentityResponse regenerateIdentityApiToken(String identityId) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to regenerate a new API Token for the given identity id. For more details refer [Regenerate Token](https://api.xooa.com/explorer/#!/Identities/Regenerate_Token).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving details about the new identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### regenerateIdentityApiToken(String identityId, long timeout)
```Java
    public IdentityResponse regenerateIdentityApiToken(String identityId, long timeout) throws 
    XooaApiException, XooaRequestTimeoutException
```
Method to regenerate a new API Token for the given identity id. For more details refer [Regenerate Token](https://api.xooa.com/explorer/#!/Identities/Regenerate_Token).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving details about the new identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### regenerateIdentityApiTokenAsync(String identityId)
```Java
    public PendingTransactionResponse regenerateIdentityApiTokenAsync(String identityId) throws
    XooaApiException 
```
Method to regenerate a new API Token for the given identity id in async mode. For more details refer [Regenerate Token](https://api.xooa.com/explorer/#!/Identities/Regenerate_Token).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.


##### getIdentity(String identityId)
```Java
    public IdentityResponse getIdentity(String identityId) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to get Identity details for the given identity id. For more details refer [Identity Information](https://api.xooa.com/explorer/#!/Identities/Get_identity).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving details about the new identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### deleteIdentity(String identityId)
```Java
    public boolean deleteIdentity(String identityId) throws XooaApiException,
    XooaRequestTimeoutException
```
Method to delete the identity from the app for the given identity id. For more details refer [Delete Identity](https://api.xooa.com/explorer/#!/Identities/Delete_Identity).

Return - Boolean
True if identity was deleted false otherwise.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### deleteIdentity(String identityId, long timeout)
```Java
    public boolean deleteIdentity(String identityId, long timeout) throws XooaApiException,
    XooaRequestTimeoutException
```
Method to delete the identity from the app for the given identity id. For more details refer [Delete Identity](https://api.xooa.com/explorer/#!/Identities/Delete_Identity).

Return - Boolean
True if identity was deleted false otherwise.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### deleteIdentityAsync(String identityId)
```Java
    public PendingTransactionResponse deleteIdentityAsync(String identityId) throws XooaApiException
```
Method to delete the identity from the app for the given identity id in async mode. For more details refer [Delete Identity](https://api.xooa.com/explorer/#!/Identities/Delete_Identity).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.



##### getCurrentBlock()
```Java
    public CurrentBlockResponse getCurrentBlock() throws XooaApiException, XooaRequestTimeoutException
```
Method to get the block number and hashes of current (highest) block. For more details refer [Get Current Block](https://api.xooa.com/explorer/#!/Ledger/BlockHeight).

Return - CurrentBlockResponse
Instance of [CurrentBlockResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/CurrentBlockResponse.java) giving the block number and hashes of current block.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getCurrentBlockAsync()
```Java
    public PendingTransactionResponse getCurrentBlockAsync() throws XooaApiException
```
Method to get the block number and hashes of current (highest) block in async mode. For more details refer [Get Current Block](https://api.xooa.com/explorer/#!/Ledger/BlockHeight).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.


##### getBlockByNumber(long blockNumber)
```Java
    public BlockResponse getBlockByNumber(long blockNumber) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to get the block number and hashes of the block number. For more details refer [Get Block](https://api.xooa.com/explorer/#!/Ledger/BlockData).

Return - BlockResponse
Instance of [BlockResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/BlockResponse.java) giving the block number and hashes of the block.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getBlockByNumberAsync(long blockNumber)
```Java
    public PendingTransactionResponse getBlockByNumberAsync(long blockNumber) throws XooaApiException
```
Method to get the block number and hashes of the block number in async mode. For more details refer [Get Block](https://api.xooa.com/explorer/#!/Ledger/BlockData).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.


##### getTransaction(String transactionId)
```Java
    public TransactionResponse getTransaction(String transactionId) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to get the transaction details for the transaction id. For more details refer [Get Transaction By TransactionId](https://api.xooa.com/explorer/#!/Ledger/BlockData_0).

Return - TransactionResponse
Instance of [TransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/TransactionResponse.java) giving the details about the transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getTransactionAsync(String transactionId)
```Java
    public PendingTransactionResponse getTransactionAsync(String transactionId) throws
    XooaApiException
```
Method to get the transaction details for the transaction id in async mode. For more details refer [Get Transaction By TransactionId](https://api.xooa.com/explorer/#!/Ledger/BlockData_0).

Return - PendingTransactionResponse
Instance of [PendingTransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/PendingTransactionResponse.java) giving result id and result url for pending transaction.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.



##### getResultForInvoke(String resultId)
```Java
    public InvokeResponse getResultForInvoke(String resultId) throws XooaApiException,
    XooaRequestTimeoutException
```
Method to get Invoke Response for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - InvokeResponse
Instance of [InvokeResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/InvokeResponse.java) giving the transaction id and payload.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getResultForQuery(String resultId)
```Java
    public QueryResponse getResultForQuery(String resultId) throws XooaApiException,
    XooaRequestTimeoutException
```
Method to get Query Response for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - QueryResponse
Instance of [QueryResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/QueryResponse.java) giving the payload.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getResultForIdentity(String resultId)
```Java
    public IdentityResponse getResultForIdentity(String resultId) throws XooaApiException,
    XooaRequestTimeoutException
```
Method to get Identity Response for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - IdentityResponse
Instance of [IdentityResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/IdentityResponse.java) giving the details related to the identity.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getResultForIdentityDelete(String resultId)
```Java
    public boolean getResultForIdentityDelete(String resultId) throws XooaApiException,
    XooaRequestTimeoutException
```
Method to get delete response for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - Boolean
True if the identity was deleted false otherwise.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getResultForCurrentBlock(String resultId)
```Java
    public CurrentBlockResponse getResultForCurrentBlock(String resultId) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to get CurrentBlockResponse for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - CurrentBlockResponse
Instance of [CurrentBlockResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/CurrentBlockResponse.java) giving the block number and hashes for the current block.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getResultForBlockByNumber(String resultId)
```Java
    public BlockResponse getResultForBlockByNumber(String resultId) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to get BlockResponse for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - BlockResponse
Instance of [BlockResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/BlockResponse.java) giving the block number and hashes for the block.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.


##### getResultForTransaction(String resultId)
```Java
    public TransactionResponse getResultForTransaction(String resultId) throws XooaApiException, 
    XooaRequestTimeoutException
```
Method to get TransactionResponse for a request in pending state. For more detials refer [Result API](https://api.xooa.com/explorer/#!/Result/Result).

Return - TransactionResponse
Instance of [TransactionResponse](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/response/TransactionResponse.java) giving the transaction details for the transaction id.

Throws - 
[XooaApiException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaApiException.java) - Instance of XooaApiException giving the error code and error message.
[XooaRequestTimeoutException](https://github.com/Xooa/xooa-java-sdk/blob/master/src/main/java/com/xooa/exception/XooaRequestTimeoutException.java) - Instance of XooaRequestTimeoutException giving result id and result url for pending transaction.
