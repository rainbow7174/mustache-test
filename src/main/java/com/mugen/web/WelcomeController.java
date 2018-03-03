package com.mugen.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {
	@GetMapping("/getwelcome")
	public String getwelcome(String name, int age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "welcome";
	}
	
	@PostMapping("/postwelcome")
	public String postwelcome() {
		return "welcome";
	}
}
