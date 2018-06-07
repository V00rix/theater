package com.elumixor.theater.dataProviders;

import org.testng.annotations.DataProvider;

public class CommonDataProvider {
    @DataProvider(name = "test1")
    public static Object[][] createData1() {
        return new Object[][] {
                { "Cedric", 36},
                { "Anne", 37},
        }; // ZDE LOAD Z GRIDU / DB
    }
}
