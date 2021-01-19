package com.frdevstudio.learningspring.web;

import com.frdevstudio.learningspring.business.domain.RoomReservation;
import com.frdevstudio.learningspring.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
// pass the controller
@WebMvcTest(RoomReservationWebController.class)
public class RoomReservationWebControllerTest {
    // allows to use mockito
    @MockBean
    private ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getReservations() throws Exception{
        String dateString="2020-01-01";
        Date date=DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations=new ArrayList<>();
        RoomReservation roomReservation=new RoomReservation();
        roomReservation.setLastName("Unit");
        roomReservation.setFirstName("J");
        roomReservation.setDate(date);
        roomReservation.setGuestId(1001);
        roomReservation.setRoomId(1002);
        roomReservation.setRoomName("New Room");
        roomReservation.setRoomNumber("N1000");
        roomReservations.add(roomReservation);
        given(reservationService.getRoomReservationForDate(date)).willReturn(roomReservations);

        this.mockMvc.perform(get("/reservations?date=2020-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Unit, J")));


    }
}
