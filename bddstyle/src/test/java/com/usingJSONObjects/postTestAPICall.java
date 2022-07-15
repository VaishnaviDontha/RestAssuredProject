package com.usingJSONObjects;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class postTestAPICall {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void postCall() {

        JSONObject data = new JSONObject();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "cityslicka");

        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .log().all();

        response = requestSpecification.post("https://reqres.in/api/login");

        validatableResponse = response.then()
                                    .statusCode(200)
                                    .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void postCallBDDStyle() {
        JSONObject data = new JSONObject();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "cityslicka");

        given().baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(data.toString())
                .log().all()

                .when()
                .post("/api/login")

                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

}
