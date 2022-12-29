package apiEngine;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static apiEngine.Endpoints.API_PATH;
import static apiEngine.Endpoints.BASE_URL;

public class ReqResSpecifications {

    private final Endpoints endpoints = new Endpoints();

    public RequestSpecification setupRequestSpecification() {
        return RestAssured.given()
                .baseUri(BASE_URL + API_PATH)
                .contentType(ContentType.JSON);
    }

    public ResponseSpecification setupResponseSpecification() {
        return RestAssured.expect()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .time(Matchers.lessThan(5000L));
    }

}
