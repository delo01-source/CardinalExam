package com.example.demo.model.Response;

import java.time.Instant;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCustomerAndReservationRequest {


	private String name;

	@Email(message = "Email is not valid")
	@Pattern(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	private String phoneNumber;

	private Instant reservationDate;

	private int numOfGuests;

	@Hidden
	private long customerId;

}
