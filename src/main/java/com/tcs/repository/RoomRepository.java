package com.tcs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tcs.entity.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

	Room findByRoomId(String roomId);
}
