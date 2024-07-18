/**
 * 
 */
package com.home;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.home.controller.UserController;
import com.home.controller.UserManager;
import com.home.model.Passport;
import com.home.model.User;
import com.home.model.Zugangsdaten;
import com.home.repository.UserService;
import com.home.service.GeneralService;
import com.home.service.impl.UserServiceImpl;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.ws.rs.core.MediaType;
import java.net.URL;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.*;

/**
 * 
 * @author Ahmad Alrefai
 */
@ExtendWith(ArquillianExtension.class)
public class UserControllerTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.home.repository")
				.addClasses(UserController.class, UserManager.class, UserService.class, UserServiceImpl.class,
						User.class, Zugangsdaten.class, GeneralService.class, Passport.class)
				.addAsResource("META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(
						Maven.resolver().loadPomFromFile("pom.xml")
								.resolve("org.hamcrest:hamcrest", "io.rest-assured:rest-assured",
										"org.mockito:mockito-core", "com.fasterxml.jackson.core:jackson-databind")
								.withTransitivity().asFile());
	}

	@ArquillianResource
	private URL baseURL;

	@BeforeEach
	public void setup() {
//		RestAssured.baseURI = baseURL.toString() + "api";
		RestAssured.baseURI = "http://localhost:8080/ahmad_arquillian" + "/api";
		System.out.println("Base URI: " + RestAssured.baseURI);

		JsonbConfig config = new JsonbConfig().withDateFormat("yyyy-MM-dd", null);
		Jsonb jsonb = JsonbBuilder.create(config);

		RestAssured.config = RestAssured.config()
				.objectMapperConfig(new ObjectMapperConfig().defaultObjectMapper(new ObjectMapper() {
					@Override
					public Object deserialize(ObjectMapperDeserializationContext context) {
						return jsonb.fromJson(context.getDataToDeserialize().asString(), context.getType());
					}

					@Override
					public Object serialize(ObjectMapperSerializationContext context) {
						return jsonb.toJson(context.getObjectToSerialize());
					}
				}));
	}

//	@Test
//	public void testAddUser() {
//	    String passportNumber = "3567654356765";
//	    LocalDate issueDate = LocalDate.now();
//	    LocalDate expiryDate = LocalDate.now().plusYears(10);
//	    Passport passport = new Passport(passportNumber, issueDate, expiryDate, "DE");
//	    User user = new User("Max1", "Mustermann1", 44, passport);
//
//	    Response response = given()
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .body(user)
//	            .log().all()
//	        .when()
//	            .post("/users")  
//	        .then()
//	            .log().all()
//	            .extract().response();
//
//	    System.out.println("Response status code: " + response.getStatusCode());
//	    System.out.println("Response body: " + response.getBody().asString());
//
//	    response.then()
//	        .statusCode(201)
//	        .body("id", notNullValue())
//	        .body("firstName", equalTo("Max1"))
//	        .body("lastName", equalTo("Mustermann1"))
//	        .body("age", equalTo(44))
//	        .body("passport.passportNo", equalTo(passportNumber))
//	        .body("passport.issueDate", equalTo(issueDate.toString()))
//	        .body("passport.expiryDate", equalTo(expiryDate.toString()))
//	        .body("passport.countryOfIssue", equalTo("DE"));
//	}

	@Test
	public void testGetUser() {
	    Response response = given()
	        .pathParam("id", 2)
	        .log().all()
	    .when()
	        .get("/users/{id}")
	    .then()
	        .log().all()
	        .extract().response();

	    System.out.println("Response Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());

	    response.then().statusCode(200);
	}

	@Test
	public void testUpdateUser() {
	    User updatedUser = new User("Maxi", "Musterfrau", 44);

	    given()
	        .contentType(ContentType.JSON)
	        .body(updatedUser)
	        .pathParam("id", 4)
	        .log().all()
	    .when()
	        .put("/users/{id}")
	    .then()
	        .log().all()
	        .statusCode(200)
	        .body("firstName", equalTo("Maxi"))
	        .body("lastName", equalTo("Musterfrau"))
	        .body("age", equalTo(44));
	}
	
//	@Test
//	public void testDeleteUser() {
//	    given()
//	        .pathParam("id", 115)
//	        .log().all()
//	    .when()
//	        .delete("/users/{id}")
//	    .then()
//	        .log().all()
//	        .statusCode(204);
//	}

	@Test
	public void testGetAllUsers() {
	    Response response = given()
	        .log().all()
	    .when()
	        .get("/users/all")
	    .then()
	        .log().all()
	        .extract().response();

	    System.out.println("Response Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());

	    if (response.getStatusCode() != 200) {
	        System.out.println("Unexpected status code. Server logs might provide more information.");
	        System.out.println("Headers: " + response.getHeaders());
	        System.out.println("Cookies: " + response.getCookies());
	    }

	    Assert.assertEquals("Unexpected status code", 200, response.getStatusCode());
	    Assert.assertNotNull("Response body should not be null", response.getBody());
	    Assert.assertTrue("Response body should contain users", response.getBody().asString().contains("users"));
	}
	
	@Test
	public void testSimpleEndpoint() {
	    String fullUrl = RestAssured.baseURI + "/users/test";
	    System.out.println("Full URL being tested: " + fullUrl);
	    
	    given()
	        .log().all()
	    .when()
	        .get("/users/test")
	    .then()
	        .log().all()
	        .statusCode(200)
	        .body(equalTo("Test successful"));
	}
}
