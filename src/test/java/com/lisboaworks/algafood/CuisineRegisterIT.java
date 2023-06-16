package com.lisboaworks.algafood;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.util.DatabaseCleaner;
import com.lisboaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CuisineRegisterIT {
	
	private static final String CUISINE_REGISTER_JSON_PATH = "/json/chinese-cuisine.json";

	private static final long NONEXISTENT_CUISINE_ID = -1L;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CuisineRepository cuisineRepository;
		
	private int qtyRegisteredCuisines; 
	private String cuisineRegisterBody;
	private Cuisine americanCuisine;

	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();		
		RestAssured.port = port;
		RestAssured.basePath = "/cuisines";
		
		cuisineRegisterBody = ResourceUtils.getContentFromResource(CUISINE_REGISTER_JSON_PATH);
		
		databaseCleaner.clearTables();
		prepareData();
	}
    
	@Test
	public void shouldReturnHttpStatusOK_WhenGettingAllCuisines() {		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnTheTotalAmountOfRegisteredCuisines_WhenGettingAllCuisines() {	
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(qtyRegisteredCuisines));
	}
	
	@Test
	public void shouldReturnHttpStatusCreated_WhenRegisteringCuisine() {
		given()
			.body(cuisineRegisterBody)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void shouldReturnResponseAndStatusCorrectly_WhenGettingExistentCuisine() {
		given()
			.pathParam("cuisineId", americanCuisine.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cuisineId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo(americanCuisine.getName()));
	}
	
	@Test
	public void shouldReturnHttpStatusNotFound_WhenGettingNonexistentCuisine() {
		given()
			.pathParam("cuisineId", NONEXISTENT_CUISINE_ID)
			.accept(ContentType.JSON)
		.when()
			.get("/{cuisineId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepareData() {	
		Cuisine thaiCuisine = new Cuisine();
		thaiCuisine.setName("Thai");
		thaiCuisine = cuisineRepository.save(thaiCuisine);
		
		americanCuisine = new Cuisine();
		americanCuisine.setName("American");
		americanCuisine = cuisineRepository.save(americanCuisine);
		
		qtyRegisteredCuisines = (int) cuisineRepository.count();
	}

}
