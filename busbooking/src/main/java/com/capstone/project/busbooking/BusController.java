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

import java.util.List;
@Tag(name = "Bus", description = "Bus management APIs")
@Controller
public class BusController {

    @Autowired
    BusRepository busRepository;

    @Operation(
            summary = "Search Bus",
            description = "Get Bus Details",
            tags = { "Bus Details", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/search")
    public String searchBus(Model model, @ModelAttribute("search") @Valid BusEntity busEntity, BindingResult result){
        if(busEntity.getBoardingPoint().equals(busEntity.getDestination())){
            model.addAttribute("bookingError", true);
            model.addAttribute("errorMsg", "Boarding point and destination cannot be the same!!!!");
        } else if (busEntity.getNoOfPassengers() <= 0 || busEntity.getNoOfPassengers() > 4) {
            model.addAttribute("bookingError", true);
            model.addAttribute("errorMsg", "No of passengers should be 1-4!!!!");
        } else{
            List<BusEntity> listBus =  busRepository.findByBoardingPointAndDestination(busEntity.getBoardingPoint(), busEntity.getDestination());
            model.addAttribute("listOfBus", listBus);
        }

        return "booking";
    }
}
