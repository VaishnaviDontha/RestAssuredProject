package com.Logging;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class logging {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    File jsonData = new File("src/test/resources/payload.json");

    @Test
    public void postCall() {

        requestSpecification = RestAssured.given()
                /**
                 * ------------------
                 * Request Logging
                 * ------------------
                 * .params()
                 * .body()
                 * .headers()
                 * .cookies()
                 * .method()
                 * .all()
                 * ------------------
                 */
                .log().params()
                .log().body()
                .log().headers()
                .log().cookies()
                .log().method()
                .contentType(ContentType.JSON)
                .body(jsonData);
                

        response = requestSpecification.post("https://reqres.in/api/login");

        validatableResponse = response.then()
                /**
                 * ----------------
                 * Response Logging
                 * ----------------
                 * .all()
                 * ----------------
                 */
                .log().all()
                /**
                 * --------------------
                 * Condiitonal Logging
                 * --------------------
                 * .ifError()
                 * .ifStatusCodeIsEqualTo()
                 * .ifValidationFails()
                 * --------------------
                 */
                .log().ifError()
                .log().ifStatusCodeIsEqualTo(200)
                .log().ifValidationFails()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void postCallBDDStyle() throws FileNotFoundException {

        PrintStream logPrintStream = new PrintStream(new FileOutputStream("logFileViaPrintStream.txt"));
        // 

        given().baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .filter(RequestLoggingFilter.logRequestTo(logPrintStream))
                .filter(ResponseLoggingFilter.logResponseTo(logPrintStream))

                .when()
                .post("/api/login")

                .then()
                .statusCode(200)
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));

    }

    
}
