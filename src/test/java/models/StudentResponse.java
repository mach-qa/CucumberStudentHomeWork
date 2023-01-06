package models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.Data;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private String status;
    private Data data;
}
