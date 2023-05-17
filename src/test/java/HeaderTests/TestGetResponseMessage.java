package HeaderTests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.BaseClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class TestGetResponseMessage extends BaseClass {

    @BeforeMethod
    public void setUp(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void tearDown() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void NonExistingUrlReturns404() throws IOException {
        HttpGet get = new HttpGet(BaseClass.BASE_ENDPOINT + "/asdadsdas");
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 404);
    }

}
