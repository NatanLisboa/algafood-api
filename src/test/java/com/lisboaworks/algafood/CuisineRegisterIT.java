package com.lisboaworks.algafood;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CuisineRegisterIT {
	
	@LocalServerPort
	private int port;
    
	@Test
	public void shouldReturnStatusOK_WhenGettingCuisines() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		given()
			.basePath("/cuisines")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

}
