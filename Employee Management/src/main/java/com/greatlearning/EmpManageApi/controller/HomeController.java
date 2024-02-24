package com.greatlearning.EmpManageApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return ("<h1> Welcome to Employee Management Application</h1>");
	}
}
