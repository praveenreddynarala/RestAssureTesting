package com.qa.restassureclient;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class RestAssureClientCall {

    private static RestAssureClientCall clientCallObj = null;

    public static RestAssureClientCall getInstance(){
        if(clientCallObj==null)
            clientCallObj = new RestAssureClientCall();
        return clientCallObj;
    }

    /**
     * This method will return REST Assure Response
     * @param sURI - URI of REST API
     * @param sValue - String data value
     * @return - Response
     */
    public Response getRESTAssureResponse(String sURI, String sValue){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = sURI;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        return httpRequest.request(Method.GET, sValue);
    }

    public Response getRESTAssureResponseUsingHeaders(String sURI, String sValue, HashMap<String, String> headerMap){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = sURI;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step
        RequestSpecification httpRequest = RestAssured.given();


        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        return httpRequest.headers(headerMap).request(Method.GET, sValue);
    }

}
