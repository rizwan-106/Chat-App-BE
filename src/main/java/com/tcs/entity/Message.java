package com.tcs.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Message {

	private String sender;
	private String content;
	
	private LocalDateTime timeStamp;

	public Message() {
	}

	public Message(String sender, String content) {
		this.sender = sender;
		this.content = content;
		this.timeStamp = LocalDateTime.now();
	}
}
