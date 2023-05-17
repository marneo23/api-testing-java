package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseClass {

        protected static final String BASE_ENDPOINT = "https://api.github.com";
        protected CloseableHttpClient client; //client object will act as curl or navigator
        protected CloseableHttpResponse response;

        @BeforeMethod
        public void setup(){
                client  = HttpClientBuilder.create().build();
        }

        @AfterMethod
        public void closeResources() throws IOException {
                client.close();
                response.close();
        }

}
