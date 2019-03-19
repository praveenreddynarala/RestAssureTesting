package com.qa.test;

import com.qa.restassureclient.RestAssureClientCall;
import com.qa.testbase.TestBase;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REST_Assure_Post_Call {

    private JSONObject requestParams;
    private String sURL;
    private Response response;

    @BeforeMethod
    public void setup(){
        sURL = TestBase.getinstance().prop.getProperty("PostURL");
    }

    @Test
    public void validate_post_registration_successfull(){

        requestParams = new JSONObject();
        requestParams.put("FirstName", "Narala");
        requestParams.put("LastName", "P");
        requestParams.put("UserName", "praveen12pda34");
        requestParams.put("Password", "password1");
        requestParams.put("Email",  "praveenreddy.narala27@gmail.com");

        response = RestAssureClientCall.getInstance().get_Post_Repornse(requestParams, sURL, "/register");

        String sSuccessCode = response.jsonPath().get("SuccessCode");
        System.out.println("Response body ->"+response.body().asString());
        Assert.assertEquals(response.getStatusCode(), TestBase.getinstance().RESPONSE_CODE_201);
        Assert.assertEquals(sSuccessCode, "OPERATION_SUCCESS", "User already exists");

    }

    @Test
    public void validate_post_registration_unsuccessfull(){

        requestParams = new JSONObject();
        requestParams.put("FirstName", "Narala");
        requestParams.put("LastName", "P");
        requestParams.put("UserName", "praveen12pda34");
        requestParams.put("Password", "password1");
        requestParams.put("Email",  "praveenreddy.narala27@gmail.com");

        response = RestAssureClientCall.getInstance().get_Post_Repornse(requestParams, sURL, "/register");

        String sSuccessCode = response.jsonPath().get("FaultId");
        System.out.println("Response body ->"+response.body().asString());
        Assert.assertEquals(response.getStatusCode(), TestBase.getinstance().RESPONSE_CODE_200, "User already exists");
        Assert.assertEquals(sSuccessCode, "User already exists", "User already exists");

    }

}
