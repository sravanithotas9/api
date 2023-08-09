package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload = new User ();
        userPayload.setId (faker.idNumber ().hashCode ());
        userPayload.setUsername (faker. name ().username ());
        userPayload.setFirstname (faker. name ().firstName ());
        userPayload.setLastname (faker. name ().lastName ());
        userPayload.setEmail (faker. internet ().safeEmailAddress ());
        userPayload.setPassword (faker. internet ().password (5,10));
        userPayload.setPhone (faker. phoneNumber ().cellPhone());
    }


    @Test(priority = 1)
    public void testPostUser(){
        Response response = UserEndPoints.createUser ( userPayload );
        response.then ().log ().all ();
        Assert.assertEquals (response.getStatusCode (),  200);

    }

    @Test(priority = 1)
    public void testGetUser(){
        Response response = UserEndPoints.readUser ( "theUser" );
        response.then ().log ().all ();
        Assert.assertEquals (response  .getStatusCode (),  200);

    }
}
