package com.qa.test;

import com.qa.restassureclient.RestAssureClientCall;
import com.qa.testbase.TestBase;
import io.restassured.response.ResponseBody;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REST_Assure_Get_Call {

    private String sURL;
    private String sCityName;
    private ResponseBody responseBody;

    @BeforeMethod
    public void setup(){
        sURL = TestBase.getinstance().prop.getProperty("ResourceURL");
        sCityName = TestBase.getinstance().prop.getProperty("CityName");
    }

    @Test
    public void getResponseBody(){
        responseBody = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, sCityName).getBody();
        System.out.println(responseBody.asString());
    }

}
