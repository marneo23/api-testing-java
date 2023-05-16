package BodyTests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.example.BaseClass;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.ResponseUtils;

import java.io.IOException;
import static org.example.ResponseUtils.getValueFor;
public class BodyTestMapping extends BaseClass{


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
    public void returnCorrectLogin() throws IOException{

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/marneo23");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        System.out.println(jsonBody); // it's possible to read with a string reader but using JSONObject makes it more maintainable

        JSONObject jsonObject = new JSONObject(jsonBody);

        String loginKeyValue = (String) getValueFor(jsonObject, "login");
        System.out.println(loginKeyValue);

        Assert.assertEquals(loginKeyValue, "marneo23");
    }

    @Test
    public void returnCorrectId() throws IOException{

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/marneo23");
        response = client.execute(get);

        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);

        int idKeyValue = (int) getValueFor(jsonObject, "id");
        System.out.println(idKeyValue);

        Assert.assertEquals(idKeyValue, Integer.valueOf("81268086"));
    }



}
