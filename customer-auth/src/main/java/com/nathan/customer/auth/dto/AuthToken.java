package com.nathan.customer.auth.dto;

import java.time.LocalDateTime;

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
	private String username;
	private LocalDateTime sessionStarted = LocalDateTime.now();
	private LocalDateTime  activeTime;
}
