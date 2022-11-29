package first.springboot.springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data

public class Customer {
    private Integer id;
    private  String firstName;
    private  String lastName;
    private LocalDate birthdate;
    private Boolean deleted;

}
