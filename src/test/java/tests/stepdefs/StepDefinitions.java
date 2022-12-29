package tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.StudentResponse;
import models.bodyRequestModels.StudentRequest;
import providers.StudentFactory;
import tests.base.TestBase;

import static apiEngine.Endpoints.STUDENT_DETAILS_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitions extends TestBase {

    String id;
    StudentResponse studentResponse;

    @Given("new Students is registered")
    public void newStudentsIsRegistered() {

        StudentRequest newStudent = StudentFactory.createRandomStudent();

        id = given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT)
                .body(newStudent).
        when()
                .post().
        then()
                .spec(specifications.setupResponseSpecification())
                .extract()
                .path("id");
    }

    @When("details of student are saved")
    public void detailsOfStudentAreSaved() {
        studentResponse = (StudentResponse) given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + "/" + id).
        when()
                .get().
        then()
                .spec(specifications.setupResponseSpecification())
                .extract()
                .body();
    }

    @And("middle name of student is changed")
    public void middleNameOfStudentIsChanged() {
        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + "/" + id)
                //.body(studentResponse.getData().setMiddle_name("kk")).
        .when()
                .put().
        then()
                .spec(specifications.setupResponseSpecification())
                .extract()
                .body();

    }

    @And("checks that middle name is changed")
    public void checksThatMiddleNameIsChanged() {
        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + "/" + id).
        when()
                .get().
        then()
                .spec(specifications.setupResponseSpecification())
                .assertThat();
    }

    @And("delete student")
    public void deleteStudent() {
        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + "/" + id).
        when()
                .delete().
        then()
                .spec(specifications.setupResponseSpecification());
    }

    @Then("verify that student does not exist")
    public void verifyThatStudentDoesNotExist() {
        given()
                .spec(specifications.setupRequestSpecification())
                .basePath(STUDENT_DETAILS_ENDPOINT + "/" + id).
        when()
                .get().
        then()
                .spec(specifications.setupResponseSpecification())
                .assertThat()
                .body("msg", equalTo("No data Found"));
    }
}
