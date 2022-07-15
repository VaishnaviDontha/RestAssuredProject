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

public class putTestAPICall {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void putCall() {

        JSONObject data = new JSONObject();
        data.put("name", "morpheus");
        data.put("job", "zion resident");

        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                .log().all();

        response = requestSpecification.put("https://reqres.in/api/users/2");

        validatableResponse = response.then()
                                    .statusCode(200)
                                    .body("name", equalTo("morpheus"))
                                    .body("job", equalTo("zion resident"));

    }

    @Test
    public void putCallBDDStyle() {
        JSONObject data = new JSONObject();
        data.put("name", "morpheus");
        data.put("job", "zion resident");

        given().baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(data.toString())
                .log().all()

                .when()
                .put("/api/users/2")

                .then()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));

    }

}
