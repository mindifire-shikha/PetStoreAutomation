package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//userEndpoints.java
// Created for perform CURD(Create, Update, Read, Delete) requests to the user API

public class UserEndPoints2 {
	
	//Created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("routes");//Load properties file from Resource
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url= getURL().getString("post_url");
		
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_url= getURL().getString("get_url");
		
		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(User payload, String userName )
	{
		String Update_url= getURL().getString("Update_url");
		
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
			
		.when()
			.put(Update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url= getURL().getString("delete_url");
		
		Response response=given()
			.pathParam("username", userName)
			
		.when()
			.delete(delete_url);
		
		return response;
	}
	
}
