package com.frdevstudio.learningspring.business.service;

import com.frdevstudio.learningspring.business.domain.RoomReservation;
import com.frdevstudio.learningspring.data.entity.Guest;
import com.frdevstudio.learningspring.data.entity.Reservation;
import com.frdevstudio.learningspring.data.entity.Room;
import com.frdevstudio.learningspring.data.repository.GuestRepository;
import com.frdevstudio.learningspring.data.repository.ReservationRepository;
import com.frdevstudio.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.*;

// service annotation allows component scanning to occur
// we could define this on main java as bean but we used component scanning here instead
// Component annotation could be used as well
@Service
public class ReservationService {
    // once they set they should never change
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    // Autowired not required since there is only one constructor
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date){
        Iterable<Room> rooms=this.roomRepository.findAll();
        // we can populate this object as we go
        Map<Long, RoomReservation> roomReservationMap=new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation=new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });
        Iterable<Reservation> reservations=this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation=roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest=this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName((guest.getLastName()));
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations=new ArrayList<>();
        for (Long id: roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(id));
        }
        return roomReservations;
    }

}
