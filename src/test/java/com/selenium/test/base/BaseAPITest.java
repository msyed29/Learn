package com.selenium.test.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.test.utils.ExtentManager;

public class BaseAPITest {
    protected RequestSpecification requestSpec;
    protected static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        
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