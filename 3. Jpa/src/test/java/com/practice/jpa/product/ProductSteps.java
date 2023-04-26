// package com.practice.jpa.product;
//
// import io.restassured.RestAssured;
// import io.restassured.response.ExtractableResponse;
// import io.restassured.response.Response;
// import org.springframework.http.MediaType;
//
// public class ProductSteps {
//     public ProductSteps() {
//     }
//
//     public static ExtractableResponse<Response> 상품등록요청(AddProductRequest request) {
//         return RestAssured.given().log().all()
//                 .contentType(MediaType.APPLICATION_JSON_VALUE)
//                 .body(request)
//                 .when()
//                 .post("/products")
//                 .then()
//                 .log().all().extract();
//     }
//
//    public static AddProductRequest 상품등록요청_생성() {
//         final int price = 1000;
//         final String name = "상품명";
//         return new AddProductRequest(name, price, DiscountPolicy.NONE);
//     }
//
//     public ExtractableResponse<Response> 상품조회요청(Long productId) {
//         return RestAssured.given().log().all()
//                 .when()
//                 .get("/products/{productId}", productId)
//                 .then().log().all()
//                 .extract();
//     }
// }