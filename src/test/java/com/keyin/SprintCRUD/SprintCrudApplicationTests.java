package com.keyin.SprintCRUD;

import com.keyin.SprintCRUD.AccessingDataMySQL.Member;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest
class SprintCrudApplicationTests {

	@Test
	void contextLoads() {
	}

	private static final String API_ROOT
			= "http://localhost:8080/member";

	private Member createRandomMember() {
		Member member = new Member();
		member.setName("John O'Neill");
		member.setAddress("createMemberAsUri");
		member.setEmail("john.oneill@keyin.com");
		member.setPhone("7096313944");
		member.setMembershipType("Family");
		member.setMembershipStartDate("2021-11-01");
		member.setMembershipLength(12);
		return member;
	}

	private String createMemberAsUri(Member member) {
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(member)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}

	@Test
	public void whenGetAllMembers_thenOK() {
		Response response = RestAssured.get(API_ROOT + "/all");
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetMemberById_thenOK() {
		Member member = createRandomMember();
		String location = createMemberAsUri(member);
		Response response = RestAssured.get(location);
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetCreatedMemberById_thenCheckValues() {
		Member member = createRandomMember();
		String location = createMemberAsUri(member);
		Response response = RestAssured.get(location);

		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assertions.assertEquals(member.getName(), response.jsonPath().get("name"));
		Assertions.assertEquals(member.getAddress(), response.jsonPath().get("address"));
		Assertions.assertEquals(member.getPhone(), response.jsonPath().get("phone"));
		Assertions.assertEquals(member.getEmail(), response.jsonPath().get("email"));
		Assertions.assertEquals(member.getMembershipLength(), response.jsonPath().get("membershipLength"));
		Assertions.assertEquals(member.getMembershipType(), response.jsonPath().get("membershipType"));
		Assertions.assertEquals(member.getMembershipStartDate(), response.jsonPath().get("membershipStartDate"));
	}

	@Test
	public void whenGetNotExistMemberById_thenNotFound() {
		Response response = RestAssured.get(API_ROOT + Math.floor(Math.random()*(5000-4000+1)+4000));

		Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
}
