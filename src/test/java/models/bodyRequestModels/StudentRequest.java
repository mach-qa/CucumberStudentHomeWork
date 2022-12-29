package models.bodyRequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
}
