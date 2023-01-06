package models.bodyRequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {

    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;
}
