package com.example.form;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationForm {

	@NotNull
	private String radio;
	
}
