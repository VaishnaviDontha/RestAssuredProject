package com.usingHashMap;

import java.util.HashMap;

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

        HashMap<String,String> data = new HashMap<String,String>();
        data.put("name", "morpheus");
        data.put("job", "zion resident");

        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(data)
                .log().all();

        response = requestSpecification.put("https://reqres.in/api/users/2");

        validatableResponse = response.then()
                                    .statusCode(200)
                                    .body("name", equalTo("morpheus"))
                                    .body("job", equalTo("zion resident"))
                                    .statusLine("HTTP/1.1 200 OK");
        
    }

    @Test
    public void putCallBDDStyle() {
        HashMap<String,String> data = new HashMap<String,String>();
        data.put("name", "morpheus");
        data.put("job", "zion resident");

        given().baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(data).
        when().put("/api/users/2").
        then().statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .statusLine("HTTP/1.1 200 OK")
                .log()
                .all();
        
    }

    


    
}
