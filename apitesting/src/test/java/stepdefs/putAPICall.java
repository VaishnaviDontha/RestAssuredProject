package stepdefs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import com.qa.Utils.responseHeader;
import com.qa.Utils.testUtil;
import com.qa.requestPOJO.Users;

import io.cucumber.java.en.*;

public class putAPICall extends baseClass{

    String url;
    String serviceUrl;
    String actualUrl;

    restClient rClient;
    responseHeader resHeader;
    HashMap<String,String> headerMap;

    CloseableHttpResponse closeableHttpResponse;


    @Given("actual url is defined with url and serviceput url")
    public void actual_url_is_defined_with_url_and_serviceput_url() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurlput"); 

        actualUrl = url + serviceUrl;

    }
    @When("headers and payload are added")
    public void headers_and_payload_are_added() throws StreamWriteException, DatabindException, IOException {

        rClient = new restClient();
        resHeader = new responseHeader();

        headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        Users user = new Users("morpheus", "zion resident");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("/Users/harishkasam/Documents/Vaishnavi/Practise-Repo/RestAssured/apitesting/src/main/java/com/qa/data/putDataUsers.json"), user);

        String usersJsonString = mapper.writeValueAsString(user);
        System.out.println(usersJsonString);
        
        closeableHttpResponse = rClient.putCallMethod(actualUrl,headerMap,usersJsonString);
        System.out.println(closeableHttpResponse);

    }
    @Then("api response should return {int}")
    public void api_response_should_return(Integer int1) throws ParseException, IOException {

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
        String name = testUtil.getValueByJPath(responseJSON, "/email");
        Assert.assertEquals(name, "morpheus");
        String job = testUtil.getValueByJPath(responseJSON, "/password");
        Assert.assertEquals(job, "zion resident");

        
        
        /*
         * Converting HTTP Header Array to HashMap and then to JSON
         */
        headerMap = resHeader.responseHeader(closeableHttpResponse);
        JSONObject headerJSON = new JSONObject(headerMap);
        System.out.println(headerJSON);

    }

}
