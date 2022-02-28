package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ServiceManager {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com/";
    @Getter
    public static Response response;
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private static RequestSpecBuilder requestSpecBuilder;
    private static ResponseSpecBuilder responseSpecBuilder;

    public static void setupServiceManager() {
        RestAssured.baseURI = BASE_URI;
        requestSpecBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();
    }

    public static void beforeEveryRestCall() {
        requestSpecBuilder.setContentType(ContentType.JSON);

        requestSpec = requestSpecBuilder.build();
        responseSpec = responseSpecBuilder.build();

        response = null;
    }

    public static Response getRequest(String endpoint) {
        beforeEveryRestCall();
        response = given().spec(requestSpec).get(endpoint);
        return response;
    }

    public static Response getRequest(String endpoint, Map<String, String> queryParams) {
        beforeEveryRestCall();
        response = given().spec(requestSpec).queryParams(queryParams).get(endpoint);
        return response;
    }

    public static Response postRequest(String endpoint, String payload) {
        beforeEveryRestCall();
        response = given().spec(requestSpec).body(payload).post(endpoint);
        return response;
    }

    public static Response putRequest(String endpoint, String payload) {
        beforeEveryRestCall();
        response = given().spec(requestSpec).body(payload).put(endpoint);
        return response;
    }

    public static Response patchRequest(String endpoint, String payload) {
        beforeEveryRestCall();
        response = given().spec(requestSpec).body(payload).patch(endpoint);
        return response;
    }

    public static Response deleteRequest(String endpoint) {
        beforeEveryRestCall();
        response = given().spec(requestSpec).delete(endpoint);
        return response;
    }

    public static Boolean verifyResponseCode(int status) {
        return getResponse().getStatusCode() == status;
    }
}
