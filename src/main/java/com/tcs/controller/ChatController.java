package com.tcs.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.entity.Message;
import com.tcs.entity.Room;
import com.tcs.payload.MessageRequest;
import com.tcs.repository.RoomRepository;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@CrossOrigin
public class ChatController {

	private final RoomRepository roomRepo;

	public ChatController(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}

	@MessageMapping("/sendMessage/{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request)
			throws Exception {
		Room room = roomRepo.findByRoomId(request.getRoomId());
		Message msg = new Message();
		msg.setSender(request.getSender());
		msg.setContent(request.getContent());
		msg.setTimeStamp(LocalDateTime.now());


		if (room != null) {
			room.getMessages().add(msg);
			roomRepo.save(room);
		} else {
			throw new RuntimeException("Room Not Found!!");
		}
		return msg;
	}

	@MessageMapping("/typing/{roomId}")
	@SendTo("/topic/typing/{roomId}")
	public String userTyping(@DestinationVariable String roomId, @RequestBody String sender) {
		return sender;
	}
}
