package com.capstone.project.busbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;

@SpringBootTest
public class BusControllerTest {
    @Mock
    BusRepository busRepository;

    @InjectMocks
    BusController busController;

    @Test
    public void testSearchForError1(){
        Model model = Mockito.mock(Model.class);
        Principal principal = Mockito.mock(Principal.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        BusEntity busEntity = Mockito.mock(BusEntity.class);
        Mockito.when(busEntity.getBoardingPoint()).thenReturn("Trichy");
        Mockito.when(busEntity.getNoOfPassengers()).thenReturn(2);
        Mockito.when(busEntity.getDestination()).thenReturn("Trichy");
        String result = busController.searchBus(model, busEntity, bindingResult);
        Assertions.assertEquals("booking", result);
    }
    @Test
    public void testSearchForError2(){
        Model model = Mockito.mock(Model.class);
        Principal principal = Mockito.mock(Principal.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        BusEntity busEntity = Mockito.mock(BusEntity.class);
        Mockito.when(busEntity.getBoardingPoint()).thenReturn("Trichy");
        Mockito.when(busEntity.getNoOfPassengers()).thenReturn(0);
        Mockito.when(busEntity.getDestination()).thenReturn("Madurai");
        String result = busController.searchBus(model, busEntity, bindingResult);
        Assertions.assertEquals("booking", result);
    }
    @Test
    public void testSearchForError3(){
        Model model = Mockito.mock(Model.class);
        Principal principal = Mockito.mock(Principal.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        BusEntity busEntity = Mockito.mock(BusEntity.class);
        Mockito.when(busEntity.getBoardingPoint()).thenReturn("Trichy");
        Mockito.when(busEntity.getNoOfPassengers()).thenReturn(5);
        Mockito.when(busEntity.getDestination()).thenReturn("Trichy");
        String result = busController.searchBus(model, busEntity, bindingResult);
        Assertions.assertEquals("booking", result);
    }


}
