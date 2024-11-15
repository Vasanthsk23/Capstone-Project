package com.capstone.project.busbooking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    BusRepository busRepository;


    @GetMapping("/add-passenger/{id}/{noOfPassengers}")
    public String addPassenger(Model model, @PathVariable(value = "id") long id, @PathVariable(value = "noOfPassengers") int noOfPassengers){
        List<PassengerEntity> listOfPassenger = new ArrayList<PassengerEntity>();
        for(int i=0; i< noOfPassengers; i++){
            listOfPassenger.add(new PassengerEntity());
        }
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBusId(id);
        bookingEntity.setPassengerList(listOfPassenger);
        bookingEntity.setNoOfPassengers(noOfPassengers);
        BusEntity busEntity = busRepository.findById(id).get();
        bookingEntity.setTotalAmount(noOfPassengers * busEntity.getPrice());
        model.addAttribute("bookingDetails", bookingEntity);
        return "passenger-form";

    }
}
