package com.qa.Utils;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;


public class responseHeader {

    public responseHeader() {
        
    }

    public HashMap<String, String> responseHeader(CloseableHttpResponse responseHeader) {

        Header[] headerArray = responseHeader.getAllHeaders();
        
        HashMap<String,String> headerMap = new HashMap<>();

        for (Header header : headerArray) {

            headerMap.put(header.getName(), header.getValue());
            
        }

        System.out.println(headerMap);
        
        return headerMap;
        
    }
    
}
