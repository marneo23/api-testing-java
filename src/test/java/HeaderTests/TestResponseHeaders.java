package HeaderTests;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.BaseClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.example.ResponseUtils.getHeader;
import static org.testng.Assert.assertEquals;

public class TestResponseHeaders extends BaseClass {

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
    public void GetResponseHeaders() throws IOException {
        HttpGet get = new HttpGet(BaseClass.BASE_ENDPOINT);
        response = client.execute(get);

        //getContentType() will get content type & charset
        Header contentType = response.getEntity().getContentType();
        System.out.println(contentType);
        assertEquals(contentType.getValue(), "application/json; charset=utf-8");

        //to get only content type, put it into another method:
        ContentType ct = ContentType.getOrDefault(response.getEntity());
        System.out.println(ct.getMimeType());
        assertEquals(ct.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGithub() throws IOException {
        HttpGet get = new HttpGet(BaseClass.BASE_ENDPOINT);
        response = client.execute(get);

        String headerValue = getHeader(response, "Server");
        System.out.println(headerValue);
        assertEquals(headerValue, "GitHub.com");
    }


}
