package tests.stepDefinitions;

import apiEngine.ReqResSpecifications;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.StudentResponse;
import models.bodyRequestModels.StudentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.providers.StudentFactory;

import static apiEngine.Endpoints.STUDENT_DETAILS_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StudentSteps {

    private static final Logger logger = LoggerFactory.getLogger("StudentSteps.class");
    protected ReqResSpecifications specifications = new ReqResSpecifications();
    int id;
    StudentResponse studentResponse;

    @Given("new Students is registered")
    public void newStudentsIsRegistered() {
        StudentRequest newStudent = StudentFactory.createRandomStudent();

        logger.info("************* Perform <<new Students is registered>> step *************");

        id = given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT)
                .body(newStudent).
        when()
                .post().
        then()
                .spec(specifications.setupResponseSpecification())
                .statusCode(201)
                .extract()
                .path("id");
    }

    @When("details of student are saved")
    public void detailsOfStudentAreSaved() {

        logger.info("************* Perform <<details of student are saved>> step *************");

        studentResponse = given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + id).
        when()
                .get().
        then()
                .spec(specifications.setupResponseSpecification())
                .statusCode(200)
                .extract()
                .as(StudentResponse.class);
    }

    @And("middle name of student is changed")
    public void middleNameOfStudentIsChanged() {
        StudentFactory.changeStudentMiddleName(studentResponse);

        logger.info("************* Perform <<middle name of student is changed>> step *************");

         given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + id)
                .body(studentResponse.getData()).
        when()
                .put().
        then()
                .spec(specifications.setupResponseSpecification())
                .statusCode(201);
    }

    @And("checks that middle name is changed")
    public void checksThatMiddleNameIsChanged() {

        logger.info("************* Perform <<checks that middle name is changed>> step *************");

        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + id).
        when()
                .get().
        then()
                .spec(specifications.setupResponseSpecification())
                .statusCode(200)
                .assertThat()
                .body("data.middle_name", equalTo(studentResponse.getData().getMiddle_name()));
    }

    @And("delete student")
    public void deleteStudent() {

        logger.info("************* Perform <<delete student>> step *************");

        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + id).
        when()
                .delete().
        then()
                .spec(specifications.setupResponseSpecification());
    }

    @Then("verify that student does not exist")
    public void verifyThatStudentDoesNotExist() {

        logger.info("************* Perform <<verify that student does not exist>> step *************");

        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + id).
        when()
                .get().
        then()
                .spec(specifications.setupResponseSpecification())
                .statusCode(404)
                .assertThat()
                .body("msg", equalTo(System.getProperty("doesNotFoundErrorMsg")));
    }
}
