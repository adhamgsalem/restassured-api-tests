package com.restassured;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



public class ApiConfig {

    public static final String BASE_URI = "https://reqres.in/";
    public static final int TIMEOUT = 5000;

    public static Response makeGetRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .when().get(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();
        System.out.println("Response: " + response.asString());
        return response;
    }

    public static Response makePostRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .when().post(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();

        System.out.println("Response: " + response.asString());
        return response;
    }

    public static Response makePutRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .when().put(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();

        System.out.println("Response: " + response.asString());
        return response;
    }

    public static Response makeDeleteRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .when().delete(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();

        System.out.println("Response: " + response.asString());
        return response;
    }

}
