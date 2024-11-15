package com.capstone.project.busbooking;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String busNumber;

    @NotEmpty(message = "Boarding point cannot be empty.")
    private String boardingPoint;

    @NotEmpty(message = "Destination cannot be empty.")
    private String destination;

    private String driverContactNumber;

    private String departureTime;

    private String arrivalTime;

    @NotNull
    @Min(value = 1)
    @Max(value = 4)
    private int noOfPassengers;

    private int noOfWindowSeats;

    private int noOfMiddleSeats;

    private int noOfAisleSeats;

    @NotNull
    private double price;

    @OneToOne(mappedBy = "busEntity")
    private BookingEntity bookingEntity;
}
