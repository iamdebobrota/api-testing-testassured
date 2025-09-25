package com.example;
import java.util.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetDataTest {

    @Test
    public void getUserName() {
        given().baseUri("https://reqres.in")
                .header("accept", "application/json")
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.first_name", equalTo("Janet")).log().all();
    }

    @Test
    public void getUserEmail() {
        given().baseUri("https://reqres.in").header("accept", "application/json")
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.first_name", equalTo("Janet")).log().all()
                .body("data.email", equalTo("janet.weaver@reqres.in")).log().all();
    }
}
