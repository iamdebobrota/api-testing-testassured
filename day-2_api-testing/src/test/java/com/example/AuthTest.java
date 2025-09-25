package com.example;
import java.util.*;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AuthTest {

    @Test
    public void submitOrder() {
        RestAssured.baseURI = "https://simple-books-api.click";

        JSONObject authData = new JSONObject();
        authData.put("clientName", "debu");
        authData.put("clientEmail", "debu1234+4@gmail.com");

        Response authResponse = given()
                .contentType("application/json")
                .body(authData.toJSONString())
                .when()
                .post("/api-clients/")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

//        extract the token
        String token = authResponse.jsonPath().getString("accessToken");
        System.out.println("Generate Token " + token);


////        Place an order using token
        JSONObject orderData = new JSONObject();
        orderData.put("bookId", 1);
        orderData.put("customerName", "John");


        Response orderResponse = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(orderData.toJSONString())
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .extract().response();

        String orderId = orderResponse.jsonPath().getString("orderId");
        System.out.println("Created OrderID " + orderId);



//    delete

        Response deleteResponse = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/orders/" + orderId)
                .then()
                .statusCode(204)
                .log().all().extract().response();

        Assert.assertTrue(deleteResponse.getBody().asString().isEmpty(), "Delete  response body should be empty");
        Assert.assertEquals(deleteResponse.getHeader("Content-Type"), "application/json");
        Assert.assertTrue(deleteResponse.getTime() < 3000, "Response time is too slow!");

    }
}
