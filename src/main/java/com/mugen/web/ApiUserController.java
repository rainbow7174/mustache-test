package com.mugen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mugen.domain.User;
import com.mugen.domain.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/{id}")
	public User userinfo(@PathVariable Long id) {
		return userRepo.findOne(id);
	}
}
