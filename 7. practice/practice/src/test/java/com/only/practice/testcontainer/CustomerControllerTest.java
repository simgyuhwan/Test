package com.only.practice.testcontainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;

/**
 * Created by Gyuhwan
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

  @Autowired
  private CustomerRepository customerRepository;

  @LocalServerPort
  private Integer port;

  static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8");

  @BeforeAll
  static void beforeAll() {
    mySQLContainer.start();
  }

  @AfterAll
  static void afterAll() {
    mySQLContainer.stop();
  }

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
    customerRepository.deleteAll();
  }

  @Test
  void shouldGetAllCustomers() {
    List<Customer> customers = List.of(new Customer(null, "사용자1", "user1@naver.com"),
        new Customer(null, "사용자2", "user2@naver.com"));

    customerRepository.saveAll(customers);

    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/customers")
        .then()
        .statusCode(200)
        .body(".", hasSize(2));
  }
}