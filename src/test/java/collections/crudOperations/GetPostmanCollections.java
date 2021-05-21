package collections.crudOperations;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import collections.deserialization.GetCollections;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPostmanCollections {
	
	RequestSpecBuilder rb;
	GetCollections response;
	Response res;
	RequestSpecification req ;
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI="https://api.getpostman.com";
		rb = new RequestSpecBuilder();
		rb.setBasePath("/collections")
	      .setContentType(ContentType.JSON)
		  .addHeader("X-API-Key","PMAK-60a48e03321172003c2cede7-3aaf25b7cd5636ad690685ff612b1e7968");
	}
	
	@Test(priority = 1)
	public void fetchGetCallResponse() {
		req = RestAssured.given(rb.build()).log().all();
		res = req.get();
		res.then().log().all();
		response = res.as(GetCollections.class);
	}
	
	@Test(priority = 2)
	public void getCollectionNames() {
		System.out.println("Collections fetched: "+response.getCollections().size());
		for(int i=0;i<response.getCollections().size();i++) {
			System.out.println(i+1+": "+response.getCollections().get(i).getName());
		}
	}
}
