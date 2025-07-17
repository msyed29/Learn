package com.selenium.test.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentTest;

public class APITestBase extends TestBase {
    protected RequestSpecification requestSpec;
    protected static final String BASE_URL = "https://reqres.in/api";

    @BeforeClass
    public void setupAPI() {
        // Request Specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        // Set default spec for all requests
        RestAssured.requestSpecification = requestSpec;
    }
    
    protected void logToReport(String message) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.info(message);
        }
    }
}