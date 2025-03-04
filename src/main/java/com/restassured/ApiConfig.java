package com.restassured;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiConfig {

    public static final String BASE_URI = "https://reqres.in/";
    public static final int TIMEOUT = 5000;

    public static Response makeGetRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when().get(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();
        System.out.println("Request URL: " + BASE_URI + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.asString());
        return response;
    }

    public static Response makePostRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when().post(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();
        System.out.println("Request URL: " + BASE_URI + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.asString());
        return response;
    }

    public static Response makePutRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when().put(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();
        System.out.println("Request URL: " + BASE_URI + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.asString());
        return response;
    }

    public static Response makeDeleteRequest(String endpoint, int expectedStatusCode) {
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .when().delete(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract().response();
        System.out.println("Request URL: " + BASE_URI + endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Body: " + response.asString());
        return response;
    }

}
