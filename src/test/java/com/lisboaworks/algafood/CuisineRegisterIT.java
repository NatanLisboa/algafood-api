package com.lisboaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.lisboaworks.algafood.util.DatabaseCleaner;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CuisineRegisterIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CuisineRepository cuisineRepository;
		
	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();		
		RestAssured.port = port;
		RestAssured.basePath = "/cuisines";
		
		databaseCleaner.clearTables();
		prepareData();
	}
    
	@Test
	public void shouldReturnStatusOK_WhenGettingCuisines() {		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldContainTwoCuisines_WhenGettingCuisines() {		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(2));
	}
	
	@Test
	public void shouldReturnStatusCreated_WhenRegisteringCuisine() {
		given()
			.body("{\"name\": \"Chinese\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	private void prepareData() {
		Cuisine cuisine1 = new Cuisine();
		cuisine1.setName("Thai");
		cuisineRepository.save(cuisine1);
		
		Cuisine cuisine2 = new Cuisine();
		cuisine2.setName("American");
		cuisineRepository.save(cuisine2);
	}

}
