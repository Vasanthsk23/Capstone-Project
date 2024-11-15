package com.capstone.project.busbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Optional;

@SpringBootTest
public class PassengerCOntrollerTest {
    @Mock
    BusRepository busRepository;

    @InjectMocks
    PassengerController passengerController;

    @Test
    public void testAddPassenger(){
        Model model = Mockito.mock(Model.class);
        Optional<BusEntity> busEntity = Mockito.mock(Optional.class);
        Mockito.when(busEntity.get()).thenReturn(new BusEntity());
        Mockito.when(busRepository.findById(Mockito.anyLong())).thenReturn(busEntity);
        String result = passengerController.addPassenger(model, 123, 2);
        Assertions.assertEquals("passenger-form", result);
    }
}
