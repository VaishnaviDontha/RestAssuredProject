package com.qa.APICalls;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;

public class deleteAPIMethod extends baseClass {

    String url;
    String serviceUrl;
    String actualUrl;

    restClient rClient;

    CloseableHttpResponse closeableHttpResponse;

    @BeforeTest
    public void setUp() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurldelete");

        actualUrl = url + serviceUrl;

    }

    @Test
    public void deleteAPIMethod() throws ClientProtocolException, IOException {

        rClient = new restClient();

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        closeableHttpResponse = rClient.deleteCallMethod(actualUrl, headerMap);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, Response_Status_Code_204);

    }

}
