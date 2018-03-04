package com.mugen.web;

import javax.servlet.http.HttpSession;

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
import com.mugen.utils.HttpSessionUtils;

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
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String userid, String password, HttpSession session) {
		User user = userRepo.findByUserid(userid);
		
		if(user == null) {
			System.out.println("login Failure : 없는 사용자");
			return "redirect:/users/loginForm";
		}
		
		if(!user.matchPassword(password)) {
			System.out.println("login Failure : 암호 틀림");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("login Success!!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
	
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
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("Warning : You cannot update another user's information.");
		}
		
		model.addAttribute("user", userRepo.findOne(id));
		return "user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		if(HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("Warning : You cannot update another user's information.");
		}
		
		User user = userRepo.findOne(id);
		user.update(updatedUser);
		userRepo.save(user);
		return "redirect:/users";
	}

}
