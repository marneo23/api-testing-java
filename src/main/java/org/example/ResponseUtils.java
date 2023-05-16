package org.example;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) { 

        //get ALL headers as an array
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";

        //loop over them, store any match
        for (Header header : httpHeaders){
            if (headerName.equalsIgnoreCase(header.getName())){
                returnHeader = header.getValue();
            }
        }

        //exception if no header found
        if(returnHeader.isEmpty()){
            throw new RuntimeException("Header not found: " + headerName);
        }

        return returnHeader;

    }


    public static Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }



}
