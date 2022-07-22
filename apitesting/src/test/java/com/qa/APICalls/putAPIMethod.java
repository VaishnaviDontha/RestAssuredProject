package com.qa.APICalls;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import com.qa.Utils.responseHeader;
import com.qa.Utils.testUtil;
import com.qa.requestPOJO.putUsers;

public class putAPIMethod extends baseClass{

    String url;
    String serviceUrl;
    String actualUrl;

    restClient rClient;
    responseHeader resHeader;

    CloseableHttpResponse closeableHttpResponse;

    @BeforeTest
    public void setUp() {

        
        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurlput"); 

        actualUrl = url + serviceUrl;
        
    }

    @Test
    public void putMethod() throws ClientProtocolException, IOException {

        rClient = new restClient();
        resHeader = new responseHeader();

        HashMap<String,String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        putUsers user = new putUsers("morpheus", "zion resident");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("/Users/harishkasam/Documents/Vaishnavi/Practise-Repo/RestAssured/apitesting/src/main/java/com/qa/data/putDataUsers.json"), user);

        String usersJsonString = mapper.writeValueAsString(user);
        System.out.println(usersJsonString);
        
        closeableHttpResponse = rClient.putCallMethod(actualUrl,headerMap,usersJsonString);
        System.out.println(closeableHttpResponse);

        // Assertions 

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, Response_Status_Code_200);

        /*
         * Converting HTTP Response to String format
         */

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");  

        /*
         * Converting String to JSON
         */
        
        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println(responseJSON);

        /*
         * Response Check
         */
        String name = testUtil.getValueByJPath(responseJSON, "/name");
        Assert.assertEquals(name, "morpheus");
        String job = testUtil.getValueByJPath(responseJSON, "/job");
        Assert.assertEquals(job, "zion resident");

        
        
        /*
         * Converting HTTP Header Array to HashMap and then to JSON
         */
        headerMap = resHeader.responseHeader(closeableHttpResponse);
        JSONObject headerJSON = new JSONObject(headerMap);
        System.out.println(headerJSON);
        
        /*
         * Response Header Check
         */
        String server = testUtil.getValueByJPath(headerJSON, "/Server");
        Assert.assertEquals(server, "cloudflare");

        
    }
    
}
