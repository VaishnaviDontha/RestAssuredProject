package stepdefs;

import io.cucumber.java.en.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import com.qa.requestPOJO.Users;

public class postAPICall extends baseClass{

    baseClass testBase;
    String url;
    String serviceUrl;
    String actualUrl;

    int statuscode;

    restClient rClient;

    CloseableHttpResponse closeableHttpResponse;

    @Given("actual url is defined with url and servicepost url")
    public void actual_url_is_defined_with_url_and_servicepost_url() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurlpost");

        actualUrl = url + serviceUrl;

    }
    @When("headers and payload is added")
    public void headers_and_payload_is_added() throws StreamWriteException, DatabindException, IOException {

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
    @Then("api response must return {int}")
    public void api_response_must_return(Integer statuscode) {

        statuscode = Response_Status_Code_201;

        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(StatusCode, statuscode);


    }

}
