package com.qa.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	//200 (OK)
	//GET an entity corresponding to the requested resource is sent in the response;
	public int RESPONSE_CODE_200 = 200;
	public int RESPONSE_CODE_201 = 201;
	public int RESPONSE_CODE_204 = 204;
	//400 (Bad Request)
	//400 is the generic client-side error status,
	// used when no other 4xx error code is appropriate.
	// Errors can be like malformed request syntax, invalid request message parameters, or deceptive request routing etc.
	public int RESPONSE_CODE_400 = 400;
	public int RESPONSE_CODE_401 = 401;
	public int RESPONSE_CODE_500 = 500;

	public String RESPONSE_STATUS_LINE = "HTTP/1.1 200 OK";
	public String BAD_RESPONSE_STATUS_LINE = "HTTP/1.1 400 Bad Request";

	public Properties prop;
	
	private static TestBase testBaseObj = null;
	
	private TestBase() {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(fi);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static TestBase getinstance() {
		if(testBaseObj == null)
			testBaseObj = new TestBase();
		return testBaseObj;
	}
	
	
	
}
