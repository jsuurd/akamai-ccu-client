# Akamai Content Control Utility Client for Java

Java client for the Akamai Content Control Utility API.

## Description

The Akamai Content Control Utility API is a simple REST API for automating content purge requests. This client implements the [Akamai Content Control Utility API v2][1] and the [Fast Purge API][2] (formerly known as the Content Control Utility API v3). 

The client has the following features:

* Adding a purge request to a purge queue. (v2, v3)
* Getting the status of a previous purge request. (v2)
* Getting the length of a purge queue. (v2)
* Supporting retry of failed requests using an exponential backoff algorithm.

## Dependencies

* [Akamai EdgeGrid Client for Java](https://github.com/akamai/AkamaiOPEN-edgegrid-java)
* [Google HTTP Client Library for Java](https://github.com/google/google-http-java-client)


[1]: https://developer.akamai.com/api/purge/ccu-v2/overview.html
[2]: https://developer.akamai.com/api/purge/ccu/overview.html