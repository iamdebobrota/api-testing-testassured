package com.example;
import java.util.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CreateUpdateTest {

    @Test
    public void createUser() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("first_name", "Jane");
        payload.put("last_name", "Jane");
        payload.put("email", "Jane@example.com");
        payload.put("avatar", "https://reqres.in/img/faces/2-image.jpg");


        given().baseUri("https://reqres.in")
                .header("accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @Test
    public void updateUser() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("first_name", "Jane");
        payload.put("last_name", "Doe");
        payload.put("email", "Jane@example.com");
        payload.put("avatar", "https://reqres.in/img/faces/2-image.jpg");


        given().baseUri("https://reqres.in")
                .header("accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(payload)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("first_name", equalTo("Jane"))
                .body("last_name", equalTo("Doe"));


        given().baseUri("https://reqres.in")
                .header("accept", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json")
                .body(payload)
                .when()
                .patch("/api/users/2")
                .then()
                .statusCode(200)
                .body("first_name", equalTo("Jane"))
                .body("last_name", equalTo("Doe"));
    }



}
