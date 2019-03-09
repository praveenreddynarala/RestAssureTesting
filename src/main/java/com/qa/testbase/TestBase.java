package com.qa.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	
	Properties prop = null;
	
	private static TestBase testBaseObj = null;
	
	private TestBase() {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(fi);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
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
