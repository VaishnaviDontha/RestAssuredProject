package com.qa.APICalls;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import com.qa.requestPOJO.Users;

public class postAPIMethod extends baseClass {

    baseClass testBase;
    String url;
    String serviceUrl;
    String actualUrl;

    restClient rClient;

    CloseableHttpResponse closeableHttpResponse;

    @BeforeTest
    public void setUp() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurlpost");

        actualUrl = url + serviceUrl;

    }

    @Test
    public void postMethod() throws StreamWriteException, DatabindException, IOException {

        rClient = new restClient();

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        // Jackson API
        ObjectMapper mapper = new ObjectMapper();

        Users users = new Users("eve.holt@reqres.in", "pistol");

        // Object to JSon
        mapper.writeValue(new File(
                "/Users/harishkasam/Documents/Vaishnavi/Practise-Repo/RestAssured/apitesting/src/main/java/com/qa/data/Users.json"),
                users);

        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        
        closeableHttpResponse = rClient.postCallMethod(actualUrl, headerMap, usersJsonString);
        System.out.println(closeableHttpResponse);         /* Completion of Execution */

    }

}
