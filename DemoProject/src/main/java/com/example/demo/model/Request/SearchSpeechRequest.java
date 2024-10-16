package com.example.demo.model.Request;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchSpeechRequest {


	private String keyword = "";

	private Instant startDate;
	
	private Instant endDate;


}
