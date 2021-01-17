package com.frdevstudio.learningspring.data.repository;

import com.frdevstudio.learningspring.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    // Spring Data will create the query based on the name below
    Iterable<Reservation> findReservationByReservationDate(Date date);
}
