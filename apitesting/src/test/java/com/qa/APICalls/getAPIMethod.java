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
import com.qa.Utils.requestHeader;
import com.qa.Utils.responseHeader;
import com.qa.Utils.testUtil;

public class getAPIMethod extends baseClass {

    baseClass testBase;
    String url;
    String serviceUrl;
    String actualUrl;

    HashMap<String, String> headerMap;

    restClient rClient;
    requestHeader reqHeader;
    responseHeader resHeader;

    CloseableHttpResponse closeableHttpResponse;

    @BeforeTest
    public void setUp() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurlget");

        actualUrl = url + serviceUrl;
        // System.out.println(actualUrl);

    }

    @Test
    public void getMethod() throws ClientProtocolException, IOException {

        rClient = new restClient();
        resHeader = new responseHeader();

        // Passing Headers
        closeableHttpResponse = rClient.getCallMethod(actualUrl, headerMap);
        
        
        /*
         * Status Code Check
         */
        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(StatusCode, Response_Status_Code_200);

        /*
         * Converting HTTP Response to String format
         */

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");  

        /*
         * Converting String to JSON
         */
        
        JSONObject responseJSON = new JSONObject(responseString);

        /*
         * Response Check
         */
        String firstName = testUtil.getValueByJPath(responseJSON, "/data/name");
        Assert.assertEquals(firstName, "fuchsia rose");

        
        /*
         * Converting HTTP Header Array to HashMap and then to JSON
         */
        headerMap = resHeader.responseHeader(closeableHttpResponse);
        JSONObject headerJSON = new JSONObject(responseJSON);
        
        /*
         * Response Header Check
         */
        String server = testUtil.getValueByJPath(headerJSON, "/Server");
        Assert.assertEquals(server, "cloudflare");

    }

}
