package com.example.demo.model.Response;

import com.example.demo.domain.Speech;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchSpeechResponse {
	
	private Iterable<Speech> speech;

}
