package com.nathan.customer.auth.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {

	private String token;
	
	@JsonIgnore
	private String username;
	
	private LocalDateTime sessionStarted = LocalDateTime.now();
	
	private LocalDateTime  activeTime;
}
