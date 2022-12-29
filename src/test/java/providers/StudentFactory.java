package providers;

import com.github.javafaker.Faker;
import models.bodyRequestModels.StudentRequest;

import java.util.Locale;


public class StudentFactory {

    static Faker usFaker = new Faker(new Locale("en-US"));

    public static StudentRequest createRandomStudent(){
        return new StudentRequest(
                usFaker.name().firstName(),
                usFaker.name().name(),
                usFaker.name().lastName(),
                usFaker.date().birthday().toString()
        );
    }

    public void changeRequestedValue(StudentRequest item) {
        
    }
}
