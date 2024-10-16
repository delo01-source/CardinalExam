package com.example.demo.domain;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speech {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long speechId;

	private String keywords;
	
	private String author;
	
	@Lob
	private String content;
	
	private Instant speechDate;
	
	private Instant dateCreated;
	private Instant dateUpdated;
	private String remarks;
	

}
