package com.example.demo.model.Request;

import java.time.Instant;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class UpdateSpeechRequest {

	private Long speechId;
	
	private String keywords;
	
	private String author;
	
	@Lob
	private String content;
	
	private Instant speechDate;
	
}
