package com.example.DataDrivenTest;
import java.util.*;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;



public class DataDrivenTest {
    String keyString = "e2ce12713f2e305b1f70740877e11235";
    String tokenString = "ATTAf292e5975837ff43236afdd9b17f2957abfed71f73114da77b64e6779ead80aaC0C2868B";

    String baseUrl = "https://api.trello.com";
    String ID;


    @Test(priority = 0)
    public void createBoard() {
            Response res = given()
                    .queryParam("name", "Board123")
                    .queryParam("key", keyString)
                    .queryParam("token", tokenString)
                    .contentType("application/json")
                    .when()
                    .post(baseUrl + "/1/boards/")
                    .then()
                    .statusCode(200)
                    .extract().response();

            String str = res.asString();
            JsonPath jp = new JsonPath(str);
            ID = jp.get("id");
            System.out.println("Create Board ID" + ID);
    }

    @Test(priority = 1)
    public void getBoard() {
        given()
                .get(baseUrl + "/1/boards/" + ID + "?key=" + keyString + "&token=" + tokenString)
                .then()
                .statusCode(200)
                .log().all();
        System.out.println("Get board is successfully");
    }

    @Test(priority = 2)
    public void deleteBoard() {
        given()
                .delete(baseUrl + "/1/boards/" + ID + "?key=" + keyString + "&token=" + tokenString)
                .then()
                .statusCode(200)
                .log().all();
    }


}
