package stepdefs;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.qa.Base.baseClass;
import com.qa.RestClient.restClient;
import com.qa.Utils.responseHeader;
import com.qa.Utils.testUtil;

import io.cucumber.java.en.*;

public class getAPICall extends baseClass{

    String url;
    String serviceUrl;
    String actualUrl;

    HashMap<String, String> headers;
    HashMap<String, String> headerMap;

    restClient rClient;
    responseHeader resHeader;
    CloseableHttpResponse closeableHttpResponse;

    @Given("url is given")
    public void url_is_given() {

        url = prop.getProperty("url");
        serviceUrl = prop.getProperty("serviceurlget");
       
    }

    @When("serviceget url is added")
    public void serviceget_url_is_added() {

        actualUrl = url + serviceUrl;
        
    }

    @Then("status code must be {int}")
    public void status_code_must_be(Integer code)  throws ClientProtocolException, IOException {

        rClient = new restClient();
        resHeader = new responseHeader();

        code = Response_Status_Code_200;

        HashMap<String,String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        // Passing Headers
        closeableHttpResponse = rClient.getCallMethod(actualUrl, headerMap);


        /*
         * Status Code Check
         */
        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(StatusCode, code);

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
        String firstName = testUtil.getValueByJPath(responseJSON, "/data/first_name");
        Assert.assertEquals(firstName, "Janet");


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
        System.out.println(server);
        Assert.assertEquals(server, "cloudflare");





    }

}
