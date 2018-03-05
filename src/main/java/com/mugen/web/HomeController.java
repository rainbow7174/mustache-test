package com.mugen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mugen.domain.QuestionRepository;

@Controller
public class HomeController {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("question", questionRepo.findAll());
		return "index";
	}
}
