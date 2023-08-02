package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*********************Creating User********************************");
		 Response response = UserEndPoints.createUser(userPayload);
		 response.then().log().all();
		 
		 Assert.assertEquals(response.getStatusCode(), 200);
		 logger.info("********************* User Created********************************");
		
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("*********************Reading User Info********************************");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********************User Info is Displayed********************************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("*********************Updating User********************************");
		//Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		 Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername() );
		 response.then().log().body();
		 
		 Assert.assertEquals(response.getStatusCode(), 200);	 
		 
		//Checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		//responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("*********************User Info is Updated********************************");
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("*********************Deleting User********************************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		//response.then().log().body().statusCode(200); //Comes along with restassure builtin (Chai) assertion
		
		Assert.assertEquals(response.getStatusCode(), 200); // TestNG Assertion
		logger.info("*********************User id Deleted********************************");
		
	}
}
