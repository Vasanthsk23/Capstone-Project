package com.capstone.project.busbooking;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Optional;

@SpringBootTest
public class BookingControllerTest {

    @Mock
    BookingRepository bookingRepository;

    @Mock
    BusRepository busRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BookingController bookingController;

    @Before("")
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBookHomePage(){
        Model model = Mockito.mock(Model.class);
        String result = bookingController.bookHomePage(model);
        Assertions.assertEquals("booking", result);
    }

    @Test
    public void testsearch(){
        Model model = Mockito.mock(Model.class);
        String result = bookingController.search(model);
        Assertions.assertEquals("booking", result);
    }

    @Test
    public void testbookTicketHasErrors(){
        Model model = Mockito.mock(Model.class);
        Principal principal = Mockito.mock(Principal.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        BookingEntity bookingEntity = Mockito.mock(BookingEntity.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        String result = bookingController.bookTicket(123, 3, model, principal, bookingEntity, bindingResult);
        Assertions.assertEquals("passenger-form", result);
    }

    @Test
    public void testbookTicket(){
        Model model = Mockito.mock(Model.class);
        Principal principal = Mockito.mock(Principal.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        BookingEntity bookingEntity = Mockito.mock(BookingEntity.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(userRepository.findByEmailId(Mockito.any())).thenReturn(Mockito.mock(UserEntity.class));
        Optional<BusEntity> busEntity = Mockito.mock(Optional.class);
        Mockito.when(busRepository.findById(Mockito.any())).thenReturn(busEntity);
        String result = bookingController.bookTicket(123, 3, model, principal, bookingEntity, bindingResult);
        Assertions.assertEquals("bus-ticket", result);
    }

}
