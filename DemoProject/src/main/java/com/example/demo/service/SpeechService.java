package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.Speech;
import com.example.demo.model.Request.SearchSpeechRequest;
import com.example.demo.repository.SpeechRepository;

@Service
public class SpeechService {
	
	private SpeechRepository speechRepository;
	
	public SpeechService(SpeechRepository speechRepository) {
		this.speechRepository = speechRepository;
	}

	// creates or updates a customer
	public Speech saveSpeech(Speech speech) {

		speechRepository.save(speech);
		return speech;

	}
	
	// creates or updates a customer
	public Iterable<Speech> getAllSpeeches() {

		return speechRepository.findAll();
	}

	
	public Iterable<Speech> searchSpeech(SearchSpeechRequest request){
		
		
		return speechRepository.findByStringIgnoreCase(request.getKeyword(), request.getStartDate(), request.getEndDate());
		 
		
	}
	
	public Optional<Speech> findSpeechById(Long ID){
		return this.speechRepository.findById(ID);
		
	}
	
	public boolean deleteSpeech(Long ID){
		//Update remarks instead
		speechRepository.deleteById(ID);
		return true;
		
	}

}
