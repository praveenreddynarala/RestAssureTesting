package com.qa.test;

import com.qa.TestUtils.Util;
import com.qa.restassureclient.RestAssureClientCall;
import com.qa.testbase.TestBase;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class REST_Assure_Get_Call {

    private String sURL;
    private String sCityName;
    private ResponseBody responseBody;
    private int sStatusCode;
    private String sStatusLine;
    private Headers headers;

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

    @Test
    public void validate_get_all_headers(){
        headers = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, sCityName).getHeaders();
        headers.forEach((k)->System.out.println("Key: " + k.getName() + " -- Value: " + k.getValue()));
    }

    @Test
    public void validate_response_headers_using_lamda_expression(){
        headers = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, sCityName).getHeaders();
        headers.forEach((k)-> {
            if (k.getName().equals("Server"))
                Assert.assertTrue(k.getValue().equals("application/json"), "Header doesn't exists");
        });
    }

    @Test
    public void validate_response_body_using_jsonobject(){
        JSONObject jObj = RestAssureClientCall.getInstance().getRESTAssureResponse(sURL, sCityName).body().as(JSONObject.class);
        String sTemp = Util.getValueByJPath(jObj, "/City");
        System.out.println(sTemp);
        Assert.assertTrue(sTemp.equalsIgnoreCase("35.85 Degree celsius"), "City doesn't exist");
    }

    @Test
    public void validate_get_API_With_Headers(){
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Connection", "keep-alive");
        headerMap.put("Server", "nginx");

        String responseBody = RestAssureClientCall.getInstance().getRESTAssureResponseUsingHeaders(sURL, sCityName, headerMap).body().asString();
        System.out.println(responseBody);

    }

}
