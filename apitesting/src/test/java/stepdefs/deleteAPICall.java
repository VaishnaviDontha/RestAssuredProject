package stepdefs;

import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import io.cucumber.java.en.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class deleteAPICall extends baseClass {

    String url;
    String serviceUrl;
    String actualUrl;

    restClient rClient;

    CloseableHttpResponse closeableHttpResponse;

    @Given("actual url is defined with url and servicedelete url")
    public void actual_url_is_defined_with_url_and_servicedelete_url() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurldelete");

        actualUrl = url + serviceUrl;


    }
    @When("headers and actualurl is hit")
    public void headers_and_actualurl_is_hit() throws IOException {

        rClient = new restClient();

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        closeableHttpResponse = rClient.deleteCallMethod(actualUrl, headerMap);

    }
    @Then("api should retturn status code as {int}")
    public void api_should_retturn_status_code_as(Integer code) {

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, Response_Status_Code_204);

    }
    
}
