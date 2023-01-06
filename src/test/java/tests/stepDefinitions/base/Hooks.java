package tests.stepDefinitions.base;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.providers.PropertiesFactory;
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger("Hooks.class");
    static PropertiesFactory propertiesFactory;

    @BeforeAll
    public static void setupAll(){
        propertiesFactory = PropertiesFactory.getInstance();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        logger.info("<------------------@BeforeAll setup has been set------------------>");
    }
}
