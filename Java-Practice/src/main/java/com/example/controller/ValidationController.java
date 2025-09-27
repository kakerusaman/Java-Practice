package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.ValidationForm;

@Controller
public class ValidationController {

	
	@GetMapping("/validation")
	public String Validation(Model model) {
		model.addAttribute("validationForm", new ValidationForm());
		return "validation";
	}
	
	@PostMapping("/validation")
	public String submitValidation(@Validated ValidationForm form, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        return "validation";
	    }
	    // フォームの処理
	    model.addAttribute("message", form.getRadio() + "が選択されました。");
	    return "result";
	}
}
