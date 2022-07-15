package com.APICalls;

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

    String json = "{\"name\": \"morpheus\", \"job\": \"zion resident\" }";
    

    @Test
    public void putCall() {

        RestAssured.baseURI = "https://reqres.in/api/users/2";
        requestSpecification = RestAssured.given().contentType(ContentType.JSON).body(json);
        
        response = requestSpecification.put();

        validatableResponse = response.then().statusCode(200)
                           .body("name", equalTo("morpheus"))
                           .body("job", equalTo("zion resident"));
        
    }

    @Test
    public void putCallBDDStyle() {

        given().baseUri("https://reqres.in/api")
               .contentType(ContentType.JSON)
               .body(json).
        when().put("/users/2").
        then().statusCode(200)
              .body("name", equalTo("morpheus"))
              .body("job", equalTo("zion resident"));
        
    }
    
}
