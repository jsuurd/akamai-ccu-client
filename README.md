# Akamai Content Control Utility Client for Java

Java client for the Akamai Content Control Utility API.

## Description

The Akamai Content Control Utility API is a simple REST API for automating content purge requests. This client implements the [Akamai Content Control Utility API v2](https://developer.akamai.com/api/purge/ccu-v2/overview.html). 

The client has the following features:

* Adding a purge request to a purge queue.
* Getting the status of a previous purge request.
* Getting the length of a purge queue. 
* Supporting retry of failed requests using an exponential backoff algorithm.

## Dependencies

* [Akamai EdgeGrid Client for Java](https://github.com/akamai/AkamaiOPEN-edgegrid-java)
* [Google HTTP Client Library for Java](https://github.com/google/google-http-java-client)
