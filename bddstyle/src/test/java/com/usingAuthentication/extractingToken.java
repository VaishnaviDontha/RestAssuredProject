package com.usingAuthentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import POJOClasses.payloadPOJO;
import POJOClasses.collectionIsbn;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class extractingToken {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void authToken() throws JsonProcessingException {

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("userName", "TOOLSQA-Test");
        data.put("password", "Test@@123");

        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(data)
                .log().all();

        response = requestSpecification.post("https://bookstore.toolsqa.com/Account/v1/GenerateToken");

        validatableResponse = response.then()
                .statusCode(200);

        String jString = response.getBody().asString();
        String token = JsonPath.from(jString).get("token");

        System.out.println(token);

        requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .header(token, ContentType.JSON, "Authorization", "Bearer " + token);

        
        collectionIsbn isbn = new collectionIsbn();
        isbn.setIsbn("9781593275846");

        List<collectionIsbn> isbnList = new ArrayList<collectionIsbn>();
        isbnList.add(isbn);

        payloadPOJO payload = new payloadPOJO();
        payload.setUserId("0fd523c4-e61e-4b6a-8abb-64a56d94876f");
        payload.setCollectionOfIsbns(isbnList);
        

        response = requestSpecification.body(payload).post("https://bookstore.toolsqa.com/BookStore/v1/Books");

        System.out.println(response.getStatusCode());

        response.prettyPrint();
    }

}
