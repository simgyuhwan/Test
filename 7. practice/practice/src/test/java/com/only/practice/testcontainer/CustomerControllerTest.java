package com.only.practice.testcontainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Created by Gyuhwan
 */
@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

  @Autowired
  private CustomerRepository customerRepository;

  @LocalServerPort
  private Integer port;

  @Container
  static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8");

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
    customerRepository.deleteAll();
  }

  @Test
  void shouldGetAllCustomers() {
    List<Customer> customers = List.of(new Customer("사용자1", "user1@naver.com"),
        new Customer("사용자2", "user2@naver.com"));

    customerRepository.saveAll(customers);

    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/customers")
        .then()
        .log().all()
        .statusCode(200)
        .body(".", hasSize(2))
        .body("name[0]", equalTo("사용자1"))
        .body("email[0]", equalTo("user1@naver.com"))
        .body("name[1]", equalTo("사용자2"))
        .body("email[1]", equalTo("user2@naver.com"));
  }
}