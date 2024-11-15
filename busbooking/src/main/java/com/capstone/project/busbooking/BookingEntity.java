package com.capstone.project.busbooking;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Bus Id cannot be empty.")
    private long busId;

    @OneToOne
    @JoinColumn(name="bus_entity_id", referencedColumnName = "id")
    private BusEntity busEntity;

    @NotNull(message = "Bus Id cannot be empty.")
    private long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name="booking_entity_passenger_entity", joinColumns = {@JoinColumn(name="id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "passenger_entity_id", referencedColumnName = "id")})
    @Valid
    private List<PassengerEntity> passengerList = new ArrayList<>();

    @NotEmpty
    private String paymentMethod;

    private double totalAmount;


    private int noOfPassengers;
}
