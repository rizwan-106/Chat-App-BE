package com.tcs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.service.RoomService;

@RestController
@RequestMapping("/api/v1/rooms")
//@CrossOrigin(origins ="http://localhost:5173")
//@CrossOrigin
public class RoomController {

	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId) {
		return roomService.createRoom(roomId);
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<?> getRoom(@PathVariable String roomId) {
		return roomService.getRoom(roomId);
	}

	@GetMapping("/{roomId}/messages")
	public ResponseEntity<?> getRoomMessages(@PathVariable String roomId,
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "20", required = false) int size) {
		return roomService.getRoomMessages(roomId, page, size);
	}

}
