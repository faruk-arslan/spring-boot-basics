package com.frdevstudio.learningspring;

import com.frdevstudio.learningspring.data.entity.Room;
import com.frdevstudio.learningspring.data.repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	// this is not a best way to do that, just for example
	@RestController
	@RequestMapping("/rooms")
	public class RoomController{

		private final RoomRepository roomRepository;

		public RoomController(RoomRepository roomRepository) {
			this.roomRepository = roomRepository;
		}

		@GetMapping
		public Iterable<Room> getRooms(){
			return this.roomRepository.findAll();
		}
	}
}
