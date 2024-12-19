package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class indexController {
	
	@GetMapping("/")
	public String index() {  // http://localhost:8081/psi/index/
		return "index";
	}
}
