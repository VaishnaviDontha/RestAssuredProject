package stepdefs;

import com.qa.RestClient.restClient;
import com.qa.Utils.responseHeader;
import io.cucumber.java.en.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class getAPICallWithParam {

    String url;
    String serviceUrl;
    String actualUrl;

    HashMap<String, String> headers;
    HashMap<String, String> headerMap;

    restClient rClient;
    responseHeader resHeader;
    CloseableHttpResponse closeableHttpResponse;

    @Given("GET api actualUrl is combined with url {string} and serviceurl {string}")
    public void get_api_actual_url_is_combined_with_url_and_serviceurl(String url, String serviceUrl) {

        System.out.println(url);
        System.out.println(serviceUrl);

        actualUrl = url + serviceUrl;
        System.out.println(actualUrl);



    }
    @When("the actualUrl is hit to the server")
    public void the_actual_url_is_hit_to_the_server() throws IOException {

        rClient = new restClient();
        resHeader = new responseHeader();

        HashMap<String,String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        // Passing Headers
        closeableHttpResponse = rClient.getCallMethod(actualUrl, headerMap);

    }
    @Then("the response code of the API must be {int}")
    public void the_response_code_of_the_api_must_be(Integer code) {

        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(StatusCode, code);

    }

}
