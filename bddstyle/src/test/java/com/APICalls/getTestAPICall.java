package com.APICalls;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getTestAPICall {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getCall() {

        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        requestSpecification = RestAssured.given();
        response = requestSpecification.get();

        String resString = response.prettyPrint();
        System.out.println(resString);

        validatableResponse = response.then();
        validatableResponse.statusCode(200);        

        // validatableResponse.statusLine(expectedStatusLine);        
        
    }

    @Test
    public void getCallBDDStyle() {

        given().
        when().get("https://reqres.in/api/users?page=2").
        then().statusCode(200)
              .body("page", equalTo(2))
              .body("data.id[0]",equalTo(7));

    }
    
}
