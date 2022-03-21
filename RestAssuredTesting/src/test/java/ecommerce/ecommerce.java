package ecommerce;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ecommerce {
	
	
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accessToken;
	
	public static int count;
	public static String email;
	public static String users;
	public static String id;
	
	
	
	
	@Test(priority = 0 ,enabled = false)
	public void signup()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"kedharn@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/signup")
			
			.then()
			.assertThat().statusCode(201).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	message = js.get("message");
	System.out.println(message);
	
	
}
	
	
	@Test(priority = 1)
	public void Login()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"kedharn@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/login")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	accessToken = js.get("accessToken");
	System.out.println(accessToken);
	}
	
	
	
	@Test(priority = 2)
	public void getUser()
	{
		RestAssured.baseURI =baseurl;
		
	/*
	 * String 	requestbody = "{\n"
	 
			+ "	\"email\": \"shesha80@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	*/
	Response resposne = given()
			.header("Content-Type","application/json")
			.header("Authorization","bearer "+accessToken)
			//.body(requ)
			
			.when()
			.get("/user")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();

	System.out.println(jsonresponse);
	
	
	JsonPath jsonData = new JsonPath(jsonresponse);


	count = jsonData.get("count");
	System.out.println(count);

	
	users = jsonData.get("users").toString();
	System.out.println(users);
	String array[]=null;
	array=users.split("}");

	
	for(int i=0;i<5;i++)
	{
		email = array[i].toString();
		System.out.println(email);

		if(email.contains("jahnavib66@gmail.com")==true)
		{
			//id = ema.get("_id");
			email =email.replace(", {", "{");
			System.out.println("email = "+email);
			
			id = email.substring(5, 29);
		      System.out.println("id = " + id);
	 
	    }
	}
	
	}	
	
	
	
	
	@Test(priority = 3,enabled = false)
	public void delete()
	{
		RestAssured.baseURI =baseurl;
		
			Response resp = given()
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer "+accessToken)
			.when()
			.delete("/user/"+id)
			
			.then()
			.contentType(ContentType.JSON)
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resp.asString();
	JsonPath js = new JsonPath(jsonresponse);
	message = js.get("message");
	System.out.println(message);
  }

	@Test(priority = 4)
	public void createP()
	{
		RestAssured.baseURI =baseurl;
		
		String 	requestbody = "{\r\n"
				+ "	\"name\": \"pizzariya\",\r\n"
				+ "	\"price\": 800\r\n"
				+ "}";
		
			Response resposne = given()
					.header("Content-Type", "application/json")
					.header("Authorization", "Bearer "+accessToken)
					.body(requestbody)
					
			.when()
			.post("/products")
			
			.then()
			.contentType(ContentType.JSON)
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
			String jsonresponse = resposne.asString();
			//i want to convert the response in to json format
			//why do i use jsonpath to convert the string in to a json format
			JsonPath js = new JsonPath(jsonresponse);
			//nw i have to fetch the id
			message = js.get("message");
			System.out.println(message);
  }
	
}
