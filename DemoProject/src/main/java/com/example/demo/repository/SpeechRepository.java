package com.example.demo.repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Speech;


@Repository
public interface SpeechRepository extends CrudRepository<Speech, Long> {
	
	Speech findById(long id);
	
	@Query(value ="SELECT * FROM speech WHERE (author like %?1% or content like %?1% or keywords like %?1%) and (speech_date BETWEEN ?2 and ?3 )", nativeQuery = true)
	Iterable<Speech> findByStringIgnoreCase(String keyword, Instant startDate , Instant endDate);

}