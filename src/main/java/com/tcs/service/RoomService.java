package com.tcs.service;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tcs.entity.Message;
import com.tcs.entity.Room;
import com.tcs.repository.RoomRepository;

@Service
public class RoomService {

	private final RoomRepository roomRepo;

	public RoomService(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}

	public ResponseEntity<?> createRoom(String roomId) {
		Room room = roomRepo.findByRoomId(roomId);
		if (room != null) {
			return ResponseEntity.badRequest().body("Room already exists!");
		}
		Room newRoom = new Room();
		newRoom.setRoomId(roomId);
		roomRepo.save(newRoom);
		return ResponseEntity.status(HttpStatus.CREATED).body(newRoom);
	}

	public ResponseEntity<?> getRoom(String roomId) {
		Room room = roomRepo.findByRoomId(roomId);
		if (room == null) {
			return ResponseEntity.badRequest().body("Room not found!");
		}
		return ResponseEntity.ok(room);
	}

	public ResponseEntity<?> getRoomMessages(String roomId, int page, int size) {
	    Room room = roomRepo.findByRoomId(roomId);
	    if (room == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
	    }

	    List<Message> messages = room.getMessages();
	    if (messages == null || messages.isEmpty()) {
	        return ResponseEntity.ok(Collections.emptyList());
	    }

	    int total = messages.size();
	    int fromIndex = Math.max(0, total - (page + 1) * size);
	    int toIndex = Math.min(total, fromIndex + size);

	    if (fromIndex >= total || fromIndex > toIndex) {
	        return ResponseEntity.ok(Collections.emptyList());
	    }

	    List<Message> paginatedMsg = messages.subList(fromIndex, toIndex);
	    return ResponseEntity.ok(paginatedMsg);
	}

}
