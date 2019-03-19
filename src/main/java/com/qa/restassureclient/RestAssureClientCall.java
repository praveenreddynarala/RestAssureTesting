package com.qa.restassureclient;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

import java.util.HashMap;

public class RestAssureClientCall {

    private RequestSpecification httpRequest = null;

    private static RestAssureClientCall clientCallObj = null;

    public static RestAssureClientCall getInstance(){
        if(clientCallObj==null)
            clientCallObj = new RestAssureClientCall();
        return clientCallObj;
    }

    /**
     * Return Http Request
     * @param sURI - URI
     * @return - HTTP Request
     */
    private RequestSpecification getHttpResponse(String sURI){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = sURI;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step
        if (httpRequest == null)
            httpRequest = RestAssured.given();
        return httpRequest;
    }

    /**
     * This method will return REST Assure Response
     * @param sURI - URI of REST API
     * @param sValue - String data value
     * @return - Response
     */
    public Response getRESTAssureResponse(String sURI, String sValue){
        getHttpResponse(sURI);
        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        return httpRequest.request(Method.GET, sValue);
    }

    /**
     * This method used to return REST Assured Response through headers
     * @param sURI - URI of REST API
     * @param sValue - String data value
     * @param headerMap - Headers map object
     * @return  Response
     */
    public Response getRESTAssureResponseUsingHeaders(String sURI, String sValue, HashMap<String, String> headerMap){
        getHttpResponse(sURI);
        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        return httpRequest.headers(headerMap).request(Method.GET, sValue);
    }

    /**
     * Get post response
     * @param requestParams - requested JSONObject params
     * @param sURI - URI
     * @param sPostValue - requested value
     * @return - Response
     */
    public Response get_Post_Repornse(JSONObject requestParams, String sURI, String sPostValue){
        getHttpResponse(sURI);
        httpRequest.body(requestParams.toJSONString());
        return httpRequest.post(sPostValue);
    }

}
