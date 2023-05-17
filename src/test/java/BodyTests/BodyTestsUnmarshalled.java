package BodyTests;

import org.apache.http.client.methods.HttpGet;
import org.example.BaseClass;
import org.example.utils.ResponseUtils;
import org.example.entities.Message;
import org.testng.annotations.Test;

import java.io.IOException;
import org.example.entities.User;
import static org.testng.Assert.assertEquals;

public class BodyTestsUnmarshalled extends BaseClass {
        @Test
        public void returnsCorrectLoginTest() throws IOException {

            HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/marneo23");

            response = client.execute(get);

            User user = ResponseUtils.unmarshall(response, User.class);

            assertEquals(user.getLogin(), "marneo23");
        }

        @Test
        public void returnsCorrectIdTest() throws IOException {

            HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/marneo23");

            response = client.execute(get);

            User user = ResponseUtils.unmarshall(response, User.class);
            System.out.println(user.getId());
            assertEquals(user.getId(), 81268086); //returned as an int :)
        }

        @Test
        public void returnsNotFoundMessageTest() throws IOException {

            HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");

            response = client.execute(get);

            Message message = ResponseUtils.unmarshall(response, Message.class);
            System.out.println(message.getMessage());
            assertEquals(message.getMessage(), "Not Found");
        }
}
