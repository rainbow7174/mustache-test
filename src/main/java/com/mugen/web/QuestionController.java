package com.mugen.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mugen.domain.Question;
import com.mugen.domain.QuestionRepository;
import com.mugen.domain.Result;
import com.mugen.domain.User;
import com.mugen.utils.HttpSessionUtils;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser, title, contents);
		questionRepo.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepo.findOne(id));
		return "qna/show";
	}
	
	private Result valid(HttpSession session, Question question) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인이 필요합니다.");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)) {
			return Result.fail("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
		return Result.ok();
	}
	
//	private boolean hasPermission(HttpSession session, Question question) {
//		if(!HttpSessionUtils.isLoginUser(session)) {
//			throw new IllegalStateException("로그인이 필요합니다.");
//		}
//		User loginUser = HttpSessionUtils.getUserFromSession(session);
//		if(!question.isSameWriter(loginUser)) {
//			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다.");
//		}
//		return true;
//	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepo.findOne(id);
		Result result = valid(session, question);
		if(!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		model.addAttribute("question", question);
		return "qna/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		Question question = questionRepo.findOne(id);
		Result result = valid(session, question);
		if(!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		question.update(title, contents);
		questionRepo.save(question);
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepo.findOne(id);
		Result result = valid(session, question);
		if(!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		questionRepo.delete(id);
		return "redirect:/";
	}
}
