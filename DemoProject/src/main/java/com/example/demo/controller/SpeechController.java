package com.example.demo.controller;

import java.time.Instant;
import java.util.Iterator;
import java.util.Optional;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.Speech;
import com.example.demo.model.Request.CreateSpeechRequest;
import com.example.demo.model.Request.SearchSpeechRequest;
import com.example.demo.model.Request.UpdateSpeechRequest;
import com.example.demo.model.Response.SearchSpeechResponse;
import com.example.demo.repository.SpeechRepository;
import com.example.demo.service.SpeechService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Controller
@RestController
@RequestMapping("/api/v1/Speech")
public class SpeechController {

	SpeechService speechService;
	
	public SpeechController(SpeechService speechService) {
		this.speechService = speechService;
	}

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	//creates a Speech supplied Speech Request form
	@Operation(summary = "Create Speech")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Speech Created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Speech.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid information supplied", content = @Content) })
	@PostMapping("/createSpeech")
	public ResponseEntity<Object> createSpeech(@RequestBody CreateSpeechRequest request) {
		LOGGER.info("START CREATE SPEECH..");
		Speech speech = new Speech();
		
		try {
			speech.setAuthor(request.getAuthor());
			speech.setContent(request.getContent());
			speech.setSpeechDate(request.getSpeechDate());
			speechService.saveSpeech(speech);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WeakHashMap<String, String> response = new WeakHashMap<>();
			response.put("message", "Invalid Request.");
			LOGGER.info("INVALID SPEECH CREATE..");
			return ResponseEntity.badRequest().body(response);
		}
		
		
		if (speech == null)
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to insert orders.");

		LOGGER.info("END CREATE SPEECH..");
		return ResponseEntity.ok().body(speech);
	}
	
	
	//Updates the Speech assuming that the ID is given
	@Operation(summary = "Update Speech")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Speech Updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Speech.class)) }),
			@ApiResponse(responseCode = "400", description = "Speech not found", content = @Content),
			@ApiResponse(responseCode = "404", description = "Speech not found", content = @Content) })
	@PutMapping("/updateSpeech")
	public ResponseEntity<Object> updateSpeech(@RequestBody UpdateSpeechRequest request) {
		LOGGER.info("START UPDATE Speech..");
		
		Speech speech = null;
		try {
			speech.setAuthor(request.getAuthor());
			speech.setContent(request.getContent());
			speech.setSpeechDate(request.getSpeechDate());
			speech.setSpeechId(request.getSpeechId());
			speechService.saveSpeech(speech);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WeakHashMap<String, String> response = new WeakHashMap<>();
			response.put("message", "Invalid Request.");
			LOGGER.info("INVALID SPEECH UPDATE..");
			return ResponseEntity.badRequest().body(response);
		}

		LOGGER.info("END UPDATE SPEECH..");
		return ResponseEntity.ok().body(speech);

	}
	
	//Updates the Speech assuming that the ID is given
	@Operation(summary = "Search Speech")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Speech Searched", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Speech.class)) }),
			@ApiResponse(responseCode = "400", description = "Speech not found", content = @Content),
			@ApiResponse(responseCode = "404", description = "Speech not found", content = @Content) })
	@GetMapping("/searchSpeech/{keyword}/{startDate}/{endDate}")
	public ResponseEntity<Object> searchSpeech(@PathVariable String keyword, @PathVariable Instant startDate, @PathVariable Instant endDate ) {
		LOGGER.info("START SEARCH Speech..");
		SearchSpeechRequest request = new SearchSpeechRequest(keyword,startDate,endDate);
		SearchSpeechResponse response = null;

		try {
			Iterable<Speech> temp = speechService.searchSpeech(request);
			response = new SearchSpeechResponse(temp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			WeakHashMap<String, String> responseTemp = new WeakHashMap<>();
			responseTemp.put("message", "Invalid Request.");
			LOGGER.info("INVALID SPEECH SEARCH..");
			return ResponseEntity.badRequest().body(responseTemp);
		}

		LOGGER.info("END SEARCH SPEECH..");
		return ResponseEntity.ok().body(response);

	}
	
	//Updates the Speech assuming that the ID is given
		@Operation(summary = "Get All Speech")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Speech Searched", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Speech.class)) }),
				@ApiResponse(responseCode = "400", description = "Speech not found", content = @Content),
				@ApiResponse(responseCode = "404", description = "Speech not found", content = @Content) })
		@GetMapping("/getAllSpeeches")
		public ResponseEntity<Object> getAllSpeeches() {
			LOGGER.info("START SEARCH Speech..");
			SearchSpeechResponse response = null;

			try {
				Iterable<Speech> temp = speechService.getAllSpeeches();
				response = new SearchSpeechResponse(temp);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				WeakHashMap<String, String> responseTemp = new WeakHashMap<>();
				responseTemp.put("message", "Invalid Request.");
				LOGGER.info("INVALID SPEECH SEARCH..");
				return ResponseEntity.badRequest().body(responseTemp);
			}

			LOGGER.info("END SEARCH SPEECH..");
			return ResponseEntity.ok().body(response);

		}
	
	


}
