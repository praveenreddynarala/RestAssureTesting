package com.qa.TestUtils;

public class Util {

    private static Util uObj = null;

    private Util(){

    }

    public static Util getInstance(){
        if (uObj == null)
            uObj = new Util();
        return uObj;
    }

}
