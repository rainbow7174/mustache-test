package com.mugen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mugen.domain.User;
import com.mugen.domain.UserRepository;

@Controller
@RequestMapping("/users")
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
	
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	@PostMapping("")
	public String create(User user) {
		userRepo.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepo.findOne(id));
		return "user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepo.findOne(id);
		user.update(newUser);
		userRepo.save(user);
		return "redirect:/users";
	}
}
