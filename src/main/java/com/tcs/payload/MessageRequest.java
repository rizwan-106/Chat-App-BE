package com.tcs.payload;

import lombok.Data;

@Data
public class MessageRequest {

	private String sender;
	private String content;
	private String roomId;
}
