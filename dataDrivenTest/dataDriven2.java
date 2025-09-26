package com.example.DataDrivenTest;
import java.util.*;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class dataDriven2 {
    @DataProvider(name = "UserData")
    public Object[][] data() {
        return new Object[][] {
                {"eve.holt@reqres.in", "pistol"},
                {"eve.holt@reqres.in", "test"},
                {"eve.holt@reqres.in", "test2"},
                {"eve.holt@reqres.in", "test"},
                {"eve.holt@reqres.in", "test2"},
                {"eve.holt@reqres.in", "test"},
                {"eve.holt@reqres.in", "test2"},
        };
    }

    @Test(dataProvider = "UserData")
    public void register(String email, String password){
        JSONObject payload = new JSONObject();
        payload.put("email", email);
        payload.put("password", password);


        given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(payload.toJSONString())
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .log().all();
    }

}
