package com.APICalls;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class deleteTestAPICall {
    
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void deleteCall() {

        RestAssured.baseURI = "https://reqres.in/api/users/2";
        requestSpecification = RestAssured.given();
        response = requestSpecification.delete();

        validatableResponse = response.then();
        validatableResponse.statusCode(204);              
        
    }

    @Test
    public void deleteCallBDDStyle() {

        given().baseUri("https://reqres.in/api").
        when().delete("/users/2").
        then().statusCode(204);
            
    }
}
