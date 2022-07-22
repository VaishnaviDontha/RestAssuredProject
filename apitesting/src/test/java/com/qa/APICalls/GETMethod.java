package com.qa.APICalls;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import com.qa.Utils.responseHeader;
import com.qa.Utils.testUtil;

public class GETMethod extends baseClass {

    baseClass testBase;
    String url;
    String serviceUrl;
    String actualUrl;

    HashMap<String, String> headers;

    HashMap<String, String> headerMap;

    restClient rClient;
    responseHeader resHeader;

    CloseableHttpResponse closeableHttpResponse;

    @BeforeTest
    public void setUp() {

        testBase = new baseClass();
        url = prop.getProperty("url");

        serviceUrl = prop.getProperty("serviceurlget");

        actualUrl = url + serviceUrl;

    }

    @Test
    public void getApiCall() throws ClientProtocolException, IOException {

        rClient = new restClient();
        resHeader = new responseHeader();

        closeableHttpResponse = rClient.getCallMethod(actualUrl, headers);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, Response_Status_Code_200);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);

        // Header[] headerArray = closeableHttpResponse.getAllHeaders();

        // HashMap<String,String> headerMap = new HashMap<>();

        // for (Header header : headerArray) {

        // headerMap.put(header.getName(), header.getValue());

        // }

        headerMap = resHeader.responseHeader(closeableHttpResponse);

        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println(responseJSON);

        String firstName = testUtil.getValueByJPath(responseJSON, "/data/first_name");
        Assert.assertEquals(firstName, "Janet");

        /*
         * Header Assertions
         */

        JSONObject headerJSON = new JSONObject(responseJSON);
        String server = testUtil.getValueByJPath(headerJSON, "/Server");
        Assert.assertEquals(server, "cloudflare");

    }

}
