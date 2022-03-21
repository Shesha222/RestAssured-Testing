

package RestTesting_Trello;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.sql.Timestamp;





public class Trello {
	
	public static String baseurl = "https://api.trello.com";
	public static String bid;
	
	@Test (priority = 0)
	public void createBoard() {
		RestAssured.baseURI = baseurl;
		//given()
		Response response =given()
		.queryParam("name","timestamp 3")
		.queryParam("key","12c5a9a451d7933174bd95bdc2588dd0")
		.queryParam("token","17ada0bc1ccbd702fb1104dcbe06b94498239075818d4094e304d4aa0299b10f")
		.header("Content-Type","application/json")
		//Response response = baseurl.given("/1/boards/");
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		//Response response = baseurl.given("/1/boards/");
		//for response
		String jsoneresponse =response.asString();
		JsonPath js = new JsonPath(jsoneresponse);
		System.out.println(jsoneresponse);
		System.out.println("we are in create");
		bid =js.get("id");
		System.out.println(bid);
		

	}

	
	
	@Test (priority = 1)
	public void getBoard() {
		RestAssured.baseURI = baseurl;
		//given()
		Response response =given()
		.queryParam("key","12c5a9a451d7933174bd95bdc2588dd0")
		.queryParam("token","17ada0bc1ccbd702fb1104dcbe06b94498239075818d4094e304d4aa0299b10f")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+bid)
		
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsoneresponse =response.asString();
		System.out.println("we are in get");
		System.out.println(jsoneresponse);
	}
	

	
	@Test(priority = 2)
	public void DeleteBoard() throws InterruptedException {
		/*
		// Here we are creating 2 timestamp objects
		Timestamp tm = new Timestamp(800000);
		// Displaying the created timestamp object
		System.out.println("The Timestamp time is : "
		+ tm.toString());
		System.out.println("The Time in milliseconds is : "
		+ tm.getTime());*/
		
		//Thread.sleep(800);
		
		RestAssured.baseURI = baseurl;
		//given()
		Response response =given()
		.queryParam("key","12c5a9a451d7933174bd95bdc2588dd0")
		.queryParam("token","17ada0bc1ccbd702fb1104dcbe06b94498239075818d4094e304d4aa0299b10f")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+bid)
		
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		String jsoneresponse =response.asString();
		System.out.println("we are in delete");
		System.out.println(jsoneresponse);
	
	}
	
	
}
