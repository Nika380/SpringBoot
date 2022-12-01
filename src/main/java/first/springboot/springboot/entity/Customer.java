package first.springboot.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


import java.time.LocalDate;

@Data
@Entity
public class Customer {
    @Id
    private Integer id;
    private  String firstName;
    private  String lastName;
    private LocalDate birthdate;
    private Boolean deleted;

}
