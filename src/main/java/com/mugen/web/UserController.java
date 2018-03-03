package com.mugen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mugen.domain.User;
import com.mugen.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
//	private List<User> users = new ArrayList<User>();
	
//	@PostMapping("/create")
//	public String create(User user) {
//		System.out.println("user: " + user.toString());
////		users.add(user);
//		userRepo.save(user);
//		return "redirect:/list";
//	}
//	
//	@GetMapping("/list")
//	public String list(Model model) {
////		model.addAttribute("users", users);
//		model.addAttribute("users", userRepo.findAll());
//		return "list";
//	}
	
	@PostMapping("/user/create")
	public String createUser(User user) {
		userRepo.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/user/list")
	public String listUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "user/list";
	}
}
