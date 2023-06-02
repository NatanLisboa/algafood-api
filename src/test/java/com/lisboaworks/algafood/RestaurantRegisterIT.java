package com.lisboaworks.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import com.lisboaworks.algafood.util.DatabaseCleaner;
import com.lisboaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantRegisterIT {
	
	private static final BigDecimal THAI_GOURMET_SHIPPING_FEE = new BigDecimal(10);

	private static final BigDecimal INDIAN_FUSION_SHIPPING_FEE = new BigDecimal(7);

	private static final String RESTAURANT_REGISTER_JSON_PATH = "/json/new-indian-restaurant-ok.json";

	private static final long NONEXISTENT_RESTAURANT_ID = -1L;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private CuisineRepository cuisineRepository;
	
	private Restaurant thaiGourmet;
	private int qtyRegisteredRestaurants;
	private String restaurantRegisterBodyOk;
	
	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();		
		RestAssured.port = port;
		RestAssured.basePath = "/restaurants";
		
		restaurantRegisterBodyOk = ResourceUtils.getContentFromResource(RESTAURANT_REGISTER_JSON_PATH);
		
		databaseCleaner.clearTables();
		prepareData();
	}
	
	@Test
	public void shouldReturnHttpStatusOK_WhenGettingAllRestaurants() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnTheTotalAmountOfRegisteredRestaurants_WhenGettingAllRestaurants() {	
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(qtyRegisteredRestaurants));
	}
	
	@Test
	public void shouldReturnHttpStatusCreated_WhenRegisteringRestaurantWithoutRequestBodyErrors() {
		given()
			.body(restaurantRegisterBodyOk)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void shouldReturnResponseAndStatusCorrectly_WhenGettingExistentRestaurant() {
		given()
			.pathParam("restaurantId", thaiGourmet.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{restaurantId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo(thaiGourmet.getName()));
	}
	
	@Test
	public void shouldReturnHttpStatusNotFound_WhenGettingNonexistentRestaurant() {
		given()
			.pathParam("restaurantId", NONEXISTENT_RESTAURANT_ID)
			.accept(ContentType.JSON)
		.when()
			.get("/{restaurantId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepareData() {
		Cuisine thaiCuisine = new Cuisine();
		thaiCuisine.setName("Thai");
		thaiCuisine = cuisineRepository.save(thaiCuisine);
		
		Cuisine indianCuisine = new Cuisine();
		indianCuisine.setName("Indian");
		indianCuisine = cuisineRepository.save(indianCuisine);
		
		thaiGourmet = new Restaurant();
		thaiGourmet.setName("Thai Gourmet");
		thaiGourmet.setShippingFee(THAI_GOURMET_SHIPPING_FEE);
		thaiGourmet.setCuisine(thaiCuisine);
		thaiGourmet = restaurantRepository.save(thaiGourmet);
		
		Restaurant indianFusion = new Restaurant();
		indianFusion.setName("Indian Fusion Cuisine");
		indianFusion.setShippingFee(INDIAN_FUSION_SHIPPING_FEE);
		indianFusion.setCuisine(indianCuisine);
		indianFusion = restaurantRepository.save(indianFusion);
		
		qtyRegisteredRestaurants = (int) restaurantRepository.count();
	}
	
}
