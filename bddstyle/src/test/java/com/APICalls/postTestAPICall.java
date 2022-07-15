package com.APICalls;

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

    String json = "{\"name\": \"morpheus\", \"job\": \"leader\" }";

    @Test
    public void postCall() {

        RestAssured.baseURI = "https://reqres.in/api/users";
        requestSpecification = RestAssured.given().contentType(ContentType.JSON).body(json);

        response = requestSpecification.post();

        String resString = response.prettyPrint();
        System.out.println(resString);

        validatableResponse = response.then();
        validatableResponse.statusCode(201);
        validatableResponse.body("name", equalTo("morpheus"));
        validatableResponse.body("job", equalTo("leader"));

    }

    @Test
    public void postCallBDDStyle() {

        given().baseUri("https://reqres.in/api")
               .body(json)
               .contentType(ContentType.JSON).
        when().post("/users").
        then().statusCode(201)
              .body("name", equalTo("morpheus"))
              .body("job", equalTo("leader"));

    }

}
