package com.qa.test;

import com.qa.restassureclient.RestAssureClientCall;
import com.qa.testbase.TestBase;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REST_Assure_Get_Call {

    private String sURL;
    private String sCityName;
    private ResponseBody responseBody;
    private int sStatusCode;
    private String sStatusLine;

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

    @Test
    public void validate_get_response_status_code(){
        sStatusCode = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, sCityName).getStatusCode();
        System.out.println("Response status code-->"+sStatusCode);
        Assert.assertEquals(sStatusCode, TestBase.getinstance().RESPONSE_CODE_200);
    }

    @Test
    public void validate_get_incorrect_response_status_code(){
        sStatusCode = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, "/Kamareddy").getStatusCode();
        System.out.println("Response status code-->"+sStatusCode);
        sStatusLine = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, "/Kamareddy").getStatusLine();
        System.out.println("Response status line-->"+sStatusLine);
        Assert.assertTrue(sStatusCode == TestBase.getinstance().RESPONSE_CODE_400, "Incorrect response status code-->"+sStatusCode);
    }

    @Test
    public void validate_get_incorrect_response_status_line(){
        sStatusLine = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, "/Kamareddy").getStatusLine();
        System.out.println("Response status line-->"+sStatusLine);
        Assert.assertTrue(sStatusLine.equalsIgnoreCase(TestBase.getinstance().BAD_RESPONSE_STATUS_LINE), "Incorrect response status line-->"+sStatusLine);
    }

    @Test
    public void validate_get_response_status_line(){
        sStatusLine = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, sCityName).getStatusLine();
        System.out.println("Response status line-->"+sStatusLine);
        Assert.assertEquals(sStatusLine, TestBase.getinstance().RESPONSE_STATUS_LINE, "Incorrect status line");
    }

}
