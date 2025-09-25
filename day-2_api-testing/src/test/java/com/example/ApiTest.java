package com.example;
import java.util.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ApiTest {

    @Test
    public void check_username() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("username", equalTo("Bret"))
                .body("email", equalTo("Sincere@april.biz"))
                .body("name", equalTo("Leanne Graham")).log().all();
    }

    @Test
    public void check_userEmail() {
        given().baseUri("https://reqres.in")
                .header("accept", "application/json")
                .header("contentType", "application/json")
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver")).log().all();
    }


    @Test
    public void createUser() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("first_name", "debu");
    payload.put("last_name", "H");
    payload.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
    payload.put("email", "debu@gmail.com");

        given().baseUri("https://reqres.in")
                .header("x-api-key", "reqres-free-v1")
                .body(payload)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
//                .body("first_name", equalTo("debu"))
                .body("createdAt", notNullValue())
                .body("id", notNullValue()).log().all();
    }

    @Test
    public void postUser() {
    JSONObject payload = new JSONObject();
    payload.put("name", "Test");
    payload.put("job", "Deveoper");

        given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(payload.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log()
                .all();
    }

    @Test
    public void loginUser() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("email", "eve.holt@reqres.in");
    payload.put("password", "cityslicka");

        given().baseUri("https://reqres.in")
                .header("x-api-key", "reqres-free-v1")
                .body(payload)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
//                .body("first_name", equalTo("debu"))
//                .body("token", notNullValue())
                .log().all();
    }

    @Test
    public void updateUserPutRequest() {
    JSONObject payload = new JSONObject();
    payload.put("name", "Test");
    payload.put("job", "Developer");

        given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(payload.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void updateUserPatchRequest() {
    JSONObject payload = new JSONObject();
    payload.put("name", "Test");
    payload.put("job", "Developer");

        given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(payload.toJSONString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void deleteUserRequest() {
        given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log()
                .all();
    }

    @Test
    public void authTestRequest() {
        given()
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .header("Authorization", "Bearer " + "3a5d05c290a8244a9732200b39281db48dc2bba57a7b1da61ce7a0f798482eff")
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log()
                .all();
    }

    @Test
    public void currentauth(){
        given()
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MzA2MjY5NTgsImV4cCI6MTczMDYyODc1OH0.4nCfLW7v28wg_CLltgHgMPgK9T0GUk0SPT6eJukIg6I")
                .get("https://dummyjson.com/auth/me").then().statusCode(200).log().all();
    }

}
