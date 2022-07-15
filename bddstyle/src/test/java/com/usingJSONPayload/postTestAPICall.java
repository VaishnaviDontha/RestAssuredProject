package com.usingJSONPayload;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class postTestAPICall {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    File jsonData = new File("src/test/resources/payload.json");

    @Test
    public void postCall() {

        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .log().all();

        response = requestSpecification.post("https://reqres.in/api/login");

        validatableResponse = response.then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void postCallBDDStyle() {

        given().baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .log().all()

                .when()
                .post("/api/login")

                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

}
