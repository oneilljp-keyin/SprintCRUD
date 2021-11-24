package com.keyin.SprintCRUD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

@SpringBootTest
class SprintCrudApplicationTests {

	@Test
	void contextLoads() {
	}

	private static final String API_ROOT
			= "http://localhost:8080/";

//	private Bio createRandomBio() {
//		Bio superstar = new Bio();
//		superstar.setReal_name("John O'Neill");
//		superstar.setHeight_cm(185.0);
//		superstar.setWeight_kg(110.0);
//		superstar.setDob("1980-04-25");
//		superstar.setDod("0000-00-00");
//		superstar.setStatus("Over There");
//		return superstar;
//	}
//
//	private String createBioAsUri(Bio superstar) {
//		Response response = RestAssured.given()
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.body(superstar)
//				.post(API_ROOT);
//		return API_ROOT + "/" + response.jsonPath().get("id");
//	}
//
//	@Test
//	public void whenGetAllBios_thenOK() {
//		Response response = RestAssured.get(API_ROOT + "/all");
//		System.out.println(HttpStatus.OK.value() + " - " + response.getStatusCode());
//		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//	}
//
//	@Test
//	public void whenGetBiosById_thenOK() {
//		Bio superstar2 = createRandomBio();
//		createBioAsUri(superstar2);
//		Response response = RestAssured.get(
//				API_ROOT + "?id=" + superstar2.getId());
//		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//		Assertions.assertTrue(response.as(List.class).size() > 0);
//	}
//
//	@Test
//	public void whenGetCreatedBioById_thenOK() {
//		Bio superstar = createRandomBio();
//		String location = createBioAsUri(superstar);
//		Response response = RestAssured.get(location);
//
//		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//		Assertions.assertEquals(superstar.getId(), response.jsonPath()
//				.get("id"));
//	}
//
//	@Test
//	public void whenGetNotExistBioById_thenNotFound() {
//		Response response = RestAssured.get(API_ROOT + "?id=" + Math.floor(Math.random()*(5000-4000+1)+4000));
//
//		Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
//	}
}
