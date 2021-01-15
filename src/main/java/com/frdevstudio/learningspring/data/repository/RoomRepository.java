package com.frdevstudio.learningspring.data.repository;

import com.frdevstudio.learningspring.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// long is the type of the id
public interface RoomRepository extends CrudRepository<Room,Long> {
}
