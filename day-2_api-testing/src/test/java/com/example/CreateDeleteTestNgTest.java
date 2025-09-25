package com.example;
import java.util.*;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateDeleteTestNgTest {

    @DataProvider(name = "usersProvider")
    public Object[][] usersProviders() {
        return new Object[][] {
                {"John", "leader"},
                {"Jane", "Manager"},
                {"Alice", "developer"},
                {"Bob", "tester"}
        };
    }


    @Test(dataProvider = "usersProvider")
    public void createDeleteTestNgTest(String name, String job) {
        RestAssured.baseURI = "https://reqres.in/api";

        JSONObject authData = new JSONObject();
        authData.put("email", "eve.holt@reqres.in");
        authData.put("password", "cityslicka");

//        Login
        Response authResponse = given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(authData.toJSONString())
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(authResponse.getStatusCode(), 200);
        String token = authResponse.jsonPath().getString("token");
        System.out.println("Generated Token = " + token);



//        create a user

        JSONObject userData = new JSONObject();
        userData.put("name", name);
        userData.put("job", job);

        Response createResponse = given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(userData.toJSONString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract().response();

        String userId = createResponse.jsonPath().getString("id");
        System.out.println("Generated User ID = " + userId);


//delete the user
        Response deleteResponse = given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(userData.toJSONString())
                .when()
                .delete("/api/users/" + userId)
                .then()
                .extract().response();

        Assert.assertEquals(deleteResponse.getStatusCode(), 204, "Failed to delete user: " + userId);
        Assert.assertTrue(deleteResponse.getBody().asString().isEmpty(), "Delete response should be empty for user: " + userId);


    }

}
