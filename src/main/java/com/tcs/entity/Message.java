package com.tcs.entity;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Message {

	private String sender;
	private String content;
	
	private LocalTime timeStamp;

	public Message() {
	}

	public Message(String sender, String content) {
		this.sender = sender;
		this.content = content;
		this.timeStamp = LocalTime.now();
	}
}
