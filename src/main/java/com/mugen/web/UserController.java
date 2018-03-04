package com.mugen.web;

import javax.jms.IllegalStateException;
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
		
		if(!password.equals(user.getPassword())) {
			System.out.println("login Failure : 암호 틀림");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("login Success!!");
		session.setAttribute("sessionedUser", user);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
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
		messageWarning(session, id);
//		Object tempUser = session.getAttribute("sessionedUser");
//		
//		if(tempUser == null) {
//			return "redirect:/users/loginForm";
//		}
//		
//		User sessionedUser = (User)tempUser;
//		if(!id.equals(sessionedUser.getId())) {
//			throw new IllegalStateException("Warning : You cannot update another user's information.");
//		}
		
		model.addAttribute("user", userRepo.findOne(id));
		return "user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		messageWarning(session, id);
//		Object tempUser = session.getAttribute("sessionedUser");
//		
//		if(tempUser == null) {
//			return "redirect:/users/loginForm";
//		}
//		
//		User sessionedUser = (User)tempUser;
//		if(!id.equals(sessionedUser.getId())) {
//			throw new IllegalStateException("Warning : You cannot update another user's information.");
//		}
		
		User user = userRepo.findOne(id);
		user.update(updatedUser);
		userRepo.save(user);
		return "redirect:/users";
	}
	
	public String messageWarning(HttpSession session, Long id) {
		Object tempUser = session.getAttribute("sessionedUser");
		
		if(tempUser == null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = (User)tempUser;
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("Warning : You cannot update another user's information.");
		}
		
		return "";
	}
}
