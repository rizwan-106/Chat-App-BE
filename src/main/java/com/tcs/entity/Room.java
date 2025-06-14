package com.tcs.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "rooms")
public class Room {
	@Id
	private String id;
	private String roomId;
	private List<Message> messages=new ArrayList<>();
}
