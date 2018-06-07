package com.elumixor.theater.common;

import com.elumixor.theater.dataProviders.CommonDataProvider;
import org.testng.annotations.Test;

public class CommonTests {

    //This test method declares that its data should be supplied by the Data
    // Provider named "test1"
    @Test(dataProvider = "test1", dataProviderClass = CommonDataProvider.class)
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }
}
