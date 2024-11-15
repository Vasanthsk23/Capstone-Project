package com.capstone.project.busbooking;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "First name cannot be empty.")
    private String passengerName;

    @NotNull(message = "Age cannot be empty.")
    private int age;

    @NotEmpty
    private String gender;

    private String seatPreferences;
}
