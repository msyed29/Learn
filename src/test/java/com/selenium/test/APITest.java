package com.selenium.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.test.base.BaseAPITest;
import io.restassured.response.Response;

public class APITest extends BaseAPITest {

    @Test(description = "Get all posts")
    public void testGetPosts() {
        logToReport("Testing GET /posts endpoint");
        
        Response response = given()
            .when()
                .get("/posts")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .extract().response();
        
        logToReport("Response received with " + response.jsonPath().getList("$").size() + " posts");
    }

    @Test(description = "Get single post")
    public void testGetSinglePost() {
        int postId = 1;
        logToReport("Testing GET /posts/" + postId + " endpoint");
        
        Response response = given()
            .when()
                .get("/posts/" + postId)
            .then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .extract().response();
        
        Assert.assertNotNull(response.jsonPath().getString("title"), "Post title should not be null");
        logToReport("Successfully retrieved post: " + response.jsonPath().getString("title"));
    }

    @Test(description = "Create new post")
    public void testCreatePost() {
        String title = "foo";
        String body = "bar";
        int userId = 1;
        
        logToReport("Testing POST /posts endpoint");
        
        String requestBody = String.format("""
            {
                "title": "%s",
                "body": "%s",
                "userId": %d
            }""", title, body, userId);

        Response response = given()
            .body(requestBody)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .body("userId", equalTo(userId))
                .extract().response();
        
        Assert.assertNotNull(response.jsonPath().getString("id"), "ID should be returned");
        logToReport("Post created with ID: " + response.jsonPath().getString("id"));
    }

    @Test(description = "Update post")
    public void testUpdatePost() {
        int postId = 1;
        String title = "Updated Title";
        String body = "Updated Body";
        int userId = 1;
        
        logToReport("Testing PUT /posts/" + postId + " endpoint");
        
        String requestBody = String.format("""
            {
                "id": %d,
                "title": "%s",
                "body": "%s",
                "userId": %d
            }""", postId, title, body, userId);

        Response response = given()
            .body(requestBody)
            .when()
                .put("/posts/" + postId)
            .then()
                .statusCode(200)
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .extract().response();
        
        logToReport("Post updated successfully");
    }

    @Test(description = "Delete post")
    public void testDeletePost() {
        int postId = 1;
        logToReport("Testing DELETE /posts/" + postId + " endpoint");
        
        given()
            .when()
                .delete("/posts/" + postId)
            .then()
                .statusCode(200);
        
        logToReport("Post successfully deleted");
    }

    @Test(description = "Get comments for a post")
    public void testGetPostComments() {
        int postId = 1;
        logToReport("Testing GET /posts/" + postId + "/comments endpoint");
        
        Response response = given()
            .when()
                .get("/posts/" + postId + "/comments")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .extract().response();
        
        logToReport("Retrieved " + response.jsonPath().getList("$").size() + " comments for post " + postId);
    }
}