package tests.base;

import apiEngine.Endpoints;
import apiEngine.ReqResSpecifications;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import providers.PropertiesFactory;

public class TestBase {

    private static PropertiesFactory propertiesFactory;
    protected Endpoints endpoints = new Endpoints();
    protected ReqResSpecifications specifications = new ReqResSpecifications();

    @BeforeAll
    public static void setup() {
        propertiesFactory = PropertiesFactory.getInstance();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
