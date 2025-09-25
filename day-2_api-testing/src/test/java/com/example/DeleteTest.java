package com.example;
import java.util.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteTest {

    @Test
    public void deleteUser() {
        given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log().all();
    }

}
