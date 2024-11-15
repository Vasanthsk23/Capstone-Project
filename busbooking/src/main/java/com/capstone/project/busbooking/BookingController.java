package com.capstone.project.busbooking;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Tag(name = "Booking", description = "Ticket Booking APIs")
@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusRepository busRepository;


    @GetMapping("/")
    public String bookHomePage(Model model){
        model.addAttribute("search", new BusEntity());
        return "booking";
    }
    @GetMapping("/booking")
    public String search(Model model){
        model.addAttribute("search", new BusEntity());
        return "booking";
    }


    @Operation(
            summary = "Book Ticket",
            description = "Book Ticket.",
            tags = { "Book Ticket", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = BookingEntity.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/book-ticket/{busId}/{noOfPassengers}")
    public String bookTicket(@PathVariable(value = "busId") long busId, @PathVariable(value = "noOfPassengers") int noOfPassengers,  Model model, Principal principal, @ModelAttribute("bookingDetails") @Valid BookingEntity bookingEntity, BindingResult result){
        if (result.hasErrors()){
            return "passenger-form";
        }else {
            UserEntity userEntity = userRepository.findByEmailId(principal.getName());
            bookingEntity.setBusId(busId);
            bookingEntity.setUserId(userEntity.getId());
            bookingEntity.setNoOfPassengers(noOfPassengers);
            bookingRepository.save(bookingEntity);
            model.addAttribute("bookingEntity", bookingEntity);
            BusEntity busEntity = busRepository.findById(bookingEntity.getBusId()).get();
            model.addAttribute("busEntity",busEntity);
            return "bus-ticket";
        }

    }

    @Operation(
            summary = "Retrieve all booking history for a given user id",
            description = "Get Booking History",
            tags = { "history", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("view-booking-history")
    public String viewHistory(Principal principal, Model model){
        UserEntity userEntity = userRepository.findByEmailId(principal.getName());
        List<BookingEntity> bookingHistory = bookingRepository.findAllByUserId(userEntity.getId());
        for(int i=0; i< bookingHistory.size(); i++){
            bookingHistory.get(i).setBusEntity(busRepository.findById(bookingHistory.get(i).getBusId()).get());
        }
        model.addAttribute("bookingHistory", bookingHistory);
        return "booking-history";
    }



}
