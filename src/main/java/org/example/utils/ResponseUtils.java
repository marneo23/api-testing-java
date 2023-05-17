package org.example.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.example.entities.User;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) { 

        //turn headers array into list, loop and store any match
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";

        for (Header header : httpHeaders){
            if (headerName.equalsIgnoreCase(header.getName())){
                returnHeader = header.getValue();
            }
        }

        if(returnHeader.isEmpty()){
            throw new RuntimeException("Header not found: " + headerName);
        }

        return returnHeader;

    }


    public static Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    public static <T> T unmarshall(CloseableHttpResponse response, Class<T> clazz) throws IOException { //passing "T" as a type parameter so to unmarshall anything

        String jsonBody = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) //if this property is not present, I have to map the entire response, as it will fail upon meeting an undefined property
                .readValue(jsonBody, clazz);
    }
}


