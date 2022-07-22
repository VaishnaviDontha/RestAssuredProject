package com.qa.Utils;

import java.util.HashMap;
import java.util.Map.Entry;

public class requestHeader {

    public requestHeader() {

    }

    public HashMap<String, String> defaultHeaders() {

        HashMap<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json");
        return defaultHeaders;

    }

    public HashMap<String,String> headersWithToken() {

        HashMap<String,String> headersWithToken = new HashMap<>();
        headersWithToken.put("Content-Type", "application/json");
        defaultHeaders().put("Authorization", "");

        return headersWithToken;
        
    }

    public HashMap<String,String> headers(HashMap<String,String> headerMap){

        for (Entry<String,String> reqHeader : headerMap.entrySet()) {

            // reqHeader.add
            
        }
        return headerMap;
    }

}
