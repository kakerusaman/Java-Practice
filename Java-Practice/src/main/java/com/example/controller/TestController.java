package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mapper.SettingsMapper;
import com.example.model.Settings;

@Controller
public class TestController {
	
	@Autowired
	private SettingsMapper settingsMapper;
	
	@GetMapping("/")
	public String hogme() {
		return "index";
	}
	
	
	@GetMapping("/test")
	public String test() {
		List<Settings> settings = settingsMapper.findAll();
		System.out.println(settings.get(0));
		return "validation";
	}
}
