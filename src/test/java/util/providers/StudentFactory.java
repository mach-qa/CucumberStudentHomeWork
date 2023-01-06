package util.providers;

import models.StudentResponse;
import models.bodyRequestModels.StudentRequest;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;


public class StudentFactory {

    private static final Logger logger = LoggerFactory.getLogger("StudentFactory.class");

    static Faker usFaker = new Faker(new Locale("en-US"));

    public static StudentRequest createRandomStudent(){
        logger.info("<------------------ Start creating a new Student Object ------------------>");
        return new StudentRequest(
                usFaker.name().firstName(),
                usFaker.name().name(),
                usFaker.name().lastName(),
                usFaker.date().birthday(18, 65, "YYYY/MM/dd")
        );
    }

    public static void changeStudentMiddleName(StudentResponse item) {
        logger.info("<------------------ Change Middle Name in given Student Object ------------------>");
        item.getData().setMiddle_name(usFaker.name().firstName());
    }
}
