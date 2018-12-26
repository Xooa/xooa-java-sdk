# Xooa Get-Set-Del Smart Contract

This page provides an overview to Xooa Get-Set-Del smart contract functionalities.



## Overview

To test the names of the methods in the chaincode any method can be invoked with a single argument as "Xooa Test" and it will return a 200 success response saying "Method test call. Nothing will be committed to ledger". If the method does not exist in the chaincode an error response will be received.

This smart contract provides 3 functions:
  
  * Get
  * Set
  * Del

#### Get

Get method is used to fetch values associated with each of the keys passed in the arguments.

If get method is invoked with a single key it will return either a 200 response with the state of the key or a 404 response if the key does not exist in the blockchain.

If get method is invoked with multiple keys it will return a 200 response containing all the keys that exist and their states in a result array and the keys for which an error occurs in errors array.


#### Set

Set method is used to store the key value pairs in the ledger.

If set method is called with even number of arguments they are taken up as key value pairs and are stored in the ledger. A response with all the key value pairs in results and an empty error array is returned.

If set method is called with odd number of arguments the first n-1 arguments are taken as key value pairs and are stored in ledger and the last nth argument is returned in errors.


#### Del

Del method is used to delete the state of a key from the ledger.

del returns a response as an array of all the keys in the arguments passed.



## Deploy the Get-Set-Del smart contract 
 
1. Log in to the Xooa blockchain console at https://xooa.com/blockchain.

2. Go to **Apps** > **Deploy New**. If you didn't log in with your GitHub account, you will need to do it now.

3. Find the Github repository **xooa/xooa-java-sdk** with the smart contract. Tap **Select**, and then **Next**.
   > For accesing **Private Git Repos**, click or tap **Authorize Private Github Access**.

4. Select the Smart Contract **Xooa-Java** to deploy, and then click **Deploy**.

5. Relax:  Xooa is doing the blockchain heavy lifting. You will be redirected to app dashboard when the deployment completes.

6. Record the **API Token** when it is shown: you will need it to authorize API requests from Java SDK. API Token cannot be dispalyed after you closed the window, but it may get regenerated. 
   > **Tip:**  to regenerate the API token: 
   >
   > 1. Go to the **Identities** tab in the App. 
   > 2. Next to the ID, select **Actions**.
   > 3. Select **Regenerate API Token**, and then select **Generate**.


