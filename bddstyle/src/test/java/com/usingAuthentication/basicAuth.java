package com.usingAuthentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import POJOClasses.payloadPOJO;
import POJOClasses.collectionIsbn;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class basicAuth {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void basicAuthentication() throws JsonProcessingException {

        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        RequestSpecification request = RestAssured.given();

        String credentials = "TOOLSQA-Test:Test@@123";

        byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());

        String encodedCredentialsAsString = new String(encodedCredentials);

        request.header("Authorization", "Basic " + encodedCredentialsAsString);

        collectionIsbn isbn = new collectionIsbn();
        isbn.setIsbn("9781593275846");

        List<collectionIsbn> isbnList = new ArrayList<collectionIsbn>();
        isbnList.add(isbn);

        payloadPOJO payload = new payloadPOJO();
        payload.setUserId("0fd523c4-e61e-4b6a-8abb-64a56d94876f");
        payload.setCollectionOfIsbns(isbnList);

        request.header("Content-Type", "application/json");

        Response response = request.body(payload).post("/BookStore/v1/Books");

        System.out.println("Response Status Code is " + response.getStatusCode());

        response.prettyPrint();
    }

    @Test
    public void basicAuthenticationBDDStyle() {

        String credentials = "TOOLSQA-Test:Test@@123";
		byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
		String encodedCredentialsAsString = new String(encodedCredentials);


        collectionIsbn isbn = new collectionIsbn();
        isbn.setIsbn("9781593275846");

        List<collectionIsbn> isbnList = new ArrayList<collectionIsbn>();
        isbnList.add(isbn);

        payloadPOJO payload = new payloadPOJO();
        payload.setUserId("0fd523c4-e61e-4b6a-8abb-64a56d94876f");
        payload.setCollectionOfIsbns(isbnList);

        given().baseUri("https://bookstore.toolsqa.com")
                .header("Authorization", "Basic "+encodedCredentialsAsString, "Content-type", "application/json")
                .body(payload).
        when().post("/BookStore/v1/Books").
        then().assertThat().statusCode(200).and().body(" ", equalTo(" "))
              .and().header("headerName", "expectedValueMatcher");
        
    }

}
